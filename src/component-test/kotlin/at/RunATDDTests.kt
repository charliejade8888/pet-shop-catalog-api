package at;

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features"],
    plugin = [
    "pretty",
    "json:build/cucumber-reports/json/cucumber.json",
    "html:build/cucumber-reports/html/cucumber.html"
    ]
)
class RunATDDTests
