package ASSOCIADO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest - API AUTENTICA ASSOCIADO (BENEFICIÁRIO)")
public class TestaAutenticacaoAssociadoApplication {

    @BeforeEach
    public void beforeEach() {
        baseURI = "https://sio-teste.fswise.com.br/UnioTeste";
    }
    @Test
    @DisplayName("Testa autenticação valida")
    public void testaAutenticacaoAssociadoValido() {

        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "00202544431600000101")
                        .queryParam("senha", "1234")
                        .when()
                        .get("AutenticaAssociadoSiteServlet")
                        .then()
                        .log().body()
                        .assertThat().body("statusBusca", equalTo(true))
                        .extract().response().path("accessToken", "mensagem");

        System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
        System.out.println("TOKEN EXTRAÍDO DO RESPONSE DO JSON: "+retorno);
        System.out.println("MENSAGEM DO QA: Login com os parametros e valores válidos!");
    }

    @Test
    @DisplayName("Testa autenticação inválida: testando passar valores incorretos nos parametros (login ou senha)")
    public void testaAutenticacaoAssociadoPassandoValoresInvalidos() {
        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "002025444316000003011")
                        .queryParam("senha", "12345")
                .when()
                        .get("AutenticaAssociadoSiteServlet")
                .then()
                        .log().body()
                        .assertThat().body("mensagem", equalTo("Login inválido!"))
                        .extract().response().path("mensagem");

            System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
            System.out.println("MENSAGEM EXTRAÍDA DO JSON: " + retorno);
            System.out.println("MENSAGEM DO QA: Login com os parametros e valores INVÁLIDOS");

    }

    @Test
    @DisplayName("Testa autenticação inválida: testando SEM passar valores nos parametros (login ou senha)")
    public void testaAutenticacaoAssociadoSemPassarValorNosParametros() {
            String retorno =
                    given().contentType(ContentType.JSON)
                            .queryParam("login", "00202544431600000301")
                            .queryParam("senha", "")
                            .when()
                            .get("AutenticaAssociadoSiteServlet")
                            .then()
                            .log().body()
                            .assertThat().body("mensagem", equalTo("Informe parâmetros válidos para o login de associado. Parâmetros esperados: [login], [senha]"))
                            .extract().response().path("mensagem");

                System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
                System.out.println("MENSAGEM ESPERADA DO JSON: "+retorno);
                System.out.println("MENSAGEM DO QA: Login SEM PASSAR OS VALORES DOS PARAMETROS");

    }


}
