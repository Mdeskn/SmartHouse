package smarthouse.controllers;

import smarthouse.models.Appliance;
import smarthouse.utils.ExceptionHandler;

import java.util.List;

public class ApplianceController {
    private final List<Appliance> appliances;

    public ApplianceController(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    public void addAppliance(Appliance appliance) {
        try {
            appliances.add(appliance);
            System.out.println("Appliance added successfully.");
        } catch (Exception e) {
            ExceptionHandler.handleException("ApplianceController.addAppliance", e);
        }
    }

    public void addAppliance(String name, double energyConsumption) {
        try {
            Appliance appliance = new Appliance(name, energyConsumption) {
                @Override
                public String getType() {
                    return "Custom Appliance";
                }
            };
            appliances.add(appliance);
            System.out.println("Appliance added successfully: " + name);
        } catch (Exception e) {
            ExceptionHandler.handleException("ApplianceController.addAppliance (String, double)", e);
        }
    }

    public void removeAppliance(int index) {
        try {
            if (isValidIndex(index, appliances)) {
                appliances.remove(index - 1);
                System.out.println("Appliance removed successfully.");
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("ApplianceController.removeAppliance", e);
        }
    }

    public void turnOnAppliance(int index) {
        try {
            if (isValidIndex(index, appliances)) {
                Appliance appliance = appliances.get(index - 1);
                if (appliance.isOn()) {
                    System.out.println("The appliance is already ON.");
                } else {
                    appliance.setOn(true);
                    System.out.println("Appliance turned on successfully.");
                }
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("ApplianceController.turnOnAppliance", e);
        }
    }

    public void turnOffAppliance(int index) {
        try {
            if (isValidIndex(index, appliances)) {
                Appliance appliance = appliances.get(index - 1);
                if (!appliance.isOn()) {
                    System.out.println("The appliance is already OFF.");
                } else {
                    appliance.setOn(false);
                    System.out.println("Appliance turned off successfully.");
                }
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("ApplianceController.turnOffAppliance", e);
        }
    }

    public void listAppliances() {
        try {
            if (appliances.isEmpty()) {
                System.out.println("No appliances to display.");
            } else {
                System.out.println("--- Appliances ---");
                for (int i = 0; i < appliances.size(); i++) {
                    Appliance appliance = appliances.get(i);
                    System.out.printf("%d. %s (Type: %s, Energy: %.2f kWh, State: %s)%n",
                            i + 1, appliance.getName(), appliance.getType(),
                            appliance.getEnergyConsumption(), appliance.isOn() ? "ON" : "OFF");
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("ApplianceController.listAppliances", e);
        }
    }

    private boolean isValidIndex(int index, List<?> list) {
        return index > 0 && index <= list.size();
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public double getTotalEnergyConsumption() {
        try {
            return appliances.stream()
                    .filter(Appliance::isOn)
                    .mapToDouble(Appliance::getEnergyConsumption)
                    .sum();
        } catch (Exception e) {
            ExceptionHandler.handleException("ApplianceController.getTotalEnergyConsumption", e);
            return 0;
        }
    }
}

