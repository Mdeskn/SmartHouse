package test;

import org.junit.jupiter.api.Test;
import smarthouse.controllers.EnergyController;
import smarthouse.models.EnergySource;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EnergyControllerTest {

    @Test
    void testAddEnergySource() {
        EnergyController controller = new EnergyController(new ArrayList<>());

        // Add a SolarSource energy source
        controller.addEnergySource("SolarSource", 500, 50);

        // Validate that the energy source was added successfully
        EnergySource addedSource = controller.getEnergySources().get(0);
        assertEquals("SolarSource", addedSource.getName());
        assertEquals(500, addedSource.getEnergyGenerated(), 0.001);
    }

    @Test
    void testRemoveEnergySource() {
        EnergyController controller = new EnergyController(new ArrayList<>());
        controller.addEnergySource("SolarSource", 500, 50);

        // Remove the energy source
        controller.removeEnergySource(1);

        // Validate that the energy source was removed successfully
        assertTrue(controller.getEnergySources().isEmpty());
    }

    @Test
    void testTurnOnEnergySource() {
        EnergyController controller = new EnergyController(new ArrayList<>());
        controller.addEnergySource("SolarSource", 500, 50);

        // Turn on the energy source
        controller.turnOnEnergySource(1);

        // Validate that the energy source is turned on
        EnergySource source = controller.getEnergySources().get(0);
        assertTrue(source.isOn());
    }

    @Test
    void testTurnOffEnergySource() {
        EnergyController controller = new EnergyController(new ArrayList<>());
        controller.addEnergySource("SolarSource", 500, 50);

        // Turn on and then turn off the energy source
        controller.turnOnEnergySource(1);
        controller.turnOffEnergySource(1);

        // Validate that the energy source is turned off
        EnergySource source = controller.getEnergySources().get(0);
        assertFalse(source.isOn());
    }
}

