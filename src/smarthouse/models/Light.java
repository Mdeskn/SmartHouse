package smarthouse.models;

public class Light extends Appliance {
    public Light(String name, double energyConsumption) {
        super(name, energyConsumption);
    }

    @Override
    public String getType() {
        return "Light";
    }
}
