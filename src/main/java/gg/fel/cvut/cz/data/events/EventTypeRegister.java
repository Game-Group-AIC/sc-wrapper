package gg.fel.cvut.cz.data.events;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import java.util.HashSet;
import java.util.Set;

/**
 * Registers one type of event
 */
public class EventTypeRegister<T> {

  private final Set<T> registeredEvents = new HashSet<>();

  public void add(T event) {
    registeredEvents.add(event);
  }

  /**
   * Persist batch of events in frame to register
   */
  public void batchEventsInFrameToRegister(int currentFrame,
      DynamicPropertyRegister<ImmutableSet<T>> propertyRegister) {
    if (!registeredEvents.isEmpty()) {
      propertyRegister.addProperty(ImmutableSet.copyOf(registeredEvents), currentFrame);
      registeredEvents.clear();
    }
  }

}
