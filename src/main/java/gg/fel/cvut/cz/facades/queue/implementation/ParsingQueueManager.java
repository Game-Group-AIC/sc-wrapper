package gg.fel.cvut.cz.facades.queue.implementation;

import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.facades.IQueueManager;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.queue.Command;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Queue manager for parsing. It only parses the game.
 */
@Slf4j
@AllArgsConstructor
public class ParsingQueueManager implements IQueueManager {

  private final UpdateManager updateManager;
  private final UpdateStrategy updateStrategy = UpdateStrategy.builder().build();

  @Override
  public void executeCommands(long timeResources) {
    updateManager.getAllContainers()
        .filter(o -> o instanceof IUpdatableContainer<?, ?>)
        .forEach(o -> ((IUpdatableContainer<?, ?>) o).update(updateManager, updateStrategy));
  }

  @Override
  public void addCommand(Command command) {
    //EMPTY
  }
}
