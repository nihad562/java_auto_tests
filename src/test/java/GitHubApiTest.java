import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class GitHubApiTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://api.github.com";
    }

    @Test
    public void testUserRepos() {
        List<String> expectedRepoNames = new ArrayList<>();
        expectedRepoNames.add("Github");
        expectedRepoNames.add("PageObject");
        expectedRepoNames.add("Start");
        expectedRepoNames.add("stepik_at_python");

        List<String> names = given().auth().basic("nihad562", "Nihad271193")
                .when()
                .get("/users/nihad562/repos")
                .then()
                .statusCode(200)
                .extract()
                .path("name");

        UserRepoInfo[] userRepoInfos = given().auth().basic("nihad562", "Nihad271193")
                .when()
                .get("/users/nihad562/repos")
                .then()
                .statusCode(200)
                .extract()
                .as(UserRepoInfo[].class);

        Assertions.assertEquals(4, userRepoInfos.length);
        Assertions.assertEquals(expectedRepoNames, names);
    }
}
