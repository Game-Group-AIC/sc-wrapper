package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

@JsonDeserialize(as = BaseLocation.class)
public interface IBaseLocation extends IAbstractPoint, InGameInterface, Serializable {

  //TODO can be removed? to update one time only?
  Optional<Integer> minerals();

  Optional<Integer> gas();

  //TODO filter from static minerals
//  Optional<Stream<IUnit>> getMinerals();

  Optional<Stream<IUnit>> getStaticMinerals();

  Optional<Stream<IUnit>> getGeysers();

  Optional<Double> getGroundDistance(IBaseLocation other);

  default Optional<Double> getAirDistance(IBaseLocation other) {
    return getApproxDistance(other);
  }

  Optional<Boolean> isIsland();

  default Optional<Boolean> isMineralOnly() {
    return getGeysers().map(iUnitStream -> iUnitStream.count() == 0);
  }

  Optional<Boolean> isStartLocation();

  default Optional<IBaseLocation> getNearestBaseLocation() {
    return Optional.of(this);
  }
}
