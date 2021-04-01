package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class MidiaReproducao extends Midia{
    private String genero, idioma, duracao;
    ArrayList<String> autores;
    MidiaReproducao(
        String caminhoArquivo,
        String titulo,
        String descricao,
        String genero,
        String idioma,
        String duracao,
        Date ano,
        ArrayList<String> autores
    ){
        super(caminhoArquivo, titulo, descricao, ano);
        this.genero = genero;
        this.idioma = idioma;
        this.duracao = duracao;
        this.autores = autores;
    }

    public String getGenero() { return genero; }
    public String getIdioma() { return idioma; }
    public String getDuracao() { return duracao; }
    public ArrayList<String> getAutores(){ return autores; }
    @Override public String getData(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(this.data);
    }

    public void setGenero(String genero) { this.genero = genero; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setDuracao(String duracao) { this.duracao = duracao; }
    public void setAutores(ArrayList<String> autores) { this.autores = autores; }
    public void setData(String ano) throws ParseException {
        this.data = new SimpleDateFormat("yyyy").parse(ano);
    }
}
