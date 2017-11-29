package gg.fel.cvut.cz.data;

import static bwapi.UpgradeType.Terran_Infantry_Armor;
import static bwapi.WeaponType.Gauss_Rifle;
import static bwapi.WeaponType.Maelstrom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import gg.fel.cvut.cz.wrappers.WUpgradeType;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import org.junit.Test;

public class WTypeTest {

  @Test
  public void testWTypeProperty() {
    WWeaponType gaussRifle = WWeaponType.getOrCreateWrapper(Gauss_Rifle);
    WWeaponType maelstrom = WWeaponType.getOrCreateWrapper(Maelstrom);
    assertNotEquals(gaussRifle, maelstrom);
    assertEquals(gaussRifle, WWeaponType.getOrCreateWrapper(Gauss_Rifle));
  }

  @Test
  public void testWTypesProperty() {
    WWeaponType gaussRifle = WWeaponType.getOrCreateWrapper(Gauss_Rifle);
    WUpgradeType terranInfantryArmor = WUpgradeType.getOrCreateWrapper(Terran_Infantry_Armor);
    assertNotEquals(gaussRifle, terranInfantryArmor);
  }

}
