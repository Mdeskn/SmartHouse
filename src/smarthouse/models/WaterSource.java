package smarthouse.models;

public class WaterSource extends EnergySource {
    public WaterSource(String name, double energyGenerated, double regenerateRate) {
        super(name, energyGenerated, regenerateRate);
    }

    @Override
    public String getType() {
        return "Water";
    }
}
