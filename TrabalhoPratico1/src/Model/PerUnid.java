package Model;

public class PerUnid extends Produto {

    public PerUnid(String nome, String descricao, float preco, float quantidade){
        super(nome, descricao, preco, quantidade);
        this.quantidade = quantidade;
    }

    @Override
    public void setQuantidade(float quantidade){
        if(quantidade % 1 == 0) {
            this.quantidade = quantidade;
        }
    }


}
