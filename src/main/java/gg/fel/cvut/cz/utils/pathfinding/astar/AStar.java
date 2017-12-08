package gg.fel.cvut.cz.utils.pathfinding.astar;

import gg.fel.cvut.cz.data.readonly.UnitType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStar {

  /**
   * @return the path of nodes ordered from source to destination
   * (including source and destination)
   */
  public static Optional<List<INodeWithHeuristic>> findPath(
      UnitType type,
      INodeWithHeuristic sourceNode,
      INodeWithHeuristic destinationNode) {

    return findPath(type, sourceNode, destinationNode, new HashSet<>());
  }

  /**
   * @return the path of nodes ordered from source to destination
   * (including source and destination)
   */
  public static Optional<List<INodeWithHeuristic>> findPath(
      UnitType type,
      INodeWithHeuristic sourceNode,
      INodeWithHeuristic destinationNode,
      Set<INodeWithHeuristic> avoidNodes) {

    // fail early
    if(avoidNodes.contains(sourceNode) || avoidNodes.contains(destinationNode)) {
      return Optional.empty();
    }

    // special case early
    if(sourceNode.equals(destinationNode)) {
      ArrayList<INodeWithHeuristic> path = new ArrayList<>();
      path.add(sourceNode);
      return Optional.of(path);
    }

    final Queue<NodeWrapper<INodeWithHeuristic>> openQueue = new PriorityQueue<>(
        11, new NodeComparator());
    final Map<INodeWithHeuristic, INodeWithHeuristic> pathGraph = new HashMap<>();
    final Set<NodeWrapper<INodeWithHeuristic>> closedSet = new HashSet<>();
    // todo: merge closed with path graph

    final Set<NodeWrapper<INodeWithHeuristic>> avoidNodesWrapped = initAvoidWrappedNodes(avoidNodes);

    while (!openQueue.isEmpty()) {
      final NodeWrapper<INodeWithHeuristic> currentNodeWrapped = openQueue.poll();
      final INodeWithHeuristic currentNode = currentNodeWrapped.getNode();

      if (currentNode.equals(destinationNode)) {
        return Optional.of(
            reconstructPath(pathGraph, destinationNode)
        );
      }

      closedSet.add(currentNodeWrapped);

      for (INodeWithHeuristic nextNode : currentNodeWrapped.getNeighbours()) {
        NodeWrapper<INodeWithHeuristic> nextNodeWrapped = new NodeWrapper<>(nextNode);

        if (closedSet.contains(nextNodeWrapped)) {
          continue;
        }
        if (avoidNodesWrapped.contains(nextNodeWrapped)) {
          continue;
        }
        if(!currentNode.isNodeNeighbourAcessible(nextNode, type)) {
          continue;
        }

        double distance = nextNode.getNodeDistance(currentNode);
        double tentativeG = distance + currentNodeWrapped.getG();

        if (tentativeG < nextNodeWrapped.getG()) {
          nextNodeWrapped.setG(tentativeG);
          nextNodeWrapped.calcF(destinationNode);

          pathGraph.put(nextNode, currentNode);
          if (!openQueue.contains(nextNodeWrapped)) {
            openQueue.add(nextNodeWrapped);
          }
        }
      }
    }

    return Optional.empty();
  }

  private static Set<NodeWrapper<INodeWithHeuristic>> initAvoidWrappedNodes(Set<INodeWithHeuristic> avoidNodes) {
    HashSet<NodeWrapper<INodeWithHeuristic>> avoidWrappedNodes = new HashSet<>();
    for (INodeWithHeuristic avoidNode : avoidNodes) {
      avoidWrappedNodes.add(new NodeWrapper<>(avoidNode));
    }
    return avoidWrappedNodes;
  }

  private static List<INodeWithHeuristic> reconstructPath(Map<INodeWithHeuristic, INodeWithHeuristic> pathGraph, INodeWithHeuristic destination) {
    final List<INodeWithHeuristic> pathList = new ArrayList<>();
    pathList.add(destination);

    while (pathGraph.containsKey(destination)) {
      destination = pathGraph.get(destination);
      pathList.add(destination);
    }

    Collections.reverse(pathList);
    return pathList;
  }

  private static class NodeComparator implements Comparator<NodeWrapper<INodeWithHeuristic>> {

    public int compare(NodeWrapper<INodeWithHeuristic> nodeFirst, NodeWrapper<INodeWithHeuristic> nodeSecond) {
      return Double.compare(nodeFirst.getF(), nodeSecond.getF());
    }
  }
}
