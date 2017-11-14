package gg.fel.cvut.cz.api;

/**
 * Common ancestor for location based objects to simplify distance computation.
 */
public interface AbstractPoint {

    int getX();

    int getY();

    default double getDistance(AbstractPoint other) {
        double dx = other.getX() - getX();
        double dy = other.getY() - getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    boolean isValid();

    int getApproxDistance(AbstractPoint position);

    double getLength();

    Chokepoint getNearestChokepoint();

    BaseLocation getNearestBaseLocation();

    Region getRegion();

}