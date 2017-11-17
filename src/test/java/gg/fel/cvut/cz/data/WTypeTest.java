package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.wrappers.WUpgradeType;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import org.junit.Test;

import static bwapi.UpgradeType.Terran_Infantry_Armor;
import static bwapi.WeaponType.Gauss_Rifle;
import static bwapi.WeaponType.Maelstrom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WTypeTest {

    @Test
    public void testWTypeProperty() {
        WWeaponType gaussRifle = new WWeaponType(Gauss_Rifle);
        WWeaponType maelstrom = new WWeaponType(Maelstrom);
        assertNotEquals(gaussRifle, maelstrom);
        assertEquals(gaussRifle, new WWeaponType(Gauss_Rifle));
    }

    @Test
    public void testWTypesProperty() {
        WWeaponType gaussRifle = new WWeaponType(Gauss_Rifle);
        WUpgradeType terranInfantryArmor = new WUpgradeType(Terran_Infantry_Armor);
        assertNotEquals(gaussRifle, terranInfantryArmor);
    }

}
