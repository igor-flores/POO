package Model;

import java.util.ArrayList;
import java.util.Date;

public class Filme extends MidiaReproducao{
    private String diretor;

    public Filme(
        String caminhoArquivo,
        String titulo,
        String descricao,
        String genero,
        String idioma,
        String duracao,
        Date ano,
        String diretor,
        ArrayList<String> autoresPrincipais
    ){
        super(caminhoArquivo, titulo, descricao, genero, idioma, duracao, ano, autoresPrincipais);
        this.diretor = diretor;
    }

    public String getDiretor() { return diretor; }

    public void setDiretor(String diretor) { this.diretor = diretor; }

    public String toString(){
        return this.getData() + " - " + this.titulo;
    }
}
