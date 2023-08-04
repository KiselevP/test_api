import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class NoAuthRequest
{
    @Test
    void requestNoAuthorizedUser()
    {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get("https://test-stand.gb.ru/api/posts")
                .then()
                .statusCode(401);
    }
}
