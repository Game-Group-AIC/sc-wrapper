package gg.fel.cvut.cz.facades.data;

import gg.fel.cvut.cz.api.Tuple;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Manage update tree from object
 */
public class UpdateTreeExecutor {

  private final Queue<Tuple<IUpdatableContainer<?, ?>, Integer>> toUpdate = new LinkedList<>();
  private final Set<IUpdatableContainer<?, ?>> containersScheduledForUpdate = new HashSet<>();
  private final UpdateStrategy updateStrategy;
  private final int currentFrame;
  private final UpdateManager updateManager;

  private UpdateTreeExecutor(UpdateStrategy updateStrategy, int currentFrame,
      UpdateManager updateManager) {
    this.updateStrategy = updateStrategy;
    this.currentFrame = currentFrame;
    this.updateManager = updateManager;
  }

  /**
   * Executes update of given notes and all referenced node we care about - by update strategy
   */
  public static void executeUpdate(IUpdatableContainer<?, ?> fromNode,
      UpdateStrategy updateStrategy,
      int currentFrame, UpdateManager updateManager) {
    if (fromNode.shouldBeUpdated(updateStrategy, 0, currentFrame)) {
      UpdateTreeExecutor updateTreeExecutor = new UpdateTreeExecutor(updateStrategy, currentFrame,
          updateManager);
      updateTreeExecutor.updateTree(fromNode, 0);
      while (!updateTreeExecutor.toUpdate.isEmpty()) {
        updateTreeExecutor.executeUpdate();
      }
    }
  }

  /**
   * Executes single update from queue and then add references to tree
   */
  private void executeUpdate() {
    Tuple<IUpdatableContainer<?, ?>, Integer> tuple = toUpdate.poll();

    //execute update
    tuple.getFirst().get().update(updateManager, currentFrame);

    //check references
    tuple.getFirst().get().getReferencedContainers(currentFrame)
        .filter(o -> o instanceof IUpdatableContainer<?, ?>)
        .forEach(o -> updateTree((IUpdatableContainer<?, ?>) o, tuple.getSecond().get() + 1));
  }

  /**
   * Registers container for update. We traverse graph of dependencies using DFS.
   */
  private void updateTree(IUpdatableContainer<?, ?> container, int depth) {

    //check if we have met this container
    if (!containersScheduledForUpdate.contains(container)) {

      //object is not in queue. if it makes sense add it to it
      if (container.shouldBeUpdated(updateStrategy, depth, currentFrame)) {
        toUpdate.add(new Tuple<>(container, depth));
      }

      //we processed it and find its lowest depth relative to root
      containersScheduledForUpdate.add(container);
    }
  }

}
