package gg.fel.cvut.cz.data;

import java.io.Serializable;
import java.util.Optional;

/**
 * Manages dynamic property by tracking its value trough the time
 *
 * @param <T>
 */
public class StaticPropertyRegister<T extends Serializable> implements IPropertyRegister<T>, Serializable {
    private Property<T> property = null;
    private Integer timeOfCreation = null;

    public synchronized void addProperty(T propertyValue, int inFrame) throws IllegalAccessException {

        //changing property
        if (property != null) {
            throw new IllegalAccessException("Changing property which is suppose to be constant.");
        }
        this.timeOfCreation = inFrame;
        this.property = new Property<>(propertyValue);
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
        return property.equals(that.property) && timeOfCreation.equals(that.timeOfCreation);
    }

    @Override
    public int hashCode() {
        int result = property.hashCode();
        result = 31 * result + timeOfCreation.hashCode();
        return result;
    }
}
