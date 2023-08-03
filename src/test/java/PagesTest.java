import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class PagesTest extends AbstractTest {

    @Test
    void requestToLookMyPostsWithoutSort() {
        List<Object> response = given()
                .queryParam("sort", "createdAt")
                .when()
                .get(getBaseUrl() + "api/posts")
                .jsonPath()
                .get("data");
        Assertions.assertNotEquals(response, null);
        Assertions.assertFalse(response.size() == 10);
    }

    @Test
    void requestToLookNotMyPostsWithoutSort() {
        List<Object> response = given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .jsonPath()
                .get("data");
        Assertions.assertTrue(response.size() == 4);
    }

    @Test
    void requestToLookAtNotMyPostsMoreToLess() {
        List<Object> response = given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .jsonPath()
                .get("data");

        Assertions.assertNotEquals(response, null);
    }

    @Test
    void requestToLookAtAllPosts() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ALL")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .statusCode(500);
    }

    @Test
    void requestToLookAtNotMyPostsOnPage() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "27654")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .statusCode(200);
    }

    @Test
    void requestToLookAtPost() {
        int id = given()
                .when()
                .get(getBaseUrl() + "api/posts/69179")
                .jsonPath()
                .get("id");
        Assertions.assertTrue(id == 69179);
    }

    @Test
    void requestToLookMyPostsLessToMore() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .statusCode(200);
    }

    @Test
    void requestToLookMyPostsMoreToLess() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .statusCode(200);
    }
}
