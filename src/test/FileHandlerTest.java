package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import smarthouse.utils.FileHandler;
import smarthouse.models.Appliance;
import smarthouse.models.EnergySource;
import java.util.List;

class FileHandlerTest {
    @Test
    void testLoadEnergySources() {
        List<EnergySource> energySources = FileHandler.loadEnergySources();
        Assertions.assertNotNull(energySources);
        Assertions.assertFalse(energySources.isEmpty());
    }

    @Test
    void testLoadAppliances() {
        List<Appliance> appliances = FileHandler.loadAppliances();
        Assertions.assertNotNull(appliances);
        Assertions.assertFalse(appliances.isEmpty());
    }
}
