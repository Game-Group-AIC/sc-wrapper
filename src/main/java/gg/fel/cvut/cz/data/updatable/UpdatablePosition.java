package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.facades.AUpdaterFacade;
import gg.fel.cvut.cz.wrappers.WPosition;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public class UpdatablePosition extends Position implements IUpdatableContainer<bwapi.Position> {
    private final transient WPosition wrapped;

    @Override
    public bwapi.Position getSCInstance() {
        return wrapped.getScInstance();
    }

    @Override
    public Stream<? extends AContainer> update(AUpdaterFacade updaterFacade) {
        //TODO set fields + lock
        return null;
    }
}
