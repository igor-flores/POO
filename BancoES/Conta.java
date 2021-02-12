public class Conta{
	private static int totalDeContas = 0;
	private final int numero;
	private double saldo;
	private double limite;
	private Cliente titular;

	public Conta(String nomeTitular, String cpf) {
		Conta.totalDeContas++;
		this.titular = new Cliente(nomeTitular, cpf);
		this.saldo = 0;
		this.limite = 1000;
		this.numero = 1000 + totalDeContas;
	}

	public int getNumero(){
		return numero;
	}
	
	public Cliente getTitular(){
		return titular;
	}
	
	public double getSaldo(){
		return saldo;
	}
	
	public double getLimite(){
		return limite;
	}
	
	public static int getTotalDeContas(){
		return totalDeContas;
		
	}
	
	public void setSaldo(double saldo){
		this.saldo = saldo;
	}
	
	public void setTitular(Cliente titular){
		this.titular = titular;
    }

	public void setLimite(double l){
		limite = l;
	}
	
	public void deposita(double valor){
		saldo = saldo + valor;
		
	}
	
	public void saca(double valor){
		if(valor <= (saldo + limite)){
			saldo = saldo - valor;
			System.out.println("Saque realizado com sucesso");
		}
		else{
			System.out.println("Saldo insuficiente");
		}
	}
	
	public String toString(){
		return (
			"\n" +
			"\nNumero = " + numero + 
			"\nTitular: " + titular.toString() + 
			"\nSaldo: " + saldo + 
			"\nLimite: " + limite + 
			"\nTotal de Contas: " + totalDeContas
		);
	}
	
}