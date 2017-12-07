package gg.fel.cvut.cz.enums;

import bwapi.GameType;
import java.util.List;

/**
 * A class that represents game types in Broodwar. A game type is selected when creating a game. See
 * also GameTypes
 */
public enum GameTypeEnum implements IGameTypes<GameType, GameTypeEnum> {
  Melee,
  FreeForAll,
  OneOnOne,
  CaptureTheFlag,
  Greed,
  Slaughter,
  SuddenDeath,
  Ladder,
  UseMapSettings,
  TeamMelee,
  TeamFreeForAll,
  TeamCaptureTheFlag,
  TopvsBottom,
  None,
  Unknown;

  @Override
  public List<GameType> getTypes() {
    return GAME_TYPES;
  }

  @Override
  public GameTypeEnum[] getValues() {
    return GameTypeEnum.values();
  }
}
