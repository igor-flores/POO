import java.util.ArrayList;

public class Livro {
    private static int totalLivros = 0;
    private final int codigo;
    private String titulo;
    private Editora editora;
    private ArrayList<Autor> autores;
    
    public Livro(String t, Editora e, ArrayList<Autor> a){
        totalLivros++;
        this.codigo = 1000 + totalLivros;
        this.titulo = t;
        this.editora = e;
        this.autores = a;

    }

    public int getTotalLivros(){return totalLivros;}
    public int getCodigo(){return this.codigo;}  
    public String getTitulo(){return this.titulo;}
    public void setTitulo(String t){this.titulo = t;}
    public void setEditora(Editora e){this.editora = e;}
    public Editora getEditora(){return this.editora;}
    public void setAutor(Autor a){this.autores.add(a);}
    public boolean rmAutor(Autor rmA){
        for(Autor a : autores){
            if(a.getCodigo() == rmA.getCodigo()){
                this.autores.remove(a);
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
        String nAutores = "";
        for(Autor a : this.autores)
            nAutores += a.getNome() + " ";

        return (
            "\nTotal de Livros: " + totalLivros +
            "\n#" + this.codigo +
            "\nTítulo: " + this.titulo +
            "\nEditora: " + this.editora.getNome() +
            "\nAutor(es): " + nAutores
        );
    }

}
