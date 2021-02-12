import java.util.ArrayList;

public class Autor{
    private static int totalAutores = 0;
    private final int codigo;
    private String nome;
    private ArrayList<Livro> livros;

    public Autor(String n){
        totalAutores++;
        this.codigo = 1000 + totalAutores;
        this.nome = n;
        this.livros = new ArrayList<Livro>();
    }

    public int getTotalAutores(){return totalAutores;}
    public int getCodigo(){return this.codigo;}  
    public String getNome(){return this.nome;}
    public void setNome(String n){this.nome = n;}
    public ArrayList<Livro> getLivros(){return this.livros;}
    public void setLivro(Livro l){this.livros.add(l);}
    public boolean rmLivro(Livro rmL){
        for(Livro l : livros){
            if(l.getCodigo() == rmL.getCodigo()){
                livros.remove(l);
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String tituloL = "";
        for(Livro l : this.livros)
            tituloL += l.getTitulo() + " ";
        
        return (
            "\nTotal de Autores: " + totalAutores +
            "\n#" + this.codigo +
            "\nAutor: " + this.nome +
            "\nLivros Publicados: " + tituloL + 
            "\n"
        );
    }
}