package org.wahlzeit.model;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodManagerTest {

    @Before
    public void clearFoodManager() {
        FoodManager.getInstance().clear();
    }

    @Test
    public void testGetFoodType() {
        FoodType type1 = FoodManager.getInstance().getFoodType("BBQ");
        FoodType type2 = FoodManager.getInstance().getFoodType("Vietnamese");
        assertEquals(2, FoodManager.getInstance().foodTypeMap.size());

        FoodType type1Copy = FoodManager.getInstance().getFoodType("BBQ");
        assertSame(type1, type1Copy);
    }

    @Test
    public void testCreateFood() {
        Food bbq1 = FoodManager.getInstance().createFood("BBQ");
        Food bbq2 = FoodManager.getInstance().createFood("BBQ");

        assertNotEquals(bbq1.getId(), bbq2.getId());

        Food vietnamese1 = FoodManager.getInstance().createFood("Vietnamese");

        assertEquals(2, FoodManager.getInstance().foodTypeMap.size());
        assertEquals(3, FoodManager.getInstance().foodMap.size());
    }

    @Test
    public void testAsSubtype() {
        //Nicht subtype von sichselbst
        assertFalse(FoodManager.getInstance().setAsSubtype("BBQ", "BBQ"));
        //Mehrere subtypes klappt
        assertTrue(FoodManager.getInstance().setAsSubtype("BBQ", "Steak"));
        assertTrue(FoodManager.getInstance().setAsSubtype("BBQ", "Ribs"));
        //Keinen Zyklus
        assertFalse(FoodManager.getInstance().setAsSubtype("Ribs", "BBQ"));
    }
}
