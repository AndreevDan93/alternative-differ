package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class NotStaticDifferTest {

    @Test
    void generateJsonFileInputStylishFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        NotStaticDiffer differ = new NotStaticDiffer(path1, path2, "stylish");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void generateYmlFileInputWithoutFormat() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";
        NotStaticDiffer differ = new NotStaticDiffer(path1, path2);
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void generateJsonFileInputPlainFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        NotStaticDiffer differ = new NotStaticDiffer(path1, path2, "plain");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test3.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void generateJsonFileInputJsonFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        NotStaticDiffer differ = new NotStaticDiffer(path1, path2, "json");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void generateJsonFileWithAbsolutePath() throws Exception {
        String path1 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file1.json";
        String path2 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file2.json";
        NotStaticDiffer notStaticDiffer = new NotStaticDiffer(path1, path2, "json");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, notStaticDiffer.generate());
    }

    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> {
            NotStaticDiffer notStaticDiffer = new NotStaticDiffer("json1", "file2");
            notStaticDiffer.generate();
        });
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void exceptionsDifferentExtensionTest() {
        Throwable thrown = catchThrowable(() -> {
            NotStaticDiffer notStaticDiffer = new NotStaticDiffer("file1.json", "file2.yml");
            notStaticDiffer.generate();
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsUnsupportedFormatTest() {
        Throwable thrown = catchThrowable(() -> {
            NotStaticDiffer notStaticDiffer = new NotStaticDiffer("file1.json", "file2.json", "yml");
            notStaticDiffer.generate();
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsInputFormatIsNotSupported() {
        Throwable thrown = catchThrowable(() -> {
            NotStaticDiffer notStaticDiffer = new NotStaticDiffer("file.txt", "file.txt");
            notStaticDiffer.generate();
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
