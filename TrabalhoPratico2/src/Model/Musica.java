package Model;

import java.util.ArrayList;

public class Musica extends MidiaReproducao{
    ArrayList<Pessoa> autores;
    ArrayList<Pessoa> interpretes;

    public Musica(
        String caminhoArquivo,
        String titulo,
        String descricao,
        String genero,
        String idioma,
        String duracao,
        int ano,
        ArrayList<Pessoa> autores,
        ArrayList<Pessoa> interpretes
    ){
        super(caminhoArquivo, titulo, descricao, genero, idioma, duracao, ano);
        this.autores = autores;
        this.interpretes = interpretes;
    }

    public ArrayList<Pessoa> getAutores() { return autores; }
    public ArrayList<Pessoa> getInterpretes() { return interpretes; }

    public void setAutores(ArrayList<Pessoa> autores) { this.autores = autores; }
    public void setInterpretes(ArrayList<Pessoa> interpretes) { this.interpretes = interpretes; }
}
