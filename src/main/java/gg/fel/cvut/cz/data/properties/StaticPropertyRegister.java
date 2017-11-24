package gg.fel.cvut.cz.data.properties;

import gg.fel.cvut.cz.data.IPropertyRegister;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Optional;

/**
 * Manages dynamic property by tracking its value trough the time
 *
 * @param <T>
 */
@Slf4j
public class StaticPropertyRegister<T extends Serializable> implements IPropertyRegister<T>, Serializable {
    private Property<T> property = null;
    private Integer timeOfCreation = null;

    public void addProperty(T propertyValue, int inFrame) {

        //changing property
        if (property != null) {
            log.error("Changing property which is suppose to be constant.");
        } else {
            this.timeOfCreation = inFrame;
            this.property = new Property<>(propertyValue);
        }
    }

    public Optional<T> getLatestValue() {
        return Optional.ofNullable(property).map(Property::getValue);
    }

    public Optional<T> getValueInFrame(int frame) {
        if (timeOfCreation == null || frame < timeOfCreation) {
            return Optional.empty();
        }
        return getLatestValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaticPropertyRegister<?> that = (StaticPropertyRegister<?>) o;

        return (property != null ? property.equals(that.property) : that.property == null)
                && (timeOfCreation != null ? timeOfCreation.equals(that.timeOfCreation) : that.timeOfCreation == null);
    }

    @Override
    public int hashCode() {
        int result = property != null ? property.hashCode() : 0;
        result = 31 * result + (timeOfCreation != null ? timeOfCreation.hashCode() : 0);
        return result;
    }
}
