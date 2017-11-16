package gg.fel.cvut.cz.api;

/**
 * Common ancestor for location based objects to simplify distance computation.
 */
public interface IAbstractPoint {

    int getX();

    int getY();

    default double getDistance(IAbstractPoint other) {
        double dx = other.getX() - getX();
        double dy = other.getY() - getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    boolean isValid();

    int getApproxDistance(IAbstractPoint position);

    double getLength();

    IChokePoint getNearestChokePoint();

    IBaseLocation getNearestBaseLocation();

    IRegion getRegion();

    /**
     * Returns X coordinate in tiles
     */
    default int getTileX() {
        return getX() / ITilePosition.SIZE_IN_PIXELS;
    }

    /**
     * Returns Y coordinate in tiles
     */
    default int getTileY() {
        return getY() / ITilePosition.SIZE_IN_PIXELS;
    }

}