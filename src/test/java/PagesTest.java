import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class PagesTest extends AbstractTest {
    @Test
    void requestToLookAtOtherPeoplesPostsWithoutSort() {
        JsonPath response = given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .when()
                .get(getBaseUrl() + "api/posts")
                .jsonPath();
        int id = response.get("data[1].id");
        assertThat(response.get("data[0].id"), lessThan(id));
    }

    @Test
    void requestToLookAtOtherPeoplesPostsLessToMore() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then();
    }

    @Test
    void requestToLookAtOtherPeoplesPostsMoreToLess() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then();
    }

    @Test
    void requestToLookAtALLOtherPeoplesPosts() {
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
    void requestToLookAtOtherPeoplesPostsNonExistentPage() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "27654")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then();
    }

    @Test
    void requestToLookAtOtherPeoplesPostsNonExistentPage2() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "758")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .statusCode(200);
    }

    @Test
    void requestToLookMyPostsWithoutSort() {
        given()
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then();
    }

    @Test
    void requestToLookMyPostsLessToMore() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then();
    }

    @Test
    void requestToLookMyPostsMoreToLess() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then();
    }

    @Test
    void requestToLookMyPostsNonExistentPage() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "10000")
                .when()
                .get(getBaseUrl() + "api/posts")
                .then();
    }
}
