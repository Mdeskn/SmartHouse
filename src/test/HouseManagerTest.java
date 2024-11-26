package test;

import org.junit.jupiter.api.Test;
import smarthouse.controllers.ApplianceController;
import smarthouse.controllers.EnergyController;
import smarthouse.controllers.HouseManager;
import smarthouse.models.Light;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HouseManagerTest {

    @Test
    void testSimulationLifecycle() {
        // Create dependencies
        ApplianceController applianceController = new ApplianceController(new ArrayList<>());
        EnergyController energyController = new EnergyController(new ArrayList<>());

        // Add dummy appliances and energy sources
        Light light = new Light("Living Room Light", 60);
        applianceController.addAppliance(light);

        // Add a SolarSource using the correct method
        energyController.addEnergySource("Solar Panel", 500, 50);

        // Create HouseManager
        HouseManager houseManager = new HouseManager(applianceController, energyController);

        // Ensure simulation is not running initially
        assertFalse(isSimulationRunning(houseManager), "Simulation should not be running initially.");

        // Start simulation
        houseManager.startSimulation();
        assertTrue(isSimulationRunning(houseManager), "Simulation should be running after starting.");

        // Stop simulation
        houseManager.stopSimulation();
        assertFalse(isSimulationRunning(houseManager), "Simulation should not be running after stopping.");
    }

    /**
     * Reflection utility to access private simulationRunning field.
     */
    private boolean isSimulationRunning(HouseManager houseManager) {
        try {
            var field = HouseManager.class.getDeclaredField("simulationRunning");
            field.setAccessible(true);
            return field.getBoolean(houseManager);
        } catch (Exception e) {
            fail("Unable to access simulationRunning field: " + e.getMessage());
            return false; // Unreachable
        }
    }
}
