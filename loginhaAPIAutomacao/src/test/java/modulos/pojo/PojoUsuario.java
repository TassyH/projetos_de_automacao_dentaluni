package modulos.pojo;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class PojoUsuario {
    private String usuarioLogin;
    private String usuarioSenha;


    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }
}
