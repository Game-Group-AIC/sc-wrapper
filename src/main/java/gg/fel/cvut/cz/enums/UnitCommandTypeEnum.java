package gg.fel.cvut.cz.enums;

import bwapi.UnitCommandType;
import java.util.List;

/**
 * A representation of a unit command in BWAPI. This is used by bots to notifySubscriber BWAPI which commands
 * to use. BWAPI filters commands accordingly and then converts them to Broodwar commands, which
 * differ in complexity. See also UnitCommandTypes
 */
public enum UnitCommandTypeEnum implements IGameTypes<UnitCommandType, UnitCommandTypeEnum> {
  AttackMove,
  AttackUnit,
  Build,
  BuildAddon,
  Train,
  Morph,
  Research,
  Upgrade,
  SetRallyPosition,
  SetRallyUnit,
  Move,
  Patrol,
  HoldPosition,
  Stop,
  Follow,
  Gather,
  ReturnCargo,
  Repair,
  Burrow,
  Unburrow,
  Cloak,
  Decloak,
  Siege,
  Unsiege,
  Lift,
  Land,
  Load,
  Unload,
  UnloadAll,
  UnloadAllPosition,
  RightClickPosition,
  RightClickUnit,
  HaltConstruction,
  CancelConstruction,
  CancelAddon,
  CancelTrain,
  CancelTrainSlot,
  CancelMorph,
  CancelResearch,
  CancelUpgrade,
  UseTech,
  UseTechPosition,
  UseTechUnit,
  PlaceCOP,
  None,
  Unknown;

  @Override
  public List<UnitCommandType> getTypes() {
    return UNIT_COMMAND_TYPES;
  }

  @Override
  public UnitCommandTypeEnum[] getValues() {
    return UnitCommandTypeEnum.values();
  }
}
