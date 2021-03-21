package Model;

import java.util.ArrayList;
import java.util.Date;

public class Foto extends Midia{
    private String fotografo, local;
    private ArrayList<Pessoa> pessoas;
    private Date data;

    public Foto(
        String caminhoArquivo,
        String titulo,
        String descricao,
        String fotografo,
        String local,
        ArrayList<Pessoa> pessoas
    ){
        super(caminhoArquivo, titulo, descricao);
        this.fotografo = fotografo;
        this.local = local;
        this.pessoas = pessoas;
        this.data = new Date();
    }

    public String getFotografo() { return fotografo; }
    public String getLocal() { return local; }
    public ArrayList<Pessoa> getPessoas() { return pessoas; }
    public Date getData() { return data; }

    public void setFotografo(String fotografo) { this.fotografo = fotografo; }
    public void setLocal(String local) { this.local = local; }
    public void setPessoas(ArrayList<Pessoa> pessoas) { this.pessoas = pessoas; }
    public void setData(Date data) { this.data = data; }
}
