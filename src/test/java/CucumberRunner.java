import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber-reports/cucumber.json",  // Generates JSON report
                "html:target/cucumber-reports/cucumber.html"   // Generates HTML report
        },
        features = { "src/test/resources/features" },
        glue = { "steps" }
)
public class CucumberRunner {
}
