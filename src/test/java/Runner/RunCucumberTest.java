package Runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@search",
        features = {"src/test/resources/Features"},
        glue = {"StepDefinitions"},
        plugin = {"pretty",
                "html:target/Cucumber-JVM-Reports/cucumber-reports/html-report",
                "json:target/jsonReports/cucumber.json",
                "junit:target/Cucumber-JVM-Reports/cucumber-reports/cucumber.xml",
                "rerun:target/rerun.txt",
        },
        monochrome = true
)
public class RunCucumberTest {
}




