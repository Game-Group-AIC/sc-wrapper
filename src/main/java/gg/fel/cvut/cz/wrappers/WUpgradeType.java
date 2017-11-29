package gg.fel.cvut.cz.wrappers;

import static bwapi.UpgradeType.Adrenal_Glands;
import static bwapi.UpgradeType.Anabolic_Synthesis;
import static bwapi.UpgradeType.Antennae;
import static bwapi.UpgradeType.Apial_Sensors;
import static bwapi.UpgradeType.Apollo_Reactor;
import static bwapi.UpgradeType.Argus_Jewel;
import static bwapi.UpgradeType.Argus_Talisman;
import static bwapi.UpgradeType.Caduceus_Reactor;
import static bwapi.UpgradeType.Carrier_Capacity;
import static bwapi.UpgradeType.Charon_Boosters;
import static bwapi.UpgradeType.Chitinous_Plating;
import static bwapi.UpgradeType.Colossus_Reactor;
import static bwapi.UpgradeType.Gamete_Meiosis;
import static bwapi.UpgradeType.Gravitic_Boosters;
import static bwapi.UpgradeType.Gravitic_Drive;
import static bwapi.UpgradeType.Gravitic_Thrusters;
import static bwapi.UpgradeType.Grooved_Spines;
import static bwapi.UpgradeType.Ion_Thrusters;
import static bwapi.UpgradeType.Khaydarin_Amulet;
import static bwapi.UpgradeType.Khaydarin_Core;
import static bwapi.UpgradeType.Leg_Enhancements;
import static bwapi.UpgradeType.Metabolic_Boost;
import static bwapi.UpgradeType.Metasynaptic_Node;
import static bwapi.UpgradeType.Moebius_Reactor;
import static bwapi.UpgradeType.Muscular_Augments;
import static bwapi.UpgradeType.None;
import static bwapi.UpgradeType.Ocular_Implants;
import static bwapi.UpgradeType.Pneumatized_Carapace;
import static bwapi.UpgradeType.Protoss_Air_Armor;
import static bwapi.UpgradeType.Protoss_Air_Weapons;
import static bwapi.UpgradeType.Protoss_Ground_Armor;
import static bwapi.UpgradeType.Protoss_Ground_Weapons;
import static bwapi.UpgradeType.Protoss_Plasma_Shields;
import static bwapi.UpgradeType.Reaver_Capacity;
import static bwapi.UpgradeType.Scarab_Damage;
import static bwapi.UpgradeType.Sensor_Array;
import static bwapi.UpgradeType.Singularity_Charge;
import static bwapi.UpgradeType.Terran_Infantry_Armor;
import static bwapi.UpgradeType.Terran_Infantry_Weapons;
import static bwapi.UpgradeType.Terran_Ship_Plating;
import static bwapi.UpgradeType.Terran_Ship_Weapons;
import static bwapi.UpgradeType.Terran_Vehicle_Plating;
import static bwapi.UpgradeType.Terran_Vehicle_Weapons;
import static bwapi.UpgradeType.Titan_Reactor;
import static bwapi.UpgradeType.U_238_Shells;
import static bwapi.UpgradeType.Unknown;
import static bwapi.UpgradeType.Upgrade_60;
import static bwapi.UpgradeType.Ventral_Sacs;
import static bwapi.UpgradeType.Zerg_Carapace;
import static bwapi.UpgradeType.Zerg_Flyer_Attacks;
import static bwapi.UpgradeType.Zerg_Flyer_Carapace;
import static bwapi.UpgradeType.Zerg_Melee_Attacks;
import static bwapi.UpgradeType.Zerg_Missile_Attacks;

import bwapi.UpgradeType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WUpgradeType extends WrapperForClassWithID<UpgradeType> {

  private final static List<UpgradeType> UPGRADE_TYPES = Arrays
      .asList(Terran_Infantry_Armor, Terran_Vehicle_Plating,
          Terran_Ship_Plating, Terran_Infantry_Weapons, Terran_Vehicle_Weapons, Terran_Ship_Weapons,
          U_238_Shells,
          Ion_Thrusters, Titan_Reactor, Ocular_Implants, Moebius_Reactor, Apollo_Reactor,
          Colossus_Reactor, Caduceus_Reactor,
          Charon_Boosters, Zerg_Carapace, Zerg_Flyer_Carapace, Zerg_Melee_Attacks,
          Zerg_Missile_Attacks, Zerg_Flyer_Attacks,
          Ventral_Sacs, Antennae, Pneumatized_Carapace, Metabolic_Boost, Adrenal_Glands,
          Muscular_Augments, Grooved_Spines,
          Gamete_Meiosis, Metasynaptic_Node, Chitinous_Plating, Anabolic_Synthesis,
          Protoss_Ground_Armor, Protoss_Air_Armor,
          Protoss_Ground_Weapons, Protoss_Air_Weapons, Protoss_Plasma_Shields, Singularity_Charge,
          Leg_Enhancements,
          Scarab_Damage, Reaver_Capacity, Gravitic_Drive, Sensor_Array, Gravitic_Boosters,
          Khaydarin_Amulet,
          Apial_Sensors, Gravitic_Thrusters, Carrier_Capacity, Khaydarin_Core, Argus_Jewel,
          Argus_Talisman, Upgrade_60,
          None, Unknown);
  private static final Map<Key, WUpgradeType> register = new HashMap<>();

  private WUpgradeType(UpgradeType scInstance) {
    super(scInstance, getIndexInList(UPGRADE_TYPES, scInstance));
  }

  public static WUpgradeType getOrCreateWrapper(UpgradeType upgradeType) {
    return getOrCreateWrapper(upgradeType, register,
        scInstance -> new Key(getIndexInList(UPGRADE_TYPES, scInstance)), WUpgradeType::new);
  }

}
