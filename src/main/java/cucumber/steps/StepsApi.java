package cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.hooks.BaseHooks;
import enums.Environment;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.ResponseBody;
import rest.LoginEntity;

public class StepsApi extends AbstractStep {

    public StepsApi(BaseHooks browser) {
        super(browser);
    }

    @Then("^API should return added product$")
    public void api_should_return_added_product() throws Throwable {

    }

    @Given("^Test Api$")
    public void testApi() {

        String jsonBody = "{username: \"Roman\", password: \"Um9tYW4uT3Npbm92LlBhc3N3b3Jk\" }";

        LoginEntity entity = new LoginEntity(jsonBody);

        String auth_token = RestAssured.
                given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(Environment.INSTANCE.getProperty("global.api.login_endpoint"))
                .thenReturn().jsonPath().getString("Auth_token");


        body.print();


    }
}
