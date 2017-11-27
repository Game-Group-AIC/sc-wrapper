package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.facades.IInternalUpdaterFacade;
import gg.fel.cvut.cz.facades.UpdateStrategy;
import gg.fel.cvut.cz.facades.UpdaterFacade;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public class UpdatableBaseLocation extends BaseLocation implements IUpdatableContainer<WBaseLocation, BaseLocation> {
    private final transient WBaseLocation wrapped;

    @Override
    public WBaseLocation getWrappedSCInstance() {
        return wrapped;
    }

    @Override
    public Stream<? extends AContainer> update(UpdaterFacade updaterFacade) {
        //TODO set fields + lock
        return null;
    }

    @Override
    public BaseLocation getContainer() {
        return this;
    }

    @Override
    public boolean shouldBeUpdated(UpdateStrategy updateStrategy, IInternalUpdaterFacade internalUpdaterFacade, int depth) {
        return updateStrategy.shouldBeUpdated(this, internalUpdaterFacade.getDeltaUpdate(this), depth);
    }

    @Override
    public void update(UpdateStrategy updateStrategy, IInternalUpdaterFacade internalUpdaterFacade, int depth, int currentFrame) {
        internalUpdaterFacade.update(this, updateStrategy, depth, currentFrame);
    }
}
