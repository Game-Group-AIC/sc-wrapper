package gg.fel.cvut.cz.data;

import bwapi.Position;
import bwapi.TilePosition;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.data.updatable.UpdatablePosition;
import gg.fel.cvut.cz.data.updatable.UpdatableTilePosition;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class SerializationTest {

    private ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());

    @Test
    public void testPresenceOfBidirectionalRelation() throws IOException {
        mapper.setVisibility(
                mapper.getSerializationConfig().
                        getDefaultVisibilityChecker().
                        withFieldVisibility(JsonAutoDetect.Visibility.ANY).
                        withGetterVisibility(JsonAutoDetect.Visibility.NONE));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        UpdatablePosition updatablePosition = new UpdatablePosition(new WPosition(new Position(0, 2)));
        UpdatableTilePosition updatableTilePosition = new UpdatableTilePosition(new WTilePosition(new TilePosition(0, 2)));
        updatableTilePosition.setPosition(updatablePosition.getDataAccessContainer(), 0);
        updatablePosition.setTilePosition(updatableTilePosition.getDataAccessContainer(), 0);
        updatablePosition.setX(0, 0);
        updatablePosition.setY(2, 0);
        IPosition tilePosition = mapper.readValue(mapper.writeValueAsString(updatablePosition), IPosition.class);
        assertTrue(tilePosition.getTilePosition().isPresent());
        assertTrue(tilePosition.getTilePosition().get().getPosition().isPresent());
    }

}
