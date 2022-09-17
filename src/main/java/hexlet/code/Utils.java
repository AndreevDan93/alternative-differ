package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Utils {
    static final String LOCAL_PATH_INSIDE_PROJECT = "src/main/resources/";

    public static Path getAbsolutePath(String inputPathString) throws IOException {
        Path inputPath = Path.of(inputPathString);
        Path absolutePath;
        if (inputPath.isAbsolute() || new File(inputPath.toString()).exists()) {
            return inputPath;
        } else {
            StringBuilder absolutePathSB = new StringBuilder(inputPath.toAbsolutePath().toString());
            absolutePathSB.insert(absolutePathSB.length() - inputPathString.length(), LOCAL_PATH_INSIDE_PROJECT);
            absolutePath = Path.of(absolutePathSB.toString());
        }
        if (!(new File(absolutePath.toString()).exists())) {
            throw new NoSuchFileException("file " + inputPathString + " is not found");
        }
        return absolutePath;
    }

    public static String getExtension(String path) throws IOException {
        String absolutePath = getAbsolutePath(path).toString();
        return (absolutePath.substring(absolutePath.lastIndexOf(".") + 1));
    }


}
