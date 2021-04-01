package Model;

import java.util.ArrayList;
import java.util.Date;

public class Foto extends Midia{
    private String fotografo;
    private String local;
    private ArrayList<String> pessoas;

    public Foto(
        String caminhoArquivo,
        String titulo,
        String descricao,
        Date data,
        String fotografo,
        String local,
        ArrayList<String> pessoas
    ){
        super(caminhoArquivo, titulo, descricao, data);
        this.fotografo = fotografo;
        this.local = local;
        this.pessoas = pessoas;
        this.data = new Date();
    }

    public String getFotografo() { return fotografo; }
    public String getLocal() { return local; }
    public ArrayList<String> getPessoas() { return pessoas; }

    public void setFotografo(String fotografo) { this.fotografo = fotografo; }
    public void setLocal(String local) { this.local = local; }
    public void setPessoas(ArrayList<String> Strings) { this.pessoas = Strings; }

    @Override
    public String toString(){
        return  getData() + " - " + this.titulo;
    }
}
