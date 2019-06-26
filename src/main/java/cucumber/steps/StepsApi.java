package cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.hooks.BaseHooks;
import enums.Environment;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonParserType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.ResponseBody;
import junit.AssertWrapper;
import rest.AuthEntity;
import rest.LoginEntity;
import rest.RestApiUtils;

import java.io.IOException;

public class StepsApi extends AbstractStep {

    public StepsApi(BaseHooks browser) {
        super(browser);
    }

    @And("^API should return added product$")
    public void apiShouldReturnAddedProduct() {
        JsonPath cardResult = getCardResult();

        AssertWrapper.assertTrueThrowIfFailed("Rest API should return not empty product.", (Integer)cardResult.get("Count") == 1);
    }

    @And("^API should return empty list of products$")
    public void apiShouldReturnEmptyListOfProducts() {
        JsonPath cardResult = getCardResult();

        AssertWrapper.assertTrueThrowIfFailed("Rest API should return not empty product.", (Integer)cardResult.get("Count") == 0);
    }

    private JsonPath getCardResult(){
        String username = Environment.INSTANCE.getProperty("global.username");
        String password = Environment.INSTANCE.getProperty("global.api.secure_password");

        LoginEntity loginEntity = RestApiUtils.getLoginEntityKeyBy(username, password);
        String jsonEntity = RestApiUtils.getJsonBy(loginEntity);
        String authToken = RestApiUtils.getAuthToken(jsonEntity);

        return RestApiUtils.getJsonCardResult(authToken);


    }
}
