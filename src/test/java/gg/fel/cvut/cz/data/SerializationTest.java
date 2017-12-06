package gg.fel.cvut.cz.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableMap;
import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.Tuple;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.PropertyMap;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.data.updatable.UpdatableTechType;
import gg.fel.cvut.cz.enums.TechTypeEnum;
import gg.fel.cvut.cz.wrappers.WTechType;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SerializationTest {

  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());

  static {
    mapper.setVisibility(
        mapper.getSerializationConfig()
            .getDefaultVisibilityChecker()
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
            .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withGetterVisibility(JsonAutoDetect.Visibility.NONE));
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
  }

  @Test
  public void testPresenceOfBidirectionalRelation() throws IOException {
    DynamicPropertyRegister<Tuple<Integer, Integer>, Property<Tuple<Integer, Integer>>> register = new DynamicPropertyRegister<Tuple<Integer, Integer>, Property<Tuple<Integer, Integer>>>(
        Property::new);
    register.addProperty(new Tuple<>(2, 3), 4);
    register.addProperty(new Tuple<>(2, 3), 5);
    register.addProperty(new Tuple<>(2, 5), 7);
    register.addProperty(new Tuple<>(3, 5), 8);
    log.info(mapper.writeValueAsString(register));

    DynamicPropertyRegister<Tuple<Integer, Integer>, Property<Tuple<Integer, Integer>>> registerDes =
        mapper.readValue(mapper.writeValueAsString(register),
            new TypeReference<DynamicPropertyRegister<Tuple<Integer, Integer>, Property<Tuple<Integer, Integer>>>>() {
            });

    BWReplayCounter counter = new BWReplayCounter(1000);
    counter = mapper.readValue(mapper.writeValueAsString(counter), BWReplayCounter.class);

    ITechType techType = new UpdatableTechType(new BWReplayCounter(),
        WTechType.getOrCreateWrapper(TechTypeEnum.ArchonWarp));
    techType = mapper.readValue(mapper.writeValueAsString(techType), ITechType.class);

    PropertyMap<Tuple<String, String>, String> tuple = mapper
        .readValue(mapper
                .writeValueAsString(new PropertyMap<>(ImmutableMap.of(new Tuple<>("a", "b"), "ahoj"))),
            new TypeReference<PropertyMap<Tuple<String, String>, String>>() {
            });

    StaticPropertyRegister<ImmutableMap<Tuple<String, String>, String>, PropertyMap<Tuple<String, String>, String>> propertyRegister = new StaticPropertyRegister<ImmutableMap<Tuple<String, String>, String>, PropertyMap<Tuple<String, String>, String>>(
        PropertyMap::new);
    propertyRegister.addProperty(ImmutableMap.of(new Tuple<>("ahoj", "test"), "ahoj"), 0);
    log.info(mapper.writeValueAsString(propertyRegister));

    TypeReference<StaticPropertyRegister<ImmutableMap<Tuple<String, String>, String>, PropertyMap<Tuple<String, String>, String>>> typeRef
        = new TypeReference<StaticPropertyRegister<ImmutableMap<Tuple<String, String>, String>, PropertyMap<Tuple<String, String>, String>>>() {
    };
    StaticPropertyRegister deserialize =
        mapper.readValue(mapper.writeValueAsString(propertyRegister), typeRef);
    log.info("DONE");
  }

}
