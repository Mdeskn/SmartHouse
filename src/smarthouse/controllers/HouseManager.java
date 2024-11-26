package smarthouse.controllers;

import smarthouse.models.Appliance;
import smarthouse.models.EnergySource;
import smarthouse.resources.logs.Logger;
import smarthouse.utils.ExceptionHandler;
import smarthouse.concurrency.TaskScheduler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HouseManager {
    private final ApplianceController applianceController;
    private final EnergyController energyController;
    private final TaskScheduler taskScheduler;
    private volatile boolean simulationRunning = false;

    public HouseManager(ApplianceController applianceController, EnergyController energyController) {
        this.applianceController = applianceController;
        this.energyController = energyController;
        this.taskScheduler = new TaskScheduler();
    }

    public void startSimulation() {
        if (simulationRunning) {
            System.out.println("Simulation is already running.");
            return;
        }
        simulationRunning = true;
        Logger.clearRecentLogs();
        Logger.logSimulation("Simulation started.");
        try {
            taskScheduler.scheduleAtFixedRate(this::runSimulation, 0, 2, TimeUnit.SECONDS);
        } catch (Exception e) {
            ExceptionHandler.handleException("HouseManager.startSimulation", e);
        }
    }

    public void stopSimulation() {
        if (!simulationRunning) {
            System.out.println("Simulation is not running.");
            return;
        }
        simulationRunning = false;
        try {
            taskScheduler.shutdown();
            Logger.logSimulation("Simulation stopped.");
            System.out.println("Simulation stopped.");
        } catch (Exception e) {
            ExceptionHandler.handleException("HouseManager.stopSimulation", e);
        }
    }

    private final Map<Appliance, EnergySource> applianceSourceMap =
            Collections.synchronizedMap(new HashMap<>());

    private void runSimulation() {
        try {
            double totalGenerated = 0;
            double totalConsumption = 0;

            // Process energy sources
            synchronized (energyController.getEnergySources()) {
                for (EnergySource source : energyController.getEnergySources()) {
                    try {
                        if (source.isOn()) {
                            source.regenerateEnergy(); // Only regenerate energy for "ON" sources
                        }
                        totalGenerated += source.getEnergyGenerated();
                        System.out.printf("Source: %s, State: %s, Energy: %.2f kWh%n",
                                source.getName(), source.isOn() ? "ON" : "OFF", source.getEnergyGenerated());
                    } catch (Exception e) {
                        ExceptionHandler.handleException("HouseManager.runSimulation - EnergySource: " + source.getName(), e);
                    }
                }
            }

            // Allocate energy to appliances
            synchronized (applianceSourceMap) {
                for (Appliance appliance : applianceController.getAppliances()) {
                    try {
                        if (!appliance.isOn()) continue;

                        EnergySource source = applianceSourceMap.get(appliance);

                        // Find or allocate a source
                        if (source == null || source.getEnergyGenerated() < appliance.getEnergyConsumption()) {
                            source = findAvailableSource(appliance);

                            if (source != null) {
                                applianceSourceMap.put(appliance, source);
                                System.out.printf("Mapped %s to %s with %.2f kWh available.%n",
                                        appliance.getName(), source.getName(), source.getEnergyGenerated());
                                Logger.logSimulation(String.format("Mapped %s to %s with %.2f kWh available.",
                                        appliance.getName(), source.getName(), source.getEnergyGenerated()));
                            } else {
                                System.out.printf("No suitable energy source found for %s. Turning it OFF.%n", appliance.getName());
                                Logger.logSimulation(String.format("No suitable energy source for %s. Turning it OFF.", appliance.getName()));
                                appliance.setOn(false);
                                applianceSourceMap.remove(appliance);
                                continue;
                            }
                        }

                        // Deduct energy
                        double consumption = appliance.getEnergyConsumption();
                        source.setEnergyGenerated(source.getEnergyGenerated() - consumption);
                        totalConsumption += consumption;

                        // Notify if energy is being consumed from an "OFF" source
                        if (!source.isOn()) {
                            System.out.printf("Using energy from %s (OFF).%n", source.getName());
                            Logger.logSimulation(String.format("Using energy from %s (OFF).", source.getName()));
                        }
                    } catch (Exception e) {
                        ExceptionHandler.handleException("HouseManager.runSimulation - Appliance: " + appliance.getName(), e);
                    }
                }
            }

            // Output simulation data
            System.out.printf("\n--- Simulation Data ---\nGenerated: %.2f kWh\nConsumed: %.2f kWh\nBalance: %.2f kWh%n",
                    totalGenerated, totalConsumption, Math.max(0, totalGenerated - totalConsumption));

        } catch (Exception e) {
            ExceptionHandler.handleException("HouseManager.runSimulation", e);
        }
    }

    private EnergySource findAvailableSource(Appliance appliance) {
        synchronized (energyController.getEnergySources()) {
            for (EnergySource source : energyController.getEnergySources()) {
                try {
                    if (source.getEnergyGenerated() >= appliance.getEnergyConsumption()) {
                        if (!source.isOn()) {
                            System.out.printf("Allocating energy from %s (OFF).%n", source.getName());
                            Logger.logSimulation(String.format("Allocating energy from %s (OFF).", source.getName()));
                        }
                        return source;
                    }
                } catch (Exception e) {
                    ExceptionHandler.handleException("HouseManager.findAvailableSource - Source: " + source.getName(), e);
                }
            }
            return null; // No suitable source found
        }
    }
}
