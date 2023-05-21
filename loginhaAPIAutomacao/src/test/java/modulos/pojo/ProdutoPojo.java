package modulos.pojo;

import java.util.List;

public class ProdutoPojo {
    private String produtoNome;
    private List<String> produtoCores;
    private List <ComponentePojo> componentePojo;
    private double produtoValor;

    public String getProdutoUrlMock() {
        return produtoUrlMock;
    }

    public void setProdutoUrlMock(String produtoUrlMock) {
        this.produtoUrlMock = produtoUrlMock;
    }

    private String  produtoUrlMock;


    public List<String> getProdutoCores() {
        return produtoCores;
    }

    public void setProdutoCores(List<String> produtoCores) {
        this.produtoCores = produtoCores;
    }

    public List<ComponentePojo> getComponentePojo() {
        return componentePojo;
    }

    public void setComponentePojo(List<ComponentePojo> componentePojo) {
        this.componentePojo = componentePojo;
    }



    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public double getProdutoValor() {
        return produtoValor;
    }

    public void setProdutoValor(double produtoValor) {
        this.produtoValor = produtoValor;
    }
}
