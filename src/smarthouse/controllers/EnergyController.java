package smarthouse.controllers;

import smarthouse.models.*;
import smarthouse.utils.ExceptionHandler;

import java.util.List;

public class EnergyController {
    private final List<EnergySource> energySources;

    public EnergyController(List<EnergySource> energySources) {
        this.energySources = energySources; // Retain the values loaded by FileHandler
    }

    public void addEnergySource(String name, double energyGenerated, double regenerateRate) {
        try {
            EnergySource source;
            switch (name) {
                case "SolarSource" -> source = new SolarSource(name, energyGenerated, regenerateRate);
                case "WindSource" -> source = new WindSource(name, energyGenerated, regenerateRate);
                case "WaterSource" -> source = new WaterSource(name, energyGenerated, regenerateRate);
                default -> {
                    // Handle custom energy source types dynamically
                    source = new EnergySource(name, energyGenerated, regenerateRate) {
                        @Override
                        public String getType() {
                            return "Custom";
                        }
                    };
                    System.out.println("Custom energy source created: " + name);
                }
            }
            energySources.add(source);
            System.out.println("Energy source added successfully: " + name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            ExceptionHandler.handleCustomException("EnergyController.addEnergySource", e.getMessage());
        } catch (Exception e) {
            ExceptionHandler.handleException("EnergyController.addEnergySource", e);
        }
    }


    public void removeEnergySource(int index) {
        try {
            if (isValidIndex(index)) {
                energySources.remove(index - 1);
                System.out.println("Energy source removed successfully.");
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("EnergyController.removeEnergySource", e);
        }
    }

    public void turnOnEnergySource(int index) {
        try {
            if (isValidIndex(index)) {
                EnergySource source = energySources.get(index - 1);
                if (source.isOn()) {
                    System.out.println("The energy source is already ON.");
                } else {
                    source.setOn(true);
                    System.out.println("Energy source turned on successfully.");
                }
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("EnergyController.turnOnEnergySource", e);
        }
    }

    public void turnOffEnergySource(int index) {
        try {
            if (isValidIndex(index)) {
                EnergySource source = energySources.get(index - 1);
                if (!source.isOn()) {
                    System.out.println("The energy source is already OFF.");
                } else {
                    source.setOn(false);
                    System.out.println("Energy source turned off successfully.");
                }
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("EnergyController.turnOffEnergySource", e);
        }
    }

    public void listEnergySources() {
        try {
            if (energySources.isEmpty()) {
                System.out.println("No energy sources to display.");
            } else {
                System.out.println("--- Energy Sources ---");
                for (int i = 0; i < energySources.size(); i++) {
                    EnergySource source = energySources.get(i);
                    System.out.printf("%d. %s (Type: %s, Energy: %.2f kWh, State: %s)%n",
                            i + 1, source.getName(), source.getType(),
                            source.getEnergyGenerated(), source.isOn() ? "ON" : "OFF");
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("EnergyController.listEnergySources", e);
        }
    }

    public List<EnergySource> getEnergySources() {
        return energySources;
    }

    public double getTotalEnergyGenerated() {
        try {
            return energySources.stream()
                    .filter(EnergySource::isOn)
                    .mapToDouble(EnergySource::getEnergyGenerated)
                    .sum();
        } catch (Exception e) {
            ExceptionHandler.handleException("EnergyController.getTotalEnergyGenerated", e);
            return 0;
        }
    }

    private boolean isValidIndex(int index) {
        return index > 0 && index <= energySources.size();
    }
}
