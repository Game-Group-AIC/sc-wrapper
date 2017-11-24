package gg.fel.cvut.cz.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Optional;

/**
 * Common interface for property managers - with constant and dynamic property
 *
 * @param <T>
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public interface IPropertyRegister<T extends Serializable> extends Serializable {

    void addProperty(T propertyValue, int inFrame) throws IllegalAccessException;

    Optional<T> getLatestValue();

    Optional<T> getValueInFrame(int frame);
}
