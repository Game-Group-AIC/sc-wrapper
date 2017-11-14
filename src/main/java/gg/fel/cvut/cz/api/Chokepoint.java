package gg.fel.cvut.cz.api;

public interface Chokepoint extends AbstractPoint {

    Pair<Region, Region> getRegions();

    Pair<Position, Position> getSides();

    Position getCenter();

    double getWidth();

}
