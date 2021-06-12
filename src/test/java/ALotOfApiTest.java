import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

public class ALotOfApiTest {

    @Test
    public void testUserList() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(2));
    }

    @Test
    public void testSingleUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    @Test
    public void testUserNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .assertThat()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void testUnknown() {
        given()
                .when()
                .get("https://reqres.in/api/unnown")
                .then()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(1));
    }

    @Test
    public void testUnknown2() {
        given()
                .when()
                .get("https://reqres.in/api/unnown/2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    @Test
    public void testUnknown23() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .assertThat()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }
}
