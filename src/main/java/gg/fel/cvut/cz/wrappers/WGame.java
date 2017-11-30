package gg.fel.cvut.cz.wrappers;

import bwapi.Game;

//There is no way to distinguish between games... :(
public class WGame extends WrapperForClassWithID<Game> {

  private WGame(Game scInstance) {
    super(scInstance, 0);
  }

  public static WGame getOrCreateWrapper(Game game) {
    return new WGame(game);
  }
}
