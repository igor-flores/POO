package Model;

import java.util.ArrayList;

public class Empresa {
    private static int quantidadeEmpresas = 0;
    private final int codigo;
    private final String nome, email, senha;
    private final ArrayList<Produto> produtos;
    private final ArrayList<NotaFiscal> notasFiscais;

    /**
     * Construct
     * Caso não seja cadastrada uma empresa já com produtos, envia uma lista vazia
     * @param n nome da Empresa
     * @param e email da Empresa
     * @param s senha da Empresa
     */
    public Empresa(String n, String e, String s){
        this(n, e, s, new ArrayList<>());
    }

    public Empresa(String n, String e, String s, ArrayList<Produto> p){
        this.codigo = 1000 + quantidadeEmpresas;
        this.nome = n;
        this.email = e;
        this.senha = s;
        this.produtos = p;
        this.notasFiscais = new ArrayList<>();

        quantidadeEmpresas++;
    }

    /**
     * Pega um produto em específico da lista
     * @param codigo id do produto
     * @return PerKg ou PerUnid da lista da Empresa
     */
    public Produto getProduto(int codigo){
        for(Produto p : produtos){
            if(p.getCodigo() == codigo)
                return produtos.get(produtos.indexOf(p));
        }
        return null;
    }

    /**
     * Remove um produto a partir de seu id
     * @param codigo id do produto
     * @return true caso exista o produto e seja removido
     */
    public boolean removeProduto(int codigo){
        for(Produto p : produtos){
            if(p.getCodigo() == codigo){
                produtos.remove(p);
                return true;
            }
        }
        return false;
    }

    /** Getter e Setters **/
    public ArrayList<Produto> getProdutos() { return produtos; }
    public ArrayList<NotaFiscal> getNotasFiscais() { return notasFiscais; }
    public void setProduto(Produto p) {this.produtos.add(p); }

    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getNome() { return this.nome; }
    public static int getQuantidadeEmpresas() { return quantidadeEmpresas; }
}
