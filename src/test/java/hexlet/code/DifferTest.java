package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DifferTest {

    @Test
    void generateJsonFileInputStylishFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, Differ.generate(path1, path2, "stylish"));
    }

    @Test
    void generateYmlFileInputWithoutFormat() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, Differ.generate(path1, path2));
    }

    @Test
    void generateJsonFileInputPlainFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test3.txt")));
        assertEquals(expected, Differ.generate(path1, path2, "plain"));
    }

    @Test
    void generateJsonFileInputJsonFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, Differ.generate(path1, path2, "json"));
    }

    @Test
    void generateJsonFileWithAbsolutePath() throws Exception {
        String path1 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file1.json";
        String path2 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, Differ.generate(path1, path2, "json"));
    }

    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> Differ.generate("json1", "file2"));
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void exceptionsDifferentExtensionTest() {
        Throwable thrown = catchThrowable(() -> Differ.generate("file1.json", "file2.yml"));
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsUnsupportedFormatTest() {
        Throwable thrown = catchThrowable(() -> Differ.generate("file1.json", "file2.json", "yml"));
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsInputFormatIsNotSupported() {
        Throwable thrown = catchThrowable(() -> Differ.generate("file.txt", "file.txt"));
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
