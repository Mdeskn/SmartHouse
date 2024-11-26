package smarthouse.models;

public class Television extends Appliance {
    public Television(String name, double energyConsumption) {
        super(name, energyConsumption);
    }

    @Override
    public String getType() {
        return "Television";
    }
}
