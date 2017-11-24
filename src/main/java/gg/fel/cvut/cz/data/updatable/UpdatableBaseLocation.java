package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.facades.AUpdaterFacade;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public class UpdatableBaseLocation extends BaseLocation implements IUpdatableContainer<bwta.BaseLocation> {
    private final transient WBaseLocation wrapped;

    @Override
    public bwta.BaseLocation getSCInstance() {
        return wrapped.getScInstance();
    }

    @Override
    public Stream<? extends AContainer> update(AUpdaterFacade updaterFacade) {
        //TODO set fields + lock
        return null;
    }

}
