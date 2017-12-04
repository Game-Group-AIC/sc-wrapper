package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IChokePoint;
import gg.fel.cvut.cz.api.IGame;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.GameTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class Game extends AContainer implements IGame, Serializable {

  public Game(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return new HashSet<>();
  }

  @Override
  public Optional<IPlayer> getSelf() {
    return null;
  }

  @Override
  public Optional<Set<IPlayer>> getPlayers() {
    return null;
  }

  @Override
  public Optional<GameTypeEnum> getGameType() {
    return null;
  }

  @Override
  public Optional<Integer> getFrameCount() {
    return null;
  }

  @Override
  public Optional<Integer> getFPS() {
    return null;
  }

  @Override
  public Optional<Double> getAverageFPS() {
    return null;
  }

  @Override
  public Optional<Integer> elapsedTime() {
    return null;
  }

  @Override
  public Optional<Set<IRegion>> getRegions() {
    return null;
  }

  @Override
  public Optional<Set<IChokePoint>> getChokePoints() {
    return null;
  }

  @Override
  public Optional<Set<IBaseLocation>> getBaseLocations() {
    return null;
  }

  @Override
  public Optional<Set<IBaseLocation>> getStartLocations() {
    return null;
  }

  @Override
  public Optional<Integer> mapWidth() {
    return null;
  }

  @Override
  public Optional<Integer> mapHeight() {
    return null;
  }

  @Override
  public Optional<String> mapName() {
    return null;
  }

  @Override
  public Optional<Set<ITilePosition>> getGrid() {
    return Optional.empty();
  }

}
