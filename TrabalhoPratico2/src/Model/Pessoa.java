package Model;

public class Pessoa {
    private static int totalPessoas = 0;
    private final int codigo;
    private String nome;

    public Pessoa(String nome){
        this.codigo = 1000 + totalPessoas;
        this.nome = nome;
        totalPessoas++;
    }

    public static int getTotalPessoas() { return totalPessoas; }
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }
}
