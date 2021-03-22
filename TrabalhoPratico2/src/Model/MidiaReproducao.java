package Model;

import java.util.Date;

abstract class MidiaReproducao extends Midia{
    private String genero, idioma, duracao;
    MidiaReproducao(
        String caminhoArquivo,
        String titulo,
        String descricao,
        String genero,
        String idioma,
        String duracao,
        Date ano
    ){
        super(caminhoArquivo, titulo, descricao, ano);
        this.genero = genero;
        this.idioma = idioma;
        this.duracao = duracao;
    }

    public String getGenero() { return genero; }
    public String getIdioma() { return idioma; }
    public String getDuracao() { return duracao; }

    public void setGenero(String genero) { this.genero = genero; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setDuracao(String duracao) { this.duracao = duracao; }
}
