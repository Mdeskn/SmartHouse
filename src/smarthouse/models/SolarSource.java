package smarthouse.models;

public class SolarSource extends EnergySource {
    public SolarSource(String name, double energyGenerated, double regenerateRate) {
        super(name, energyGenerated, regenerateRate);
    }

    @Override
    public String getType() {
        return "Solar";
    }
}
