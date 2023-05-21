package dataFactory;

import modulos.pojo.ComponentePojo;
import modulos.pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criaProdutoComValorIgualA(double valor){
        ProdutoPojo produtoPojo = new ProdutoPojo();
        produtoPojo.setProdutoNome("Escova de cabelo");
        produtoPojo.setProdutoValor(0);
        produtoPojo.setProdutoUrlMock("");
        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");
        produtoPojo.setProdutoCores(cores);
        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Escova de Cabelo");
        componente.setComponenteQuantidade(3);
        componentes.add(componente);

        produtoPojo.setComponentePojo(componentes);

        return produtoPojo;
    }
        }
