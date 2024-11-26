package smarthouse;

import smarthouse.controllers.ApplianceController;
import smarthouse.controllers.EnergyController;
import smarthouse.controllers.HouseManager;
import smarthouse.resources.logs.Logger;
import smarthouse.utils.ExceptionHandler;
import smarthouse.utils.FileHandler;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ApplianceController applianceController = new ApplianceController(FileHandler.loadAppliances());
    private static final EnergyController energyController = new EnergyController(FileHandler.loadEnergySources());
    private static final HouseManager houseManager = new HouseManager(applianceController, energyController);

    public static void main(String[] args) {
        Logger.logSimulation("SmartHouse system started.");
        try {
            while (true) {
                try {
                    printMenu();
                    String choice = scanner.nextLine().trim();
                    switch (choice) {
                        case "1" -> manageSmartDevices();
                        case "2" -> manageEnergySources();
                        case "3" -> simulateEnergyConsumptionAndGeneration();
                        case "4" -> manageLogs();
                        case "5" -> {
                            Logger.logSimulation("SmartHouse system exited.");
                            System.out.println("Exiting the SmartHouse system. Goodbye!");
                            return;
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                } catch (Exception e) {
                    ExceptionHandler.handleException("Main", e);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("Main", e);
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Smart House Management System ===");
        System.out.println("1. Manage Smart Devices");
        System.out.println("2. Manage Energy Sources");
        System.out.println("3. Simulate Energy Consumption and Generation");
        System.out.println("4. Manage Logs");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static void manageLogs() {
        while (true) {
            System.out.println("\n=== Manage Logs ===");
            System.out.println("1. View Simulation Logs");
            System.out.println("2. View Error Logs");
            System.out.println("3. Clear Simulation Logs");
            System.out.println("4. Clear Error Logs");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            try {
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> System.out.println("\n--- Simulation Logs ---\n" + Logger.readSimulationLogs());
                    case "2" -> System.out.println("\n--- Error Logs ---\n" + Logger.readErrorLogs());
                    case "3" -> {
                        Logger.clearSimulationLogs();
                        System.out.println("Simulation logs cleared.");
                    }
                    case "4" -> {
                        Logger.clearErrorLogs();
                        System.out.println("Error logs cleared.");
                    }
                    case "5" -> {
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                ExceptionHandler.handleException("manageLogs", e);
            }
        }
    }

    private static void manageSmartDevices() {
        while (true) {
            System.out.println("\n=== Manage Smart Devices ===");
            System.out.println("1. Add a Smart Device");
            System.out.println("2. Remove a Smart Device");
            System.out.println("3. Turn a Device ON");
            System.out.println("4. Turn a Device OFF");
            System.out.println("5. View Devices");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");

            try {
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter device name: ");
                        String deviceName = scanner.nextLine().trim();
                        if (deviceName.isEmpty()) {
                            System.out.println("Device name cannot be empty.");
                            break;
                        }
                        System.out.print("Enter energy consumption (kWh): ");
                        double consumption = getValidDoubleInput("Energy consumption must be positive.");
                        applianceController.addAppliance(deviceName, consumption);
                        Logger.logSimulation("Added device: " + deviceName + ", Consumption: " + consumption + " kWh.");
                    }
                    case "2" -> {
                        applianceController.listAppliances();
                        System.out.print("Enter index to remove: ");
                        int index = getValidIndexInput(applianceController.getAppliances().size());
                        applianceController.removeAppliance(index);
                        Logger.logSimulation("Removed device at index: " + index);
                    }
                    case "3" -> {
                        applianceController.listAppliances();
                        System.out.print("Enter index to turn ON: ");
                        int index = getValidIndexInput(applianceController.getAppliances().size());
                        applianceController.turnOnAppliance(index);
                        Logger.logSimulation("Turned ON device at index: " + index);
                    }
                    case "4" -> {
                        applianceController.listAppliances();
                        System.out.print("Enter index to turn OFF: ");
                        int index = getValidIndexInput(applianceController.getAppliances().size());
                        applianceController.turnOffAppliance(index);
                        Logger.logSimulation("Turned OFF device at index: " + index);
                    }
                    case "5" -> applianceController.listAppliances();
                    case "6" -> {
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                ExceptionHandler.handleException("manageSmartDevices", e);
            }
        }
    }

    private static void manageEnergySources() {
        while (true) {
            System.out.println("\n=== Manage Energy Sources ===");
            System.out.println("1. Add an Energy Source");
            System.out.println("2. Remove an Energy Source");
            System.out.println("3. Turn an Energy Source ON");
            System.out.println("4. Turn an Energy Source OFF");
            System.out.println("5. View Energy Sources");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");

            try {
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter energy source type (SolarSource, WindSource, WaterSource): ");
                        String sourceType = scanner.nextLine().trim();
                        if (!isValidEnergySourceType(sourceType)) {
                            System.out.println("Invalid energy source type.");
                            break;
                        }
                        System.out.print("Enter initial energy (kWh): ");
                        double initialEnergy = getValidDoubleInput("Initial energy must be positive.");
                        System.out.print("Enter regeneration rate (kWh): ");
                        double regenerationRate = getValidDoubleInput("Regeneration rate must be positive.");
                        energyController.addEnergySource(sourceType, initialEnergy, regenerationRate);
                        Logger.logSimulation("Added energy source: " + sourceType + ", Initial Energy: " + initialEnergy + " kWh, Regeneration Rate: " + regenerationRate + " kWh.");
                    }
                    case "2" -> {
                        energyController.listEnergySources();
                        System.out.print("Enter index to remove: ");
                        int index = getValidIndexInput(energyController.getEnergySources().size());
                        energyController.removeEnergySource(index);
                        Logger.logSimulation("Removed energy source at index: " + index);
                    }
                    case "3" -> {
                        energyController.listEnergySources();
                        System.out.print("Enter index to turn ON: ");
                        int index = getValidIndexInput(energyController.getEnergySources().size());
                        energyController.turnOnEnergySource(index);
                        Logger.logSimulation("Turned ON energy source at index: " + index);
                    }
                    case "4" -> {
                        energyController.listEnergySources();
                        System.out.print("Enter index to turn OFF: ");
                        int index = getValidIndexInput(energyController.getEnergySources().size());
                        energyController.turnOffEnergySource(index);
                        Logger.logSimulation("Turned OFF energy source at index: " + index);
                    }
                    case "5" -> energyController.listEnergySources();
                    case "6" -> {
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                ExceptionHandler.handleException("manageEnergySources", e);
            }
        }
    }

    private static double getValidDoubleInput(String errorMessage) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0) return value;
                System.out.println(errorMessage);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static int getValidIndexInput(int maxIndex) {
        while (true) {
            try {
                int index = Integer.parseInt(scanner.nextLine().trim());
                if (index > 0 && index <= maxIndex) return index;
                System.out.println("Invalid index. Please enter a number between 1 and " + maxIndex + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static boolean isValidEnergySourceType(String sourceType) {
        return sourceType.equals("SolarSource") || sourceType.equals("WindSource") ||
               sourceType.equals("WaterSource") || !sourceType.isEmpty();
    }

    private static void simulateEnergyConsumptionAndGeneration() {
        try {
            System.out.println("\n=== Simulate Energy Consumption and Generation ===");
            Logger.logSimulation("Simulation started.");
            houseManager.startSimulation();
            System.out.println("Press Enter to stop the simulation.");
            scanner.nextLine();
            houseManager.stopSimulation();
            Logger.logSimulation("Simulation stopped.");
        } catch (Exception e) {
            ExceptionHandler.handleException("simulateEnergyConsumptionAndGeneration", e);
        }
    }
}
