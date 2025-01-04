package AppTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html",
                "timeline:target/cucumber-reports/timeline"

        },
        features = { "src/test/resources/features" },
        glue = { "steps" }
)
public class CucumberTest {

        @AfterClass
        public static void openCucumberReport() {
                File htmlFile = new File("target/cucumber-reports/cucumber.html");

                // Log the file path for debugging purposes
                System.out.println("Attempting to open report: " + htmlFile.getAbsolutePath());

                if (!htmlFile.exists()) {
                        System.err.println("Error: The report file does not exist! Please check if it was generated successfully.");
                        return;
                }

                try {
                        // Open the file with the Desktop API if supported
                        if (Desktop.isDesktopSupported()) {
                                Desktop.getDesktop().browse(htmlFile.toURI());
                                System.out.println("Report successfully opened in the default browser.");
                        } else {
                                // Use fallback mechanism for unsupported systems
                                openWithRuntime(htmlFile);
                        }
                } catch (IOException e) {
                        System.err.println("Failed to open the report automatically. Please open it manually: " + htmlFile.getAbsolutePath());
                        e.printStackTrace();
                }
        }

        private static void openWithRuntime(File htmlFile) throws IOException {
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + htmlFile.getAbsolutePath());
                } else if (os.contains("mac")) {
                        Runtime.getRuntime().exec("open " + htmlFile.getAbsolutePath());
                } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                        Runtime.getRuntime().exec("xdg-open " + htmlFile.getAbsolutePath());
                } else {
                        System.err.println("Unsupported operating system. Please open the report manually: " + htmlFile.getAbsolutePath());
                }
        }
}
