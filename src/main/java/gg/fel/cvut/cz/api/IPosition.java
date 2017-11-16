package gg.fel.cvut.cz.api;

/**
 * Positions are measured in pixels and are the highest resolution.
 */
public interface IPosition extends IAbstractPoint {
    IPosition makeValid();

    ITilePosition toTilePosition();
}