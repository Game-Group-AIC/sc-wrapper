package gg.fel.cvut.cz.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyRegisterTest {

    @Test
    public void testPropertyRegister() {
        String value = "Hello";
        DynamicPropertyRegister<String> dynamicPropertyRegister = new DynamicPropertyRegister<>();
        assertTrue(!dynamicPropertyRegister.getLatestValue().isPresent());
        try {
            dynamicPropertyRegister.addProperty(value, 10);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
        assertTrue(dynamicPropertyRegister.getLatestValue().isPresent());
        assertTrue(!dynamicPropertyRegister.getValueInFrame(9).isPresent());
        assertTrue(dynamicPropertyRegister.getValueInFrame(10).isPresent());
        try {
            dynamicPropertyRegister.addProperty(value, 12);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
        assertTrue(dynamicPropertyRegister.getValueInFrame(12).isPresent());
        assertEquals(dynamicPropertyRegister.getValueInFrame(12).get(), value);
        try {
            dynamicPropertyRegister.addProperty(value, 10);
            fail("Should have thrown any exception");
        } catch (Exception ignored) {
        }
        assertTrue(dynamicPropertyRegister.getValueInFrame(14).isPresent());
        assertEquals(dynamicPropertyRegister.getValueInFrame(14).get(), value);
    }

}
