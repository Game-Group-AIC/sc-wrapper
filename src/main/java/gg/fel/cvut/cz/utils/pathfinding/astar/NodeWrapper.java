package gg.fel.cvut.cz.utils.pathfinding.astar;

import java.util.List;

final class NodeWrapper<T extends INodeWithHeuristic> {
  private final T node;

  private double g;  // g is distance from the source
  private double h;  // h is the heuristic of destination.
  private double f;  // f = g + h 

  NodeWrapper(T node) {
    this.node = node;
    this.g = Double.MAX_VALUE;
  }

  private int getNodeId() {
    return node.getNodeId();
  }

  T getNode() {
    return node;
  }

  double getG() {
    return g;
  }

  void setG(double g) {
    this.g = g;
  }

  void calcF(T destination) {
    this.h = node.getNodeDistance(destination);
    this.f = g + h;
  }

  double getH() {
    return h;
  }

  double getF() {
    return f;
  }

  List<INodeWithHeuristic> getNeighbours() {
    return node.getNodeNeighbours();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    NodeWrapper<?> that = (NodeWrapper<?>) o;

    return node.getNodeId() == that.getNodeId();
  }

  @Override
  public int hashCode() {
    return node.getNodeId();
  }
}
