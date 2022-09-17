package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EngineTest {

    @Test
    void generateJsonFileInputStylishFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        Engine engine = new Engine(path1, path2, "stylish");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, engine.generate());
    }

    @Test
    void generateYmlFileInputWithoutFormat() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";
        Engine engine = new Engine(path1, path2);
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, engine.generate());
    }

    @Test
    void generateJsonFileInputPlainFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        Engine engine = new Engine(path1, path2, "plain");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test3.txt")));
        assertEquals(expected, engine.generate());
    }

    @Test
    void generateJsonFileInputJsonFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        Engine engine = new Engine(path1, path2, "json");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, engine.generate());
    }

    @Test
    void generateJsonFileWithAbsolutePath() throws Exception {
        String path1 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file1.json";
        String path2 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file2.json";
        Engine engine = new Engine(path1, path2, "json");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, engine.generate());
    }

    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> {
            Engine engine = new Engine("json1", "file2");
            engine.generate();
        });
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void exceptionsDifferentExtensionTest() {
        Throwable thrown = catchThrowable(() -> {
            Engine engine = new Engine("file1.json", "file2.yml");
            engine.generate();
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsUnsupportedFormatTest() {
        Throwable thrown = catchThrowable(() -> {
            Engine engine = new Engine("file1.json", "file2.json", "yml");
            engine.generate();
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsInputFormatIsNotSupported() {
        Throwable thrown = catchThrowable(() -> {
            Engine engine = new Engine("file.txt", "file.txt");
            engine.generate();
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
