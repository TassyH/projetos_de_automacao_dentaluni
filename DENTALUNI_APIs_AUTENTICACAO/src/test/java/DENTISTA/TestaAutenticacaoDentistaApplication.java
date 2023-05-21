package DENTISTA; /**
 *
 * @author: Tassy Helen Lazamé
 * @Version: 1.0.0
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest - API AUTENTICA DENTISTA")
public class TestaAutenticacaoDentistaApplication {

    @BeforeEach
    public void beforeEach() {
        baseURI = "https://sio-teste.fswise.com.br/UnioTeste";

    }

    @Test
    @DisplayName("Testa autenticação valida")
    public void TestaAutenticacaoValida() {

        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "142")
                        .queryParam("senha", "1234")
                        .queryParam("uf","PR")
                .when()
                        .get("AutenticaDentistaSiteServlet")
                .then()
                        .log().body()
                        .assertThat().body("statusBusca", equalTo(true))
                        .extract().response().path("accessToken");

        System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
        System.out.println("TOKEN EXTRAÍDO DO RESPONSE DO JSON: "+retorno);
        System.out.println("MENSAGEM DO QA: Login com os parametros e valores válidos!");
    }
    @Test
    @DisplayName("Testa autenticação invalida - dados do login invalidos (uf, senha, login)")
    public void testaAutenticacaoDadosDoLoginIncorretos() {

        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "14211")
                        .queryParam("senha", "1234")
                        .queryParam("uf","PR")
                .when()
                        .get("AutenticaDentistaSiteServlet")
                .then()
                        .log().body()
                        .assertThat().body("mensagem", equalTo("Login inválido!"))
                        .extract().path("mensagem");

        System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
        System.out.println("MENSAGEM DE RETORNO EXTRAÍDA DO JSON: "+retorno);
        System.out.println("MENSAGEM DO QA: Tentativa de login passando os dados ERRADOS! ");
    }

    @Test
    @DisplayName("Testa autenticação inválida - tentativa de login sem passar o valor de um ou mais parametro obrigatório")
    public void TestaAutenticacaSemPassarValorDoParametro() {

        String retorno =
                given().contentType(ContentType.JSON)
                        .queryParam("login", "")
                        .queryParam("senha", "1234")
                        .queryParam("uf","PR")
                .when()
                        .get("AutenticaDentistaSiteServlet")
                .then()
                        .log().body()
                        .assertThat().body("mensagem", equalTo("Informe parâmetros válidos para o login de dentista. Parâmetros esperados: [login], [senha], [uf]"))
                        .extract().path("mensagem");

        System.out.println("---------------------PRINTA NO CONSOLE---------------------------");
        System.out.println("MENSAGEM DE RETORNO EXTRAÍDA DO JSON: ["+retorno+"]");
        System.out.println("MENSAGEM DO QA: Tentativa de login sem passar o valor de algum parametro");

    }
}