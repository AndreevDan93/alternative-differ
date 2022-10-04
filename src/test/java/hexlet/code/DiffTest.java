package hexlet.code;


import hexlet.code.differ.Diff;
import hexlet.code.differ.JsonDiff;
import hexlet.code.differ.YmlDiff;
import hexlet.code.formatter.OutputFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DiffTest {
    private final OutputFormat jsonOutputFormat = OutputFormat.JSON;
    private final OutputFormat plainOutputFormat = OutputFormat.PLAIN;
    private final OutputFormat stylishOutputFormat = OutputFormat.STYLISH;


    @Test
    void jsonDiffTest() throws Exception {
        String jsonFile1 = "file1.json";
        String jsonFile2 = "file2.json";
        Diff diff = new JsonDiff(jsonFile1, jsonFile2);

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stylish.txt")));
        assertEquals(expected, diff.generate(stylishOutputFormat));
        assertEquals(expected, diff.generate());

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/plain.txt")));
        assertEquals(expected, diff.generate(plainOutputFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/json.txt")));
        assertEquals(expected, diff.generate(jsonOutputFormat));
    }

    @Test
    void ymlDiffTest() throws Exception {
        String ymlFile1 = "file1.yml";
        String ymlFile2 = "file2.yml";
        Diff diff = new YmlDiff(ymlFile1, ymlFile2);

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stylish.txt")));
        assertEquals(expected, diff.generate(stylishOutputFormat));
        assertEquals(expected, diff.generate());

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/plain.txt")));
        assertEquals(expected, diff.generate(plainOutputFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/json.txt")));
        assertEquals(expected, diff.generate(jsonOutputFormat));
    }


    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> {
            Diff diff = new JsonDiff("json1", "file2");
            diff.generate(stylishOutputFormat);
        });
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void exceptionsDifferentExtensionTest() {
        Throwable thrown = catchThrowable(() -> {
            Diff diff = new JsonDiff("file1.json", "file2.yml");
            diff.generate(stylishOutputFormat);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }


    @Test
    void exceptionsInputFormatIsNotSupported() {
        Throwable thrown = catchThrowable(() -> {
            Diff diff = new JsonDiff("file.txt", "file.txt");
            diff.generate(stylishOutputFormat);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
