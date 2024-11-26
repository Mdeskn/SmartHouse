package smarthouse.models;

public abstract class EnergySource {
    private String name;
    private boolean isOn;
    private double energyGenerated;
    private double regenerateRate;

    public EnergySource(String name, double energyGenerated, double regenerateRate) {
        this.name = name;
        this.energyGenerated = Math.max(0, energyGenerated);
        this.regenerateRate = regenerateRate;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public double getEnergyGenerated() {
        return Math.max(0, energyGenerated); // Always return the current energy
    }


    public void setEnergyGenerated(double energyGenerated) {
        this.energyGenerated = Math.max(0, energyGenerated);
    }

    public void regenerateEnergy() {
        if (isOn) {
            this.energyGenerated += regenerateRate;
        }
    }

    public abstract String getType();
}
