import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest
{
    @Test
    void getXAuthToken() {
        String response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "login001")
                .formParam("password", "1c1dbe676d")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then().extract()
                .jsonPath()
                .get("token")
                .toString();

        Assertions.assertNotEquals(response, null);
    }

    @Test
    void noGetXAuthTokenInvalidLogin() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "login001" + "1")
                .formParam("password", "1c1dbe676d")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .statusCode(401);
    }

    @Test
    void noGetXAuthTokenInvalidPassword() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "login001")
                .formParam("password", "1c1dbe676d" + "1")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .statusCode(401);
    }
}
