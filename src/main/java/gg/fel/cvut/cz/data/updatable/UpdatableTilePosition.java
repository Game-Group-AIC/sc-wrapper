package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.facades.AUpdaterFacade;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public class UpdatableTilePosition extends TilePosition implements IUpdatableContainer<bwapi.TilePosition> {
    private transient final WTilePosition wrapped;

    @Override
    public bwapi.TilePosition getSCInstance() {
        return wrapped.getScInstance();
    }

    @Override
    public Stream<? extends AContainer> update(AUpdaterFacade updaterFacade) {
        //TODO set fields + lock
        return null;
    }
}
