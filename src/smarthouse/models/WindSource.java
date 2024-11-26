package smarthouse.models;

public class WindSource extends EnergySource {
    public WindSource(String name, double energyGenerated, double regenerateRate) {
        super(name, energyGenerated, regenerateRate);
    }

    @Override
    public String getType() {
        return "Wind";
    }
}
