package hexlet.code;


import hexlet.code.differ.Diff;
import hexlet.code.differ.JsonDiff;
import hexlet.code.differ.YmlDiff;
import hexlet.code.formatter.Format;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DiffTest {
    private final Format jsonFormat = Format.JSON;
    private final Format plainFormat = Format.PLAIN;
    private final Format stylishFormat = Format.STYLISH;


    @Test
    void jsonDiffTest() throws Exception {
        String jsonFile1 = "file1.json";
        String jsonFile2 = "file2.json";
        Diff diff = new JsonDiff(jsonFile1, jsonFile2);

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stylish.txt")));
        assertEquals(expected, diff.generate(stylishFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/plain.txt")));
        assertEquals(expected, diff.generate(plainFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/json.txt")));
        assertEquals(expected, diff.generate(jsonFormat));
    }

    @Test
    void ymlDiffTest() throws Exception {
        String ymlFile1 = "file1.yml";
        String ymlFile2 = "file2.yml";
        Diff diff = new YmlDiff(ymlFile1, ymlFile2);

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stylish.txt")));
        assertEquals(expected, diff.generate(stylishFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/plain.txt")));
        assertEquals(expected, diff.generate(plainFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/json.txt")));
        assertEquals(expected, diff.generate(jsonFormat));
    }


    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> {
            Diff diff = new JsonDiff("json1", "file2");
            diff.generate(stylishFormat);
        });
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void exceptionsDifferentExtensionTest() {
        Throwable thrown = catchThrowable(() -> {
            Diff diff = new JsonDiff("file1.json", "file2.yml");
            diff.generate(stylishFormat);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }


    @Test
    void exceptionsInputFormatIsNotSupported() {
        Throwable thrown = catchThrowable(() -> {
            Diff diff = new JsonDiff("file.txt", "file.txt");
            diff.generate(stylishFormat);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
