package gg.fel.cvut.cz.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import java.io.IOException;
import org.junit.Test;

public class SerializationTest {

  private ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());

  @Test
  public void testPresenceOfBidirectionalRelation() throws IOException {
//        mapper.setVisibility(
//                mapper.getSerializationConfig().
//                        getDefaultVisibilityChecker().
//                        withFieldVisibility(JsonAutoDetect.Visibility.ANY).
//                        withGetterVisibility(JsonAutoDetect.Visibility.NONE));
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        UpdatablePosition updatablePosition = new UpdatablePosition(new WPosition(new Position(0, 2)));
//        UpdatableTilePosition updatableTilePosition = new UpdatableTilePosition(new WTilePosition(new TilePosition(0, 2)));
//        IPosition position = mapper.readValue(mapper.writeValueAsString(updatablePosition), IPosition.class);
//        assertTrue(position.getTilePosition().isPresent());
//        assertTrue(position.getTilePosition().get().getPosition().isPresent());
  }

}
