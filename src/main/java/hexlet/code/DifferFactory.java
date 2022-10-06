package hexlet.code;

import hexlet.code.differ.Differ;
import hexlet.code.differ.JsonDiffer;
import hexlet.code.differ.YmlDiffer;

import java.io.IOException;

import static hexlet.code.Utils.getExtension;

public final class DifferFactory {

    public static Differ getDiffer(String filepath1, String filepath2) throws IOException {
        extMatchingCheck(filepath1, filepath2);
        return switch (getExtension(filepath1)) {
            case "json" -> new JsonDiffer(filepath1, filepath2);
            case "yml" -> new YmlDiffer(filepath1, filepath2);
            default -> throw new RuntimeException("Unsupported file's format");
        };
    }

    private static void extMatchingCheck(String filePath1, String filePath2) throws IOException {
        String extension1 = getExtension(filePath1);
        String extension2 = getExtension(filePath2);
        if (!extension1.equals(extension2)) {
            throw new RuntimeException("input file formats are different");
        }
    }
}
