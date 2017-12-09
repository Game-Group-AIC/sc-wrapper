package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.WalkPosition;
import java.io.Serializable;
import java.util.Optional;

/**
 * Walk Position
 */
@JsonDeserialize(as = WalkPosition.class)
public interface IWalkPosition extends IAbstractPoint, InGameInterface, Serializable {

  //TODO maybe take in account visibility...
  Optional<Boolean> isWalkable();

}
