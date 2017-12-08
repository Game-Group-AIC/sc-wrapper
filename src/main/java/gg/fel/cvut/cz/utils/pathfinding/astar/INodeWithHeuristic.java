package gg.fel.cvut.cz.utils.pathfinding.astar;

import gg.fel.cvut.cz.api.IUnitType;
import java.util.List;

public interface INodeWithHeuristic {
  int getNodeId();
  double getNodeDistance(INodeWithHeuristic another);
  List<INodeWithHeuristic> getNodeNeighbours();
  boolean isNodeNeighbourAcessible(INodeWithHeuristic another, IUnitType byUnitType);
}
