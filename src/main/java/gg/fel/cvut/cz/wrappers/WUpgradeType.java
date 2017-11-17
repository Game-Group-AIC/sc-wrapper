package gg.fel.cvut.cz.wrappers;

import bwapi.UpgradeType;

import java.util.Arrays;
import java.util.List;

import static bwapi.UpgradeType.*;

public class WUpgradeType extends WrapperForClassWithID<UpgradeType> {
    private final static List<UpgradeType> UPGRADE_TYPES = Arrays.asList(Terran_Infantry_Armor, Terran_Vehicle_Plating,
            Terran_Ship_Plating, Terran_Infantry_Weapons, Terran_Vehicle_Weapons, Terran_Ship_Weapons, U_238_Shells,
            Ion_Thrusters, Titan_Reactor, Ocular_Implants, Moebius_Reactor, Apollo_Reactor, Colossus_Reactor, Caduceus_Reactor,
            Charon_Boosters, Zerg_Carapace, Zerg_Flyer_Carapace, Zerg_Melee_Attacks, Zerg_Missile_Attacks, Zerg_Flyer_Attacks,
            Ventral_Sacs, Antennae, Pneumatized_Carapace, Metabolic_Boost, Adrenal_Glands, Muscular_Augments, Grooved_Spines,
            Gamete_Meiosis, Metasynaptic_Node, Chitinous_Plating, Anabolic_Synthesis, Protoss_Ground_Armor, Protoss_Air_Armor,
            Protoss_Ground_Weapons, Protoss_Air_Weapons, Protoss_Plasma_Shields, Singularity_Charge, Leg_Enhancements,
            Scarab_Damage, Reaver_Capacity, Gravitic_Drive, Sensor_Array, Gravitic_Boosters, Khaydarin_Amulet,
            Apial_Sensors, Gravitic_Thrusters, Carrier_Capacity, Khaydarin_Core, Argus_Jewel, Argus_Talisman, Upgrade_60,
            None, Unknown);

    public WUpgradeType(UpgradeType scInstance) {
        super(scInstance, getIndexInList(UPGRADE_TYPES, scInstance));
    }
}
