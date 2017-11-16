package gg.fel.cvut.cz.data;

import com.google.common.collect.Iterables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manages property by tracking its value trough the time
 *
 * @param <T>
 */
public class PropertyRegister<T extends Serializable> implements Serializable {
    private final List<Property<T>> propertyTimeline = new ArrayList<>();
    private final List<Integer> timelineWithReferenceToProperty = new ArrayList<>();

    public synchronized void addProperty(T propertyValue, int inFrame) throws IllegalAccessException {

        //altering timeline
        if (timelineWithReferenceToProperty.size() > inFrame) {
            throw new IllegalAccessException("The timeline can not be altered.");
        }
        //extending timeline
        else {

            //fill timeline with time steps and references
            for (int i = timelineWithReferenceToProperty.size(); i < inFrame; i++) {
                timelineWithReferenceToProperty.add(propertyTimeline.size() - 1);
            }

            //do we have new value?
            if (propertyTimeline.isEmpty() || !Iterables.getLast(propertyTimeline).hasSameValue(propertyValue)) {
                propertyTimeline.add(new Property<>(propertyValue));
            }

            //add current
            timelineWithReferenceToProperty.add(propertyTimeline.size() - 1);
        }
    }

    public Optional<Property<T>> getLatestValue() {
        if (propertyTimeline.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(Iterables.getLast(propertyTimeline));
    }

    public Optional<Property<T>> getValueInFrame(int frame) {
        if (timelineWithReferenceToProperty.size() <= frame) {
            return getLatestValue();
        }
        int index = timelineWithReferenceToProperty.get(frame);
        if (index < 0) {
            return Optional.empty();
        }
        return Optional.of(propertyTimeline.get(index));
    }

}
