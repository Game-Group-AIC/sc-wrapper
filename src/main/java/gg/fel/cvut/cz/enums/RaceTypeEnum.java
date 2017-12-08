package gg.fel.cvut.cz.enums;

import bwapi.Race;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public enum RaceTypeEnum implements IGameTypes<Race, RaceTypeEnum> {
  Zerg,
  Terran,
  Protoss,
  Random,
  None,
  Unknown;

  @Override
  public List<Race> getTypes() {
    return RACES;
  }

  @Override
  public RaceTypeEnum[] getValues() {
    return RaceTypeEnum.values();
  }

  final private static HashMap<RaceTypeEnum, HashSet<TechTypeEnum>> techTypes = new HashMap<>();
  static {
    HashSet<TechTypeEnum> zergTech = new HashSet<>();
    zergTech.add(TechTypeEnum.Burrowing);
    zergTech.add(TechTypeEnum.Infestation);
    zergTech.add(TechTypeEnum.SpawnBroodlings);
    zergTech.add(TechTypeEnum.DarkSwarm);
    zergTech.add(TechTypeEnum.Plague);
    zergTech.add(TechTypeEnum.Consume);
    zergTech.add(TechTypeEnum.Ensnare);
    zergTech.add(TechTypeEnum.Parasite);
    zergTech.add(TechTypeEnum.LurkerAspect);
    techTypes.put(Zerg, zergTech);

    HashSet<TechTypeEnum> terranTech = new HashSet<>();
    terranTech.add(TechTypeEnum.StimPacks);
    terranTech.add(TechTypeEnum.Lockdown);
    terranTech.add(TechTypeEnum.EMPShockwave);
    terranTech.add(TechTypeEnum.SpiderMines);
    terranTech.add(TechTypeEnum.ScannerSweep);
    terranTech.add(TechTypeEnum.TankSiegeMode);
    terranTech.add(TechTypeEnum.DefensiveMatrix);
    terranTech.add(TechTypeEnum.Irradiate);
    terranTech.add(TechTypeEnum.YamatoGun);
    terranTech.add(TechTypeEnum.CloakingField);
    terranTech.add(TechTypeEnum.PersonnelCloaking);
    terranTech.add(TechTypeEnum.Restoration);
    terranTech.add(TechTypeEnum.OpticalFlare);
    terranTech.add(TechTypeEnum.Healing);
    terranTech.add(TechTypeEnum.NuclearStrike);
    techTypes.put(Terran, terranTech);

    HashSet<TechTypeEnum> protossTech= new HashSet<>();
    protossTech.add(TechTypeEnum.PsionicStorm);
    protossTech.add(TechTypeEnum.Hallucination);
    protossTech.add(TechTypeEnum.Recall);
    protossTech.add(TechTypeEnum.StasisField);
    protossTech.add(TechTypeEnum.ArchonWarp);
    protossTech.add(TechTypeEnum.DisruptionWeb);
    protossTech.add(TechTypeEnum.MindControl);
    protossTech.add(TechTypeEnum.DarkArchonMeld);
    protossTech.add(TechTypeEnum.Feedback);
    protossTech.add(TechTypeEnum.Maelstrom);
    techTypes.put(Protoss, protossTech);
  }

  public static HashSet<TechTypeEnum> getTechType(RaceTypeEnum race) {
    return techTypes.get(race);
  }
}
