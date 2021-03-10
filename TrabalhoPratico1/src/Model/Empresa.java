package Model;

import java.util.ArrayList;

public class Empresa {
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
        this.nome = n;
        this.email = e;
        this.senha = s;
        this.produtos = p;
        this.notasFiscais = new ArrayList<>();
    }

    /** Getter e Setters **/
    public ArrayList<Produto> getProdutos() { return produtos; }
    public ArrayList<NotaFiscal> getNotasFiscais() { return notasFiscais; }
    public void setProduto(Produto p) {this.produtos.add(p); }

    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getNome() { return this.nome; }
}
