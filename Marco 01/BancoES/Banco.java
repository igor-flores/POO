import java.util.ArrayList;

public class Banco{
    private String nome;
    public ArrayList<Conta> contas;
	
    public Banco(){
        this.nome = "Banco ES";
        this.contas = new ArrayList<Conta>();
    }

    public void abrirConta(Conta conta){
        contas.add(conta);
    }

    public boolean sacar(int numero, double valor){
        for (Conta c : contas){
            if(c.getNumero() == numero){
                if(c.getSaldo() >= valor){
                    c.setSaldo(c.getSaldo() - valor);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    public boolean depositar(int numero, double valor){
        for (Conta c : contas){
            if(c.getNumero() == numero){
                c.setSaldo(c.getSaldo() + valor);
                return true;
            }
        }
        return false;
    }

    public boolean encerrarConta(int numero){
        for (Conta c : contas){
            if(c.getNumero() == numero){
                contas.remove(c);
                return true;
            }
        }
        return false;
    }

    public String getNome(){
        return this.nome;
    }
    
    public Conta getConta(int numero){
        for(Conta c : contas){
            if(c.getNumero() == numero)
                return c;
        }
        return null;
    }

    public String toString(){
        return (
            "\nNome: " + nome +
            "\nConta: " + contas.toString()
        );
    }
}