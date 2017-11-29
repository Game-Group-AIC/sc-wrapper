package gg.fel.cvut.cz.enums;

/**
 * Represents the type of controller for the player slot (i.e. human, computer). See also
 * PlayerTypes
 */
public enum EPlayerType implements IGameTypes {
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
  Unknown
}
