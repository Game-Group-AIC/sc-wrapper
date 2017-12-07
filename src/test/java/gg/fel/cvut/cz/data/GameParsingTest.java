package gg.fel.cvut.cz.data;

import bwta.BWTA;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasEndedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasStartedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IOnFrameNotificationSubscriber;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.facades.managers.GameFacade;
import gg.fel.cvut.cz.facades.managers.ReplayGameFacade;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class GameParsingTest {

  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());
  private static Optional<BaseLocation> location = Optional.empty();

  static {
    mapper.setVisibility(
        mapper.getSerializationConfig().
            getDefaultVisibilityChecker().
            withFieldVisibility(JsonAutoDetect.Visibility.ANY).
            withIsGetterVisibility(JsonAutoDetect.Visibility.NONE).
            withGetterVisibility(JsonAutoDetect.Visibility.NONE));
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  private static final GameFacade gameFacade = GameFacade.builder()
      .onFrame(Optional.of(new OnFrameNotificationSubscriber()))
      .frameExecutionTime(10)
      .onStart(Optional.of(new GameHasStartedNotificationSubscriber()))
      .gameDefaultSpeed(0)
      .isForReplay(true)
      .onEnd(Optional.of(new GameHasEndedNotificationSubscriber()))
      .build();

  @Test
  public void parseGame() {
    gameFacade.run();
  }

  public static class GameHasStartedNotificationSubscriber implements
      IGameHasStartedNotificationSubscriber {

    @Override
    public void notifySubscriber() {
      location = gameFacade
          .getDataContainer(WBaseLocation.getOrCreateWrapper(BWTA.getBaseLocations().get(0)));
      location.ifPresent(
          baseLocation -> gameFacade.update(location.get(), UpdateStrategy.builder().build()));
      log.info("Game has started.");
    }
  }

  @AllArgsConstructor
  private static class GameHasEndedNotificationSubscriber implements
      IGameHasEndedNotificationSubscriber {

    @Override
    public void notifySubscriber(boolean ourBotWinResult) {
      try {

        //serialize base location
        location.ifPresent(baseLocation -> {
          try {
            log.info(mapper.writeValueAsString(baseLocation));
          } catch (JsonProcessingException e) {
            e.printStackTrace();
          }
        });

        log.info("Our bot win: " + ourBotWinResult);
        Optional<ReplayGameFacade> replay = gameFacade.getGameAsReplay();
        if (replay.isPresent()) {
          log.info(mapper.writeValueAsString(replay.get()));
          ReplayGameFacade replayGameFacade = mapper
              .readValue(mapper.writeValueAsString(replay.get()), ReplayGameFacade.class);
          log.info("DONE");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @AllArgsConstructor
  private static class OnFrameNotificationSubscriber implements IOnFrameNotificationSubscriber {

    @Override
    public void notifySubscriber(int currentFrame) {
      if (currentFrame % 100 == 0) {
        log.info(currentFrame + "");
      }
    }
  }

}
