package gg.fel.cvut.cz.data;

import static gg.fel.cvut.cz.enums.UpgradeTypeEnum.TerranInfantryArmor;
import static gg.fel.cvut.cz.enums.WeaponTypeEnum.GaussRifle;
import static gg.fel.cvut.cz.enums.WeaponTypeEnum.Maelstrom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import gg.fel.cvut.cz.wrappers.WUpgradeType;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import org.junit.Test;

public class WTypeTest {

  @Test
  public void testWTypeProperty() {
    WWeaponType gaussRifle = WWeaponType.getOrCreateWrapper(GaussRifle);
    WWeaponType maelstrom = WWeaponType.getOrCreateWrapper(Maelstrom);
    assertNotEquals(gaussRifle, maelstrom);
    assertEquals(gaussRifle, WWeaponType.getOrCreateWrapper(GaussRifle));
  }

  @Test
  public void testWTypesProperty() {
    WWeaponType gaussRifle = WWeaponType.getOrCreateWrapper(GaussRifle);
    WUpgradeType terranInfantryArmor = WUpgradeType.getOrCreateWrapper(TerranInfantryArmor);
    assertNotEquals(gaussRifle, terranInfantryArmor);
  }

}
