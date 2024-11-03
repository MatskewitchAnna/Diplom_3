import constants.WebDriverSettings;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import user.UserSteps;
import static constants.Url.URL;

public abstract class DriverManagement {
    public static WebDriver webDriver;
    public final UserSteps userSteps = new UserSteps();

    @Before
    public void setup(){
        webDriver = WebDriverSettings.getWebDriver();
        webDriver.get(URL);
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = URL;
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
