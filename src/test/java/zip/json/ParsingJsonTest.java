package zip.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

public class ParsingJsonTest {

    ClassLoader cl = ParsingJsonTest.class.getClassLoader();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Парсинг JSON файла")
    void jsonParsingTest() throws IOException {
        try (
                InputStream resource = cl.getResourceAsStream("example/massiv.json");
                InputStreamReader reader = new InputStreamReader(resource)
        )
            {
                ParsingJsonTest  members = mapper.readValue(reader, ParsingJsonTest.class);
                assertThat(members.name).isEqualTo("Molecule Man");
                assertThat(members.age).isEqualTo(29);
                assertThat(members.secretIdentity).isEqualTo("Dan Jukes");
                assertThat(members.powers).isEqualTo("Radiation resistance",
                        "Turning tiny","Radiation blast");
            }
        }
    }
