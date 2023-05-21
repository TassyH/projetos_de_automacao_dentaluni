package dataFactory;

import modulos.pojo.PojoUsuario;

public class UsuarioDataFactory {
    public static PojoUsuario criaUsuario(){
        PojoUsuario userToken = new PojoUsuario();
        userToken.setUsuarioLogin("admin");
        userToken.setUsuarioSenha("admin");
        return userToken;
    }
}
