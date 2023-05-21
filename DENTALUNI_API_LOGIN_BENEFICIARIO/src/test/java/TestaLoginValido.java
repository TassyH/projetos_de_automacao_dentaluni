
import data.UsuarioData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")
public class TestaLoginValido {
    private String mgs;

    @BeforeEach
    public void beforeEach(){
        baseURI = "https://sio-teste.fswise.com.br/UnioTeste";
        basePath = "/api/";

    }

    @Test
    @DisplayName("testando o login valido - retorna nome dos titulares")
    public void testaLoginValidoRetornaTitular(){

        List <String> logins = given().contentType(ContentType.JSON)
                .body(UsuarioData.loginUsario("92192841035", "92192841035"))
                .when().post("beneficiarios/login")
                .then().log().ifValidationFails() .assertThat()
                .body("status", equalTo(true))
                .extract().path("logins.A012_NOME_COMPLETO");

        System.out.println(logins);
        System.out.println("o login é valido");
    }

    @Test
    @DisplayName("testando o login valido - retorna nome dos dependentes")
    public void testaLoginValidoRetornaDependente(){

        List <String> dependente = given().contentType(ContentType.JSON)
                .body(UsuarioData.loginUsario("92192841035", "92192841035"))
                .when().post("beneficiarios/login")
                .then().log().ifValidationFails() .assertThat()
                .body("status", equalTo(true))
                .extract().path("logins.DEPENDENTES.A012_NOME_COMPLETO");

        System.out.println(dependente);
        System.out.println("o login é valido");
    }
    }


