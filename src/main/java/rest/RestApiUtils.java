package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Environment;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class RestApiUtils {

    public static LoginEntity getLoginEntityKeyBy(String username, String password) {
        return new LoginEntity(username, password);
    }

    public static String getJsonBy(AbstractEntity entity) {
        String jsonBody = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonBody = objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonBody;
    }

    public static String getAuthToken(String jsonEntity){
        String unparsedResult = RestAssured.
                given()
                    .contentType(ContentType.JSON)
                    .body(jsonEntity)
                .when()
                    .post(Environment.INSTANCE.getProperty("global.api.login_endpoint"))
                .thenReturn().getBody().print();

        String tmp = unparsedResult.split(": ")[1];
        return tmp.substring(0, tmp.length() - 1);
    }

    public static JsonPath getJsonCardResult(String authKey){
        String jsonBy = getJsonBy(new CardEntityRequest(authKey));

        return RestAssured.
                given()
                    .contentType(ContentType.JSON)
                    .body(jsonBy)
                .when()
                    .post(Environment.INSTANCE.getProperty("global.api.card_endpoint"))
                .thenReturn().getBody().jsonPath();
    }

}
