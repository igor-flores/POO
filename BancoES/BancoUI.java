import java.util.Scanner;

public class BancoUI{
    private Banco banco;

    public BancoUI(){
        this.banco = new Banco();
    }

    public void abrirConta(){
        String nome, cpf;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Nome: ");
        nome = input.next();
        input.nextLine();
        
        System.out.print("CPF: ");
        cpf = input.next();
    
        try{
            Conta novaConta = new Conta(nome, cpf);
            banco.abrirConta(novaConta);
            System.out.println("Conta criada! Conta nº #" + novaConta.getNumero());

        }catch(Exception e){
            System.out.println("Conta não criada");
        }
    }

    public void sacar(){
        Scanner input = new Scanner(System.in);
        int numero;
        System.out.print("Numero da conta: ");
        numero = input.nextInt();
        input.nextLine();

        System.out.print("Valor de saque: ");
        if(banco.sacar(numero, input.nextDouble())){
            System.out.print("Valor sacado! :D");
        }else{
            System.out.print("Conta não encontrada ou Valor indisponível para saque :/");
        }
        
    }

    public void depositar(){
        Scanner input = new Scanner(System.in);
        int numero;
        System.out.print("Numero da conta: ");
        numero = input.nextInt();
        input.nextLine();

        System.out.print("Valor de depósito: ");
        if(banco.depositar(numero, input.nextDouble())){
            System.out.print("Valor depositado! :D");
        }else{
            System.out.print("Erro");
        }
    }

    public void encerrarConta(){
        Scanner input = new Scanner(System.in);
        System.out.print("Numero da conta: ");
        if(banco.encerrarConta(input.nextInt())){
            System.out.print("Conta encerrada! :/");
        }else{
            System.out.print("Erro");
        }
    }

	public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        BancoUI b = new BancoUI();
        int e;
        do{
            System.out.print(
                "\n[1] Abrir conta" + 
                "\n[2] Sacar" + 
                "\n[3] Depositar" + 
                "\n[4] Encerrar conta" + 
                "\n[5] toString" +
                "\n[0] Sair" +
                "\nInsira a opção desejada: "
            );
            e = input.nextInt();
            switch(e){
                case 1: b.abrirConta(); break;
                case 2: b.sacar(); break;
                case 3: b.depositar(); break;
                case 4: b.encerrarConta(); break;
                case 5: System.out.println(b.banco.toString()); break;
                case 0: System.out.println("Programa encerrado"); break;
                default: System.out.println("Opção Inválida :/"); break;
            }
        }while(e != 0);
        input.close();
        
	}
}