package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.counters.IBWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Region;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import java.util.stream.Stream;

/**
 * Contract of UpdaterFacade (updatable methods)
 */
public interface IUpdaterFacade extends IBWCounter {

  /**
   * Update bullet
   */
  void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy);

  /**
   * Update baseLocation
   */
  void update(BaseLocation baseLocation, UpdateStrategy updateStrategy);

  /**
   * Update chokePoint
   */
  void update(ChokePoint chokePoint, UpdateStrategy updateStrategy);

  /**
   * Update position
   */
  void update(Position position, UpdateStrategy updateStrategy);

  /**
   * Update region
   */
  void update(Region region, UpdateStrategy updateStrategy);

  /**
   * Update tilePosition
   */
  void update(TilePosition tilePosition, UpdateStrategy updateStrategy);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Bullet bullet);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(BaseLocation baseLocation);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(ChokePoint chokePoint);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Position position);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Region region);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(TilePosition tilePosition);

  /**
   * Update bullet
   */
  void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy, int depth, int currentFrame);

  /**
   * Update position
   */
  void update(Position position, UpdateStrategy updateStrategy, int depth, int currentFrame);

  /**
   * Update baseLocation
   */
  void update(TilePosition tilePosition, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Update baseLocation
   */
  void update(BaseLocation baseLocation, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns are containers associated with this updater
   */
  Stream<? extends AContainer> getAllContainers();

}
