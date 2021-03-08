package Model;

public class Produto {
    private static int quantidadeProdutos = 0;
    protected int codigo;
    protected String nome, descricao;
    protected float preco, quantidade;

    public Produto(String nome, String descricao, float preco, float quantidade){
        this.codigo = 1000 + quantidadeProdutos;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        quantidadeProdutos++;
    }

    /** Getter e Setters **/
    public String getNome(){ return this.nome; }
    public int getCodigo(){ return this.codigo; }
    public String getDescricao(){ return this.descricao; }
    public float getPreco(){ return this.preco; }

    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(float preco) { this.preco = preco; }
    public void setQuantidade(float quantidade){
        this.quantidade = quantidade;
    }
    public float getQuantidade(){return this.quantidade; }

    public String toString(){
        return "#" + this.codigo + " - " + this.nome + "(Em estoque: " + this.quantidade + ")";
    }
}
