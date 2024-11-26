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

