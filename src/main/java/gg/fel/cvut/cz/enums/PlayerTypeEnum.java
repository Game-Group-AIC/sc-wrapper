package gg.fel.cvut.cz.enums;

import bwapi.PlayerType;
import java.util.List;

/**
 * Represents the type of controller for the player slot (i.e. human, computer). See also
 * PlayerTypes
 */
public enum PlayerTypeEnum implements IGameTypes<PlayerType, PlayerTypeEnum> {
  None,
  Computer,
  Player,
  RescuePassive,
  EitherPreferComputer,
  EitherPreferHuman,
  Neutral,
  Closed,
  PlayerLeft,
  ComputerLeft,
  Unknown;


  @Override
  public List<PlayerType> getTypes() {
    return PLAYER_TYPES;
  }

  @Override
  public PlayerTypeEnum[] getValues() {
    return PlayerTypeEnum.values();
  }
}
