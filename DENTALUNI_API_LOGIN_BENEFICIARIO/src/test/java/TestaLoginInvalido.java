import com.sun.deploy.association.AssociationAlreadyRegisteredException;
import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import data.UsuarioData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.opentest4j.AssertionFailedError;
import pojo.UsuarioPojo;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest - teste de informações erradas, tem que retornar erro")
public class TestaLoginInvalido {
    private String mgs;

    @BeforeEach
    public void beforeEach(){
        baseURI = "https://sio-teste.fswise.com.br/UnioTeste";
        basePath = "/api/";

    }
    @Test
    @DisplayName("testando o login invalido")
    public void testaLoginInvalido(){
        this.mgs =
                given().contentType(ContentType.JSON)
                        .body(UsuarioData.loginUsario("9219284111035", "92192841035"))
                        .when().post("beneficiarios/login")
                        .then().log().all().assertThat()
                        .body("msg", equalTo("Login inválido"))
                        .extract().path("data.msg");

        System.out.println("os dados passdos no campo de login ou senha estão errados. Login invalido, o teste tem que passar");
    //    throw new RuntimeException("o teste passou");
    }

    @Test
    @DisplayName("testando senha invalida")
    public void testaSenhaInvalida(){


        this.mgs =
        given().contentType(ContentType.JSON)
                .body(UsuarioData.loginUsarioComNumeroCartao("00202544485800000101","3123123"))
            .when().post("beneficiarios/login")
                .then().log().all().assertThat()
                .body("msg", equalTo("Login inválido"))
                .extract().path("msg");

        System.out.println(mgs);
        System.out.println("a senha está errada, o benef nao pode logar. O teste tem que passar");
        // throw new RuntimeException("o teste passou");

    }

    @Test
    @DisplayName("testando logar sem passar o valor da variavel Login")
    public void testaLogarSemPassarOLogin(){

        this.mgs =
                given().contentType(ContentType.JSON)
                        .body(UsuarioData.loginUsarioSemPassarLogin("", "1234"))
                        .when().post("beneficiarios/login")
                        .then().log().all().assertThat()
                        .body("msg", equalTo("Login inválido"))
                        .extract().path("msg");

        System.out.println(mgs);
        System.out.println("nao tem o valor da variavel login, o benef nao pode logar. O teste tem que passar");
        // throw new RuntimeException("o teste passou");
    }

    @Test
    @DisplayName("testando logar sem passar o valor da variavel Login")
    public void testaLogarSemPassarASenha(){


    this.mgs =
                given().contentType(ContentType.JSON)
                        .body(UsuarioData.loginUsarioSemPassarSenha("92192841035",""))
                        .when().post("beneficiarios/login")
                        .then().log().all().assertThat()
                        .body("msg", equalTo("Login inválido"))
                        .extract().path("msg");

        System.out.println(mgs);
        System.out.println("nao tem o valor da variavel SENHA, o benef nao pode logar. O teste tem que passar");
        // throw new RuntimeException("o teste passou");
    }
}
