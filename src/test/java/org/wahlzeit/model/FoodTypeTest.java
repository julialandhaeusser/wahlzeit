package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTypeTest {

    @Before
    public void clearFoodManager() {
        FoodManager.getInstance().clear();
    }

    @Test
    public void testIsSubtype() {
        FoodManager.getInstance().setAsSubtype("BBQ", "Steak");
        FoodManager.getInstance().setAsSubtype("BBQ", "Ribs");
        FoodType vietnamese = FoodManager.getInstance().getFoodType("Vietnamese");

        FoodType steak = FoodManager.getInstance().getFoodType("Steak");
        FoodType bbq = FoodManager.getInstance().getFoodType("BBQ");

        assertTrue(steak.isSubtype(bbq));
        assertFalse(bbq.isSubtype(steak));
        assertFalse(vietnamese.isSubtype(bbq));
    }
}
