package gg.fel.cvut.cz.api;

import java.util.List;

public interface BaseLocation extends AbstractPoint {

    Position getPosition();

    TilePosition getTilePosition();

    Region getRegion();

    int minerals();

    int gas();

    List<Unit> getMinerals();

    List<Unit> getStaticMinerals();

    List<Unit> getGeysers();

    double getGroundDistance(BaseLocation other);

    double getAirDistance(BaseLocation other);

    boolean isIsland();

    boolean isMineralOnly();

    boolean isStartLocation();

}
