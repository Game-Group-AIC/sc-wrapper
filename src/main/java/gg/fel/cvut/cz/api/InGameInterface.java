package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Common ancestor for all in game interfaces
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public interface InGameInterface {
  //TODO more specific interfaces
  //TODO specific interfaces to command units
}
