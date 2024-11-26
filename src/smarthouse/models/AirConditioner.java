package smarthouse.models;

public class AirConditioner extends Appliance {
    public AirConditioner(String name, double energyConsumption) {
        super(name, energyConsumption);
    }

    @Override
    public String getType() {
        return "Air Conditioner";
    }
}
