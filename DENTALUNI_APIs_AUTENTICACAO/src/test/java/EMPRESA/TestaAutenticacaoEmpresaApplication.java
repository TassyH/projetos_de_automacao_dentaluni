package EMPRESA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest - API AUTENTICA EMPRESA")
public class TestaAutenticacaoEmpresaApplication {

    @BeforeEach
    public void beforeEach() {
        baseURI = "https://sio-teste.fswise.com.br/UnioTeste";
    }

    @Test
    @DisplayName("Testa autenticação valida")
    public void testaAutenticacaoEmpresaValida() {

        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "444780")
                        .queryParam("senha", "12345")
                        .when()
                        .get("AutenticaEmpresaSiteServlet")
                        .then()
                        .log().body()
                        .assertThat().body("statusBusca", equalTo(true))
                        .extract().response().path("accessToken");

        System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
        System.out.println("TOKEN EXTRAÍDO DO RESPONSE DO JSON: "+retorno);
        System.out.println("MENSAGEM DO QA: Login com os parametros e valores válidos!");
    }

    @Test
    @DisplayName("Testa autenticação inválida: testando passar valores incorretos nos parametros (login)")
    public void testaAutenticacaoEmpresaPassandoLoginInvalido() {
        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "444782")
                        .queryParam("senha", "12345")
                        .when()
                        .get("AutenticaEmpresaSiteServlet")
                        .then()
                        .log().body()
                        .assertThat().body("mensagem", equalTo("Login inválido"))
                        .extract().response().path("mensagem");

            System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
            System.out.println("MENSAGEM EXTRAÍDA DO JSON: " + retorno);
            System.out.println("MENSAGEM DO QA: Login SEM PASSAR OS PARAMETROS - O TESTE PASSOU!");
    }

    @Test
    @DisplayName("Testa autenticação inválida: testando passar valores incorretos nos parametros (senha)")
    public void testaAutenticacaoEmpresaPassandoSenhaInvalido() {
        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "444780")
                        .queryParam("senha", "1dfd11")
                        .when()
                        .get("AutenticaEmpresaSiteServlet")
                        .then()
                        .log().body()
                        .assertThat().body("mensagem", equalTo("Login inválido"))
                        .extract().response().path("mensagem");

        System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
        System.out.println("MENSAGEM EXTRAÍDA DO JSON: " + retorno);
        System.out.println("MENSAGEM DO QA: Login SEM PASSAR OS PARAMETROS - O TESTE PASSOU!");
    }

    @Test
    @DisplayName("Testa autenticação inválida: testando sem passar os valores dos parametros (login)")
    public void testaAutenticacaoEmpresaSemPassarOParametroLogin(){
          String retorno =
                    given().contentType(ContentType.JSON)
                            .queryParam("login", "")
                            .queryParam("senha", "12345")
                            .when()
                            .get("AutenticaEmpresaSiteServlet")
                            .then()
                            .log().body()
                            .assertThat().body("mensagem", equalTo("Informe parâmetros válidos para o login de empresa. Parâmetros esperados: [login], [senha]"))
                            .extract().response().path("mensagem");

                System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
                System.out.println("MENSAGEM EXTRAÍDA DO JSON: " + retorno);
                System.out.println("MENSAGEM DO QA: Login SEM PASSAR OS PARAMETROS - O TESTE PASSOU!");
    }

    @Test
    @DisplayName("Testa autenticação inválida: testando sem passar o valor do parametro senha")
    public void testaAutenticacaoEmpresaSemPassarOParametroSenha(){
        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "444316")
                        .queryParam("senha", "")
                        .when()
                        .get("AutenticaEmpresaSiteServlet")
                        .then()
                        .log().body()
                        .assertThat().body("mensagem", equalTo("Informe parâmetros válidos para o login de empresa. Parâmetros esperados: [login], [senha]"))
                        .extract().response().path("mensagem");

        System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
        System.out.println("MENSAGEM EXTRAÍDA DO JSON: " + retorno);
        System.out.println("MENSAGEM DO QA: Login SEM PASSAR OS PARAMETROS - O TESTE PASSOU!");
    }

}

