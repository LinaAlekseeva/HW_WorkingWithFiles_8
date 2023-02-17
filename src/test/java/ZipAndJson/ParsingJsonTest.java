package ZipAndJson;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa.guru.FilesParsingTest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParsingJsonTest {

    @Test
    @DisplayName("Парсинг JSON файла")
    void jsonParsingTest() throws Exception {
        ClassLoader cl = FilesParsingTest.class.getClassLoader();
        Gson gson  = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("example/massiv.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader,JsonObject.class);
            assertThat(jsonObject.get("name").getAsString()).isEqualTo("John");
            assertThat(jsonObject.get("age")).isEqualTo(30);
        }
    }
}