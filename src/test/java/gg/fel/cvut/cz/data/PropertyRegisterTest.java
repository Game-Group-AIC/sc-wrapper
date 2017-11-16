package gg.fel.cvut.cz.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyRegisterTest {

    @Test
    public void testPropertyRegister() {
        String value = "Hello";
        PropertyRegister<String> propertyRegister = new PropertyRegister<>();
        assertTrue(!propertyRegister.getLatestValue().isPresent());
        try {
            propertyRegister.addProperty(value, 10);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
        assertTrue(propertyRegister.getLatestValue().isPresent());
        assertTrue(!propertyRegister.getValueInFrame(9).isPresent());
        assertTrue(propertyRegister.getValueInFrame(10).isPresent());
        try {
            propertyRegister.addProperty(value, 12);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
        assertTrue(propertyRegister.getValueInFrame(12).isPresent());
        assertEquals(propertyRegister.getValueInFrame(12).get().getValue(), value);
        try {
            propertyRegister.addProperty(value, 10);
            fail("Should have thrown any exception");
        } catch (Exception ignored) {
        }
        assertTrue(propertyRegister.getValueInFrame(14).isPresent());
        assertEquals(propertyRegister.getValueInFrame(14).get().getValue(), value);
    }

}
