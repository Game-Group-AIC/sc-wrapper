package gg.fel.cvut.cz.api;

import java.util.List;

public interface IBaseLocation extends IAbstractPoint {

    IPosition getPosition();

    ITilePosition getTilePosition();

    IRegion getRegion();

    int minerals();

    int gas();

    List<IUnit> getMinerals();

    List<IUnit> getStaticMinerals();

    List<IUnit> getGeysers();

    double getGroundDistance(IBaseLocation other);

    double getAirDistance(IBaseLocation other);

    boolean isIsland();

    boolean isMineralOnly();

    boolean isStartLocation();

}
