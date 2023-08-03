import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest
{
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String authToken;
    private static String baseUrl;
    private static String userName;
    private static String password;
    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void initTest()
            throws IOException
    {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        authToken = prop.getProperty("auth_token");
        baseUrl = prop.getProperty("base_url");
        userName = prop.getProperty("user_name");
        password = prop.getProperty("password");

        requestSpecification = new RequestSpecBuilder()
                .addHeader("X-Auth-Token", authToken)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.requestSpecification = requestSpecification;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
