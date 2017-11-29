package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.EUpgradeType;
import java.io.Serializable;
import java.util.Optional;

/**
 * The upgrade type represents a passive upgrade that can be obtained with UnitInterface::upgrade.
 * See also UpgradeTypes
 */
public interface IUpgradeType extends InGameInterface, Serializable {

  EUpgradeType getUpgradeType();

  /**
   * Retrieves the race the upgrade is for. For example, UpgradeTypes::Terran_Infantry_Armor.getRace()
   * will return Races::Terran. Returns IRace that this upgrade belongs to.
   */
  IRace getRace();

  /**
   * Returns the mineral price for the upgrade. Parameters level (optional) The next upgrade level.
   * Note Upgrades start at level 0. Returns The mineral cost of the upgrade for the given level.
   */
  Optional<Integer> mineralPrice();

  Optional<Integer> mineralPrice(int level);

  /**
   * The amount that the mineral price increases for each additional upgrade. Returns The mineral
   * cost added to the upgrade after each level.
   */
  Optional<Integer> mineralPriceFactor();

  /**
   * Returns the vespene gas price for the first upgrade. Parameters level (optional) The next
   * upgrade level. Note Upgrades start at level 0. Returns The gas cost of the upgrade for the
   * given level.
   */
  Optional<Integer> gasPrice();

  Optional<Integer> gasPrice(int level);

  /**
   * Returns the amount that the vespene gas price increases for each additional upgrade. Returns
   * The gas cost added to the upgrade after each level.
   */
  Optional<Integer> gasPriceFactor();

  /**
   * Returns the number of frames needed to research the first upgrade. Parameters level (optional)
   * The next upgrade level. Note Upgrades start at level 0. Returns The time cost of the upgrade
   * for the given level.
   */
  Optional<Integer> upgradeTime();

  Optional<Integer> upgradeTime(int level);

  /**
   * Returns the maximum number of times the upgrade can be researched. Returns Maximum number of
   * times this upgrade can be upgraded.
   */
  Optional<Integer> maxRepeats();

  /**
   * Returns the type of unit that researches the upgrade. Returns The IUnitType that is used to
   * upgrade this type.
   */
  Optional<IUnitType> whatUpgrades();

  /**
   * Returns the type of unit that is required for the upgrade. The player must have at least one of
   * these units completed in order to start upgrading this upgrade. Parameters level (optional) The
   * next upgrade level. Note Upgrades start at level 0. Returns IUnitType required to obtain this
   * upgrade.
   */
  Optional<IUnitType> whatsRequired();

  Optional<IUnitType> whatsRequired(int level);

}
