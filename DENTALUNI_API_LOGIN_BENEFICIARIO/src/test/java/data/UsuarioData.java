package data;

import pojo.UsuarioPojo;

public class UsuarioData extends UsuarioPojo {
    public static UsuarioPojo loginUsario(String senha, String login){
        UsuarioPojo usuarioPojo = new UsuarioPojo();
        usuarioPojo.setLogin(login);
        usuarioPojo.setSenha(senha);
        return usuarioPojo;


    }

    public static UsuarioPojo loginUsarioComNumeroCartao(String cartao, String senha){
        UsuarioPojo usuarioPojo = new UsuarioPojo();
        usuarioPojo.setLogin(cartao);
        usuarioPojo.setSenha(senha);
        return usuarioPojo;
    }

    public static UsuarioPojo loginUsarioSemPassarLogin(String login, String senha){
        UsuarioPojo usuarioPojo = new UsuarioPojo();
        usuarioPojo.setLogin(login);
        usuarioPojo.setSenha(senha);
        return usuarioPojo;
    }

    public static UsuarioPojo loginUsarioSemPassarSenha(String login, String senha){
        UsuarioPojo usuarioPojo = new UsuarioPojo();
        usuarioPojo.setLogin(login);
        usuarioPojo.setSenha(senha);

        return usuarioPojo;
    }
}
