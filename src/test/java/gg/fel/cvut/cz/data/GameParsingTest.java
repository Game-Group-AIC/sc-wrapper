package gg.fel.cvut.cz.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasEndedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IOnFrameNotificationSubscriber;
import gg.fel.cvut.cz.facades.managers.GameFacade;
import gg.fel.cvut.cz.facades.managers.ReplayGameFacade;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class GameParsingTest {

  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());

  static {
    mapper.setVisibility(
        mapper.getSerializationConfig().
            getDefaultVisibilityChecker().
            withFieldVisibility(JsonAutoDetect.Visibility.ANY).
            withIsGetterVisibility(JsonAutoDetect.Visibility.NONE).
            withGetterVisibility(JsonAutoDetect.Visibility.NONE));
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  private static final IOnFrameNotificationSubscriber onFrameNotificationSubscriber = currentFrame -> {
    if (currentFrame % 100 == 0) {
      log.info(currentFrame + "");
    }
  };
  private static final GameFacade gameFacade = GameFacade.builder()
      .onFrame(Optional.of(onFrameNotificationSubscriber))
      .frameExecutionTime(10)
      .gameDefaultSpeed(0)
      .onEnd(Optional.of(new GameHasEndedNotificationSubscriber()))
      .build();

  @Test
  public void parseGame() {
    gameFacade.run();
  }

  @AllArgsConstructor
  private static class GameHasEndedNotificationSubscriber implements
      IGameHasEndedNotificationSubscriber {

    @Override
    public void notifySubscriber(boolean result) {
      try {
        log.info("Our bot win: " + result);
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

}
