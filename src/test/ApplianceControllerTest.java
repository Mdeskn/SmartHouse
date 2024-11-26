package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import smarthouse.controllers.ApplianceController;
import smarthouse.models.Light;

class ApplianceControllerTest {
    @Test
    void testAddAppliance() {
        ApplianceController controller = new ApplianceController(new ArrayList<>());
        Light light = new Light("Living Room Light", 60);
        controller.addAppliance(light);

        Assertions.assertEquals(1, controller.getAppliances().size());
        Assertions.assertEquals(light, controller.getAppliances().get(0));
    }

    @Test
    void testRemoveAppliance() {
        ApplianceController controller = new ApplianceController(new ArrayList<>());
        Light light = new Light("Living Room Light", 60);
        controller.addAppliance(light);
        controller.removeAppliance(1);

        Assertions.assertEquals(0, controller.getAppliances().size());
    }
}
