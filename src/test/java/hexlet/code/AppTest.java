package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void mainTest()  {
        App app = new App();
        StringWriter sw = new StringWriter();
        CommandLine cmd = new CommandLine(app);
        cmd.setOut(new PrintWriter(sw));

        int exitCode = cmd.execute("file1.json", "file2.json");
        assertEquals(0, exitCode);
    }
}
