// nome do pacote
package  apiTest;

// Bibliotecas


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// Classe
public class TesteBooker {     // inicio da classe
    // Atributos

    // Funções e Métodos
    // Funções de Apoio
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }
    @Test
    public void testarGerarToken() throws IOException {
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/token1.json");

        // realizar o teste
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post("https://restful-booker.herokuapp.com/auth")
        .then()
                .log().all()
                .statusCode(200)
        ;


    }
    @Test
    public void testarCreateBooking() throws IOException {
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/create1.json");

        //realizar teste
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post("https://restful-booker.herokuapp.com/booking")
        .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname", is("Ronyy"))
                .body("booking.totalprice", is(400))
                .body("booking.depositpaid", is(true))
                .body("booking.bookingdates.checkin", is("2024-01-01"))

        ;

    }



} // fim da classe
