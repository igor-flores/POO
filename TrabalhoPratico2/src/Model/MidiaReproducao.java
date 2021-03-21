package Model;

abstract class MidiaReproducao extends Midia{
    private String genero, idioma, duracao;
    private int ano;
    MidiaReproducao(
        String caminhoArquivo,
        String titulo,
        String descricao,
        String genero,
        String idioma,
        String duracao,
        int ano
    ){
        super(caminhoArquivo, titulo, descricao);
        this.genero = genero;
        this.idioma = idioma;
        this.duracao = duracao;
        this.ano = ano;
    }

    public String getGenero() { return genero; }
    public String getIdioma() { return idioma; }
    public String getDuracao() { return duracao; }
    public int getAno() { return ano; }

    public void setGenero(String genero) { this.genero = genero; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setDuracao(String duracao) { this.duracao = duracao; }
    public void setAno(int ano) { this.ano = ano; }
}
