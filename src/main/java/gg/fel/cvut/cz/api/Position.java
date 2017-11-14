package gg.fel.cvut.cz.api;

/**
 * Positions are measured in pixels and are the highest resolution.
 */
public interface Position extends AbstractPoint {
    Position makeValid();

    TilePosition toTilePosition();
}