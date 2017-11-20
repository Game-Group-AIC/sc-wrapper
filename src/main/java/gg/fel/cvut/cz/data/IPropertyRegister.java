package gg.fel.cvut.cz.data;

import java.io.Serializable;
import java.util.Optional;

/**
 * Common interface for property managers - with constant and dynamic property
 *
 * @param <T>
 */
public interface IPropertyRegister<T extends Serializable> extends Serializable {

    void addProperty(T propertyValue, int inFrame) throws IllegalAccessException;

    Optional<T> getLatestValue();

    Optional<T> getValueInFrame(int frame);
}
