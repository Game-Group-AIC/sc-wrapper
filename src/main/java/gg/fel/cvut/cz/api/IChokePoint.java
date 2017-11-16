package gg.fel.cvut.cz.api;

import bwapi.Pair;

public interface IChokePoint extends IAbstractPoint {

    Pair<IRegion, IRegion> getRegions();

    Pair<IPosition, IPosition> getSides();

    IPosition getCenter();

    double getWidth();

}
