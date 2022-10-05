package hexlet.code;


import hexlet.code.differ.Differ;

import hexlet.code.formatter.enums.OutputFormat;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DifferTest {
    private final OutputFormat jsonOutputFormat = OutputFormat.JSON;
    private final OutputFormat plainOutputFormat = OutputFormat.PLAIN;
    private final OutputFormat stylishOutputFormat = OutputFormat.STYLISH;


    @Test
    void jsonDiffTest() throws Exception {
        String jsonFile1 = "file1.json";
        String jsonFile2 = "file2.json";
        Differ differ = DifferFactory.getDiffer(jsonFile1, jsonFile2);

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stylish.txt")));
        assertEquals(expected, differ.generate(stylishOutputFormat));
        assertEquals(expected, differ.generate());

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/plain.txt")));
        assertEquals(expected, differ.generate(plainOutputFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/json.txt")));
        assertEquals(expected, differ.generate(jsonOutputFormat));
    }

    @Test
    void ymlDiffTest() throws Exception {
        String ymlFile1 = "file1.yml";
        String ymlFile2 = "file2.yml";
        Differ differ = DifferFactory.getDiffer(ymlFile1, ymlFile2);

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/stylish.txt")));
        assertEquals(expected, differ.generate(stylishOutputFormat));
        assertEquals(expected, differ.generate());

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/plain.txt")));
        assertEquals(expected, differ.generate(plainOutputFormat));

        expected = new String(Files.readAllBytes(Paths.get("src/test/resources/json.txt")));
        assertEquals(expected, differ.generate(jsonOutputFormat));
    }


    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> {
            Differ differ = DifferFactory.getDiffer("json1", "file2");
            differ.generate(stylishOutputFormat);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsDifferentExtensionTest() {
        Throwable thrown = catchThrowable(() -> {
            Differ differ = DifferFactory.getDiffer("file1.json", "file1.yml");
            differ.generate(stylishOutputFormat);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }


    @Test
    void exceptionsInputFormatIsNotSupported() {
        Throwable thrown = catchThrowable(() -> {
            Differ differ = DifferFactory.getDiffer("file.txt", "file.txt");
            differ.generate(stylishOutputFormat);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
