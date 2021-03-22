package Model;

import java.util.ArrayList;

public class BancoMidia {
    private ArrayList<Midia> fotos;
    private ArrayList<Midia> filmes;
    private ArrayList<Midia> musicas;

    public BancoMidia(){
        fotos = new ArrayList<>();
        filmes = new ArrayList<>();
        musicas = new ArrayList<>();
    }

    public ArrayList<Midia> getFotos() { return fotos; }
    public ArrayList<Midia> getFilmes() { return filmes; }
    public ArrayList<Midia> getMusicas() { return musicas; }

    public void setFotos(ArrayList<Midia> fotos) { this.fotos = fotos; }
    public void setFilmes(ArrayList<Midia> filmes) { this.filmes = filmes; }
    public void setMusicas(ArrayList<Midia> musicas) { this.musicas = musicas; }

    public ArrayList<String> getGeneroFilme(){
        ArrayList<String> generos = new ArrayList<>();

        for (Midia m: filmes) {
            Filme f = (Filme) m;
            generos.add(f.getGenero());
        }

        return generos;
    }
    public ArrayList<String> getGeneroMusica(){
        ArrayList<String> generos = new ArrayList<>();

        for (Midia m: musicas) {
            Musica musica = (Musica) m;
            generos.add(musica.getGenero());
        }

        return generos;
    }
}
