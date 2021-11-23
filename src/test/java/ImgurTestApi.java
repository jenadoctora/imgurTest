import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class ImgurTestApi {

    @BeforeAll
    static void beforAll() {
        RestAssured.baseURI = ImgurApiParams.API_URL + "/" + ImgurApiParams.API_VERSION;
    }

    @DisplayName("Тест на получение базовой информации об аккаунте")
    @Test
    void testAccountBase() {
        String url = "account/" + "jenadoctora";
        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .expect()
                .statusCode(is(200))
                .body("success", is(true))
                .body("status", is(200))
                .body("data.bio", is(null))
                .body("data.reputation", is(0))
                .log()
                .all()
                .when()
                .get(url);
    }

    @DisplayName("Тест получения информации о картинке")
    @Test
    void testGetImageInfo() {
        String imageHash = "vKtKhId";
        String url = "image/" + imageHash;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when();
//                .post(url);

    }

    @DisplayName("Тест обновления информации о картинке")
    @Test
    void testUpdateImageInfo() {
        String imageHash = "eXl1Rok";
        String url = "image/" + imageHash;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addFormParam("title", "Omg Braxton")
                .addFormParam("description", "Just a simple mem")
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is(true))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Тест добавления картинки в избранное")
    @Test
    void testImageFavorite() {
        String imageHash = "eXl1Rok";
        String url = "image/" + imageHash + "/favorite";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is("favorited"))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Тест удаления картинки")
    @Test
    void testDeleteImage() {
        String imageHash = "eXl1Rok";
        String url = "image/" + imageHash;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is(true))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Создание альбома")
    @Test
    void testCreationAlbum() {
//        String imageHash = "nYcP2p6";
        String url = "/album";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addFormParam("ids", "nYcP2p6")
                .addFormParam("title", "Omg Braxton")
                .addFormParam("description", "Just a simple mem")
                .addFormParam("cover", "nYcP2p6")
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data.id", is("sgswVTm"))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Что лежит в альбоме")
    @Test
    void testPicturesInTheAlbum() {
        String albumHash = "lCV89PY";
        String url = albumHash + "/images";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("data.type", is("image/jpeg"))
                .expectBody("success", is(true))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get(url);
    }

    @DisplayName("Обновление альбома")
    @Test
    void testUpdateAlbum() {
        String albumHash = "lCV89PY";
        String url = "/album" + albumHash;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addFormParam("title", "Omg Braxton")
                .addFormParam("description", "Just a simple mem")
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is(true))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Добавление альбома в избранное")
    @Test
    void testAddToFavourites() {
        String albumHash = "lCV89PY";
        String url = "/album" + albumHash + "/favorite";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is("favorited"))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Удаление картинки из альбома")
    @Test
    void testRemovePictureFromAlbum() {
        String albumHash = "lCV89PY";
        String url = "/album" + albumHash + "/remove_images";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addFormParam("ids", "nYcP2p6")
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is("true"))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }

    @DisplayName("Удаление альбома")
    @Test
    void testRemoveAlbum() {
        String albumHash = "lCV89PY";
        String url = "/album" + albumHash;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is("true"))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(ImgurApiParams.TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .delete(url);
    }





}
