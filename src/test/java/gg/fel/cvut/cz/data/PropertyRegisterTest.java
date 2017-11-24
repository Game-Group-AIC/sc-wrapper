package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropertyRegisterTest {

    @Test
    public void testPropertyRegister() {
        String value = "Hello";
        DynamicPropertyRegister<String> dynamicPropertyRegister = new DynamicPropertyRegister<>();
        assertTrue(!dynamicPropertyRegister.getLatestValue().isPresent());
        dynamicPropertyRegister.addProperty(value, 10);
        assertTrue(dynamicPropertyRegister.getLatestValue().isPresent());
        assertTrue(!dynamicPropertyRegister.getValueInFrame(9).isPresent());
        assertTrue(dynamicPropertyRegister.getValueInFrame(10).isPresent());
        dynamicPropertyRegister.addProperty(value, 12);
        assertTrue(dynamicPropertyRegister.getValueInFrame(12).isPresent());
        assertEquals(dynamicPropertyRegister.getValueInFrame(12).get(), value);
        assertTrue(dynamicPropertyRegister.getValueInFrame(14).isPresent());
        assertEquals(dynamicPropertyRegister.getValueInFrame(14).get(), value);
    }

}
