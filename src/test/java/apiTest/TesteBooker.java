// nome do pacote
package  apiTest;

// Bibliotecas


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// Classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteBooker {     // inicio da classe
    // Atributos
    private static String uri = "https://restful-booker.herokuapp.com/";
    private static String ct = "application/json";

    private static String bookingId;  // Agora é um campo da classe
    private static String token;


    // Funções e Métodos
    // Funções de Apoio
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));


    }
    @Test
    @Order(1)
    public void testarGerarToken() throws IOException { // inicio gerar Token Post
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/token1.json");


        // realizar o teste
        Response response = (Response)
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "auth")
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
    @Order(2)
    public void testarCreateBooking() throws IOException {  // inicio do post booking
        // carregar os dados do json
        String jsonBody = lerArquivoJson("src/test/resources/json/create1.json");

        //realizar teste
       Response response = (Response)
       given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "booking")
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
    @Order(3)
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
            .contentType(ct)
            .log().all()
    .when()
            .get(uri + "booking/" + bookingId )
    .then()
            .log().all()
            .statusCode(200)
            .body("firstname", is(firstname))
            .body("lastname", is(lastname))
            .body("totalprice", is(totalprice))
            .body("depositpaid", is(depositpaid))
            .body("bookingdates.checkin", is(checkin))
            .body("bookingdates.checkout", is(checkout))

    ;


    }   // fim do get booking

    @Test
    @Order(4)
    public void testarAlterarBooking() throws IOException { // inicio do put booking
        testarCreateBooking();  // Chamando o método que cria o booking
        testarGerarToken();

        String jsonBody = lerArquivoJson("src/test/resources/json/update1.json");

        given()
                .contentType(ct)
                //.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Cookie", "token=" + token)
                .body(jsonBody)
                .log().all()
        .when()
                .put(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(200)
                .body("firstname", is("Ronny"))
                .body("lastname", is("Brown"))
                .body("totalprice", is(200))
                .body("depositpaid", is(false))
                .body( "bookingdates.checkin", is("2024-01-10"))
        ;


    }   // fim do put booking

    @Test
    @Order(5)
    public void testarExcluirBooking() throws IOException { // inicio do delete booking

        testarCreateBooking();  // Chamando o método que cria o booking
        //testarGerarToken();


        given()
                .contentType(ct)
                //.header("Cookie", "token=" + token)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .log().all()
        .when()
                .delete(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(201)
        ;



    }   // fim do delete user

    @Test
    @Order(6)
    public void testarConsultarBookingIds() throws IOException {


       /* String firstname = "Ronyy";
        String lastname = "Brown";
        String checkin = "2024-01-01";
        String checkout = "2024-01-10";*/

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "booking")
        .then()
                .log().all()
                .statusCode(200)

        ;

    }
    @ParameterizedTest
    @Order(7)
    @CsvFileSource(resources = "/csv/massaTesteBooking.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarCreateBookingCSV(

        // inicio do post bookingCSV
        String firstname,
        String lastname,
        double totalprice,
        Boolean depositpaid,
        String checkin,
        String checkout,
        String additionalneeds) {

        EntityBooker booker = new EntityBooker(); // instancia a classe entity booker

        booker.firstname = firstname;
        booker.lastname = lastname;
        booker.totalprice = totalprice;
        booker.depositpaid = depositpaid;

        BookingDates bookingDates = new BookingDates();
        bookingDates.checkin = checkin;
        bookingDates.checkout = checkout;

        booker.bookingdates = bookingDates;
        booker.additionalneeds = additionalneeds;

        Gson gson = new Gson(); // instancia a classe gson
        String jsonBody = gson.toJson(booker);

        //realizar teste
                given()
                        .contentType(ct)
                        .log().all()
                        .body(jsonBody)
                .when()
                        .post(uri + "booking")
                .then()
                        .log().all()
                        .statusCode(200)
                        //.body("booking.firstname", is("Ronyy"))
                        //.body("booking.totalprice", is(400))
                        //.body("booking.depositpaid", is(true))
                        //.body("booking.bookingdates.checkin", is("2024-01-01"))
                ;

    }   // fim do post booking CSV


} // fim da classe
