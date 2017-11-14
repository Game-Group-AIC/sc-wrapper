package gg.fel.cvut.cz.api;

/**
 * The upgrade type represents a passive upgrade that can be obtained with UnitInterface::upgrade. See also UpgradeTypes
 */
public interface UpgradeType {

    /**
     * Retrieves the race the upgrade is for. For example, UpgradeTypes::Terran_Infantry_Armor.getRace() will return Races::Terran. Returns Race that this upgrade belongs to.
     */
    Race getRace();

    /**
     * Returns the mineral price for the upgrade. Parameters level (optional) The next upgrade level. Note Upgrades start at level 0. Returns The mineral cost of the upgrade for the given level.
     */
    int mineralPrice();

    int mineralPrice(int level);

    /**
     * The amount that the mineral price increases for each additional upgrade. Returns The mineral cost added to the upgrade after each level.
     */
    int mineralPriceFactor();

    /**
     * Returns the vespene gas price for the first upgrade. Parameters level (optional) The next upgrade level. Note Upgrades start at level 0. Returns The gas cost of the upgrade for the given level.
     */
    int gasPrice();

    int gasPrice(int level);

    /**
     * Returns the amount that the vespene gas price increases for each additional upgrade. Returns The gas cost added to the upgrade after each level.
     */
    int gasPriceFactor();

    /**
     * Returns the number of frames needed to research the first upgrade. Parameters level (optional) The next upgrade level. Note Upgrades start at level 0. Returns The time cost of the upgrade for the given level.
     */
    int upgradeTime();

    int upgradeTime(int level);

    /**
     * Returns the number of frames that the upgrade time increases for each additional upgrade. Returns The time cost added to the upgrade after each level.
     */
    int upgradeTimeFactor();

    /**
     * Returns the maximum number of times the upgrade can be researched. Returns Maximum number of times this upgrade can be upgraded.
     */
    int maxRepeats();

    /**
     * Returns the type of unit that researches the upgrade. Returns The UnitType that is used to upgrade this type.
     */
    UnitType whatUpgrades();

    /**
     * Returns the type of unit that is required for the upgrade. The player must have at least one of these units completed in order to start upgrading this upgrade. Parameters level (optional) The next upgrade level. Note Upgrades start at level 0. Returns UnitType required to obtain this upgrade.
     */
    UnitType whatsRequired();

    UnitType whatsRequired(int level);

}
