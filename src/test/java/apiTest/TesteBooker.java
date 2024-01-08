// nome do pacote
package  apiTest;

// Bibliotecas


import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// Classe
public class TesteBooker {     // inicio da classe
    // Atributos
    private String bookingId;  // Agora é um campo da classe
    private String token;

    // Funções e Métodos
    // Funções de Apoio
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));


    }
    @Test
    public void testarGerarToken() throws IOException { // inicio gerar Token Post
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/token1.json");


        // realizar o teste
        Response response = (Response) given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post("https://restful-booker.herokuapp.com/auth")
        .then()
                .log().all()
                .statusCode(200)
                .body("token", hasLength(15))
        .extract()
        ;
        // Extração do token da resposta

        token = response.jsonPath().getString("token");
        System.out.println("Conteúdo do Token: " + token);

    }   // fim do post token

    @Test
    public void testarCreateBooking() throws IOException {  // inicio do post booking
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/create1.json");

        //realizar teste
       Response response = (Response) given()
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
       .extract()
        ;
        // Extração de ID Booking

        bookingId = response.jsonPath().getString("bookingid");
        System.out.println("Conteúdo do ID: " + bookingId );

    }   // fim do post booking

    @Test
    public void testarConsultarBooking() throws IOException {   // inicio do get booking

    testarCreateBooking();  // Chamando o método que cria o booking

    // resultado esperados
    String firstname = "Ronyy";
    String lastname = "Brown";
    int totalprice = 400;
    boolean depositpaid = true;
    String checkin = "2024-01-01";
    String checkout = "2024-01-15";

    given()
            .contentType("application/json")
            .log().all()
    .when()
            .get("https://restful-booker.herokuapp.com/booking/" + bookingId )
    .then()
            .log().all()
            //.statusCode(200)
            .body("firstname", is(firstname))
            .body("lastname", is(lastname))
            .body("totalprice", is(totalprice))
            .body("depositpaid", is(depositpaid))
            .body("bookingdates.checkin", is(checkin))
            .body("bookingdates.checkout", is(checkout))

    ;


    }   // fim do get booking

    @Test
    public void testarAlterarBooking() throws IOException { // inicio do put booking
        testarCreateBooking();  // Chamando o método que cria o booking

        String jsonBody = lerArquivoJson("src/test/resources/json/update1.json");

        given()
                .contentType("application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(jsonBody)
                .log().all()
        .when()
                .put("https://restful-booker.herokuapp.com/booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(200)
                .body("firstname", is("Ronyy"))
                .body("lastname", is("Brown"))
                .body("totalprice", is(200))
                .body("depositpaid", is(false))
                .body( "bookingdates.checkin", is("2024-01-01"))
        ;


    }   // fim do put booking

    @Test
    public void testarExcluirBooking() throws IOException { // inicio do delete booking

        testarCreateBooking();  // Chamando o método que cria o booking


        given()
                .contentType("application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .log().all()
        .when()
                .delete("https://restful-booker.herokuapp.com/booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(201)
        ;



    }   // fim do delete user



} // fim da classe
