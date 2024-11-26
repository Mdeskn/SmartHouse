package smarthouse.models;

public abstract class Appliance {
    private String name;
    private boolean isOn;
    private double energyConsumption; // Energy consumption in kWh

    public Appliance(String name, double energyConsumption) {
        this.name = name;
        this.energyConsumption = energyConsumption;
        this.isOn = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public double getEnergyConsumption() {
        return isOn ? energyConsumption : 0;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    // Abstract method to be implemented by specific appliances
    public abstract String getType();
}
