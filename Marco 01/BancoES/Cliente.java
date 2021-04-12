public class Cliente{
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public String getEndereco(){
        return endereco;
    }
    public String getTelefone(){
        return telefone;
    }
    public String toString(){
        return(
            "{" +
            "\n    Nome: " + nome +
            "\n    CPF: " + cpf +
            "\n    Endereço: " + endereco +
            "\n    Telefone: " + telefone +
            "\n}"
        );
    }
}