package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import modulos.pojo.ComponentePojo;
import modulos.pojo.PojoUsuario;
import modulos.pojo.ProdutoPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Resources;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach// before each é = faça algo antes de ir para o teste
    public void beforeEach(){
        // configurado os dados da api da loginha
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        // obter o token do user admin

        this.token =
            given()// onde eu vou passar os parametros para poder fazer a requisição
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criaUsuario())
            .when()// onde eu coloco qual metodo eu vou usar
                .post("v2/login")
            .then()// onde eu vou dizer o que ele tem que fazer com a informação que o when () gerou
                .extract()
                    .path("data.token");
    }

    @Test
    @DisplayName("Validar colocar valor zerado dos campo de valor do produto")
    public void testeValidarValorZeradoDeProduto(){


        // tentar inserir um produto com o valor 0.0 e validar se mostrar a mensgem de erro e o status cod 422
        given()
                .contentType(ContentType.JSON)
                .headers("token", this.token)
                .body(ProdutoDataFactory.criaProdutoComValorIgualA(0.00))
        .when()
                .post("v2/produtos")
        .then().log().all()
                .assertThat()
                .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);

    }

    @Test
    @DisplayName("Validar colocar valor limite de 7.000.01 dos campo de valor do produto")
    public void testeValidarValorLimiteDeProduto(){


        // tentar inserir um produto com o valor max e validar se mostrar a mensgem de erro e o status cod 422
        given()
                .contentType(ContentType.JSON)
                .headers("token", this.token)
                .body(ProdutoDataFactory.criaProdutoComValorIgualA(7000.01))
                .when()
                .post("v2/produtos")
                .then()
                .assertThat()
                .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);

    }
}
