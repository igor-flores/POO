public class Editora {
    private static int totalEditoras = 0;
    private final int codigo;
    private String nome;

    public Editora(String n){
        totalEditoras++;
        this.codigo = 1000 + totalEditoras;
        this.nome = n;
    }

    public int getTotalEditoras(){return totalEditoras;}
    public int getCodigo(){return this.codigo;}  
    public String getNome(){return this.nome;}
    public void setNome(String n){this.nome = n;}

    public String toString(){
        return (
            "\nTotal de Autores: " + totalEditoras +
            "\n#" + this.codigo +
            "\nAutor: " + this.nome
        );
    }
}
