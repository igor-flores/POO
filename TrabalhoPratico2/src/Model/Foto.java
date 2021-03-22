package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Foto extends Midia{
    private Pessoa fotografo;
    private String local;
    private ArrayList<Pessoa> pessoas;
    private Date data;

    public Foto(
        String caminhoArquivo,
        String titulo,
        String descricao,
        Date data,
        Pessoa fotografo,
        String local,
        ArrayList<Pessoa> pessoas
    ){
        super(caminhoArquivo, titulo, descricao, data);
        this.fotografo = fotografo;
        this.local = local;
        this.pessoas = pessoas;
        this.data = new Date();
    }

    public Pessoa getFotografo() { return fotografo; }
    public String getLocal() { return local; }
    public ArrayList<Pessoa> getPessoas() { return pessoas; }

    public void setFotografo(Pessoa fotografo) { this.fotografo = fotografo; }
    public void setLocal(String local) { this.local = local; }
    public void setPessoas(ArrayList<Pessoa> pessoas) { this.pessoas = pessoas; }

    public String toString(){
        return  getData() + " - " + this.titulo;
    }
}
