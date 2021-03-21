package Model;

import java.util.ArrayList;

public class Filme extends MidiaReproducao{
    private Pessoa diretor;
    private ArrayList<Pessoa> autoresPrincipais;

    public Filme(
        String caminhoArquivo,
        String titulo,
        String descricao,
        String genero,
        String idioma,
        String duracao,
        int ano,
        Pessoa diretor,
        ArrayList<Pessoa> autoresPrincipais
    ){
        super(caminhoArquivo, titulo, descricao, genero, idioma, duracao, ano);
        this.diretor = diretor;
        this.autoresPrincipais = autoresPrincipais;
    }

    public Pessoa getDiretor() { return diretor; }
    public ArrayList<Pessoa> getAutoresPrincipais() { return autoresPrincipais; }

    public void setDiretor(Pessoa diretor) { this.diretor = diretor; }
    public void setAutoresPrincipais(ArrayList<Pessoa> autoresPrincipais) { this.autoresPrincipais = autoresPrincipais; }
}
