package gg.fel.cvut.cz.data;

import bwapi.Race;
import bwapi.UnitType;
import bwapi.WeaponType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import gg.fel.cvut.cz.data.events.subscribers.IOnFrameNotificationSubscriber;
import gg.fel.cvut.cz.enums.IGameTypes;
import gg.fel.cvut.cz.facades.managers.GameFacade;
import java.util.Optional;
import org.junit.Test;

public class GetTypesFromGameTest {

  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());
  private static final GameFacade gameFacade = GameFacade.builder()
      .onFrame(Optional.of(new OnFrameNotificationSubscriber()))
      .build();

  @Test
  public void parseGame() {
    gameFacade.run();
  }

  public static class OnFrameNotificationSubscriber
      implements IOnFrameNotificationSubscriber {

    @Override
    public void notifySubscriber(int currentFrame) {
      for (WeaponType weaponType : IGameTypes.WEAPON_TYPES) {
        UnitType unitType = weaponType.whatUses();
        Race race = unitType.getRace();

        System.out.println(race + " " + weaponType + " ");
      }

      System.exit(0);
    }
  }
}
