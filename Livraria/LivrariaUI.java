import java.util.ArrayList;
import java.util.Scanner;

public class LivrariaUI {
    private Livraria livraria;
    
    public LivrariaUI(String n){
        this.livraria = new Livraria(n);
    }

    //CREATE
    public void cadastrar(int e){
        Scanner input = new Scanner(System.in);
        String retorno;
        int cod;
        switch(e){
            case 1:{ 
                System.out.print("Nome do autor: ");
                String n = input.next();
                cod = livraria.cadastrarAutor(n);
                retorno = ("Código do autor " + cod);
            }break;
            case 2:{
                System.out.print("Nome da Editora: ");
                String n = input.next();
                cod = livraria.cadastrarEditora(n);
                retorno = ("Código da editora " + cod);
            }break;
            default:{
                System.out.print("Nome do Livro: ");
                String n = input.next();
                System.out.print("Código da Editora: ");
                e = input.nextInt();
                int i;
                ArrayList<Autor> a = new ArrayList<Autor>();
                do{
                    boolean achouAutor = false;
                    System.out.print("Código do Autor: ");
                    i = input.nextInt();
                    if(i == 0)
                        break;
                    for(Autor autor : livraria.getAutores()){
                        if(autor.getCodigo() == i){
                            a.add(autor);
                            achouAutor = true;
                        }
                    }
                    if(!achouAutor)
                        System.out.println("Não foi encontrado este autor :/ ");
                    
                }while((i != 0) || (a.isEmpty()));
                cod = livraria.cadastrarLivro(n, e, a);
                retorno = ("Código do livro " + cod);
            }
        }
        e = 0;
        System.out.print(retorno);
    }

    //READ
    public void ler(int e){
        Scanner input = new Scanner(System.in);
        System.out.print("Informe o código: ");
        int cod = input.nextInt();
        switch(e){
            case 1: System.out.println(livraria.readAutor(cod)); break;
            case 2: System.out.println(livraria.readEditora(cod)); break;
            default: System.out.println(livraria.readLivro(cod)); 
        }
    }

    //UPDATE
    public void atualiza(int e){
        Scanner input = new Scanner(System.in);
        switch(e){
            case 1: {
                System.out.print("Código do autor: ");
                int cod = input.nextInt();
                System.out.println(
                    "\n[1] Atualizar nome" +
                    "\n[2] Adicionar livro ao autor" +
                    "\n[3] Remover livro do autor" +
                    "\n[0] Voltar" +
                    "\nEntre com a opção desejada: "
                );
                int op = input.nextInt();
                switch(op){
                    case 1:{
                        System.out.print("Nome: ");
                        String nome = input.next();
                        if(livraria.atualizaAutor(cod, nome)){
                            System.out.print("Autor atualizado com sucesso! :D");
                        }else{
                            System.out.print("Poxa, houve um erro. :/");
                        }
                    }break;
                    case 2:{ 
                        System.out.print("Código do livro: ");
                        int codLivro = input.nextInt();
                        if(livraria.atualizaAutor(cod, true, codLivro)){
                            System.out.print("Autor atualizado com sucesso! :D");
                        }else{
                            System.out.print("Poxa, houve um erro. :/");
                        }
                    }break;
                    case 3:{ 
                        System.out.print("Código do livro: ");
                        int codLivro = input.nextInt();
                        if(livraria.atualizaAutor(cod, false, codLivro)){
                            System.out.print("Autor atualizado com sucesso! :D");
                        }else{
                            System.out.print("Poxa, houve um erro. :/");
                        }
                    }break;
                }
            }break;
            case 2:{
                System.out.print("Código da Editora: ");
                int cod = input.nextInt();
                System.out.print("Nome: ");
                String nome = input.next();
                if(livraria.atualizaEditora(cod, nome)){
                    System.out.println("Alterado com sucesso! :D");
                }else{
                    System.out.println("Poxa houve algum erro. :/");
                }
            }break;
            case 3:{
                System.out.println("Código do livro: ");
                int cod = input.nextInt();
                System.out.println("Nome do livro: ");
                String nome = input.next();
                if(livraria.atualizaLivro(cod, nome)){
                    System.out.println("Alterado com sucesso! :D");
                }else{
                    System.out.println("Poxa houve algum erro. :/");
                }
            }
        }
    }

    //DELETA
    public void deleta(int e){
        Scanner input = new Scanner(System.in);
        switch(e){
            case 1:{
                System.out.println("Código do autor: ");
                int cod = input.nextInt();
                if(livraria.deletaAutor(cod)){
                    System.out.println("Alterado com sucesso! :D");
                }else{
                    System.out.println("Poxa houve algum erro. :/");
                }
            }break;
            case 2:{
                System.out.println("Código da editora: ");
                int cod = input.nextInt();
                if(livraria.deletaEditora(cod)){
                    System.out.println("Alterado com sucesso! :D");
                }else{
                    System.out.println("Poxa houve algum erro. :/");
                }
            }break;
            case 3:{
                System.out.println("Código do livro: ");
                int cod = input.nextInt();
                if(livraria.deletaLivro(cod)){
                    System.out.println("Alterado com sucesso! :D");
                }else{
                    System.out.println("Poxa houve algum erro. :/");
                }
            }break;
        }
    }

    public void crud(int op1, int op2){
        switch(op1){
            case 1: cadastrar(op2); break;
            case 2: ler(op2); break;
            case 3: atualiza(op2); break;
        }

    }

    public static void main(String [] args){        
        LivrariaUI l = new LivrariaUI("Sebo");
        Scanner input = new Scanner(System.in);
        int op1, op2;
        do{
            System.out.print(
                "\n[1] Cadastro" +
                "\n[2] Ler" +
                "\n[3] Atualizar" +
                "\n[4] Deletar" +
                "\n[0] Encerrar sistema" + 
                "\nEntre com a opção desejada: "
            );
            op1 = input.nextInt();
            input.nextLine();
            if((op1 > 0) && (op1 < 5)){
                String titulo;
                switch(op1){
                    case 1: titulo = "CADASTRAR"; break;
                    case 2: titulo = "LER"; break;
                    case 3: titulo = "ATUALIZAR"; break;
                    default: titulo = "DELETAR";
                }
                do{
                    System.out.print(
                        "\n\n" + titulo +
                        "\n[1] Autor" +
                        "\n[2] Editora" +
                        "\n[3] Livro" +
                        "\n[0] Voltar" + 
                        "\nEntre com a opção desejada: "
                    );
                    op2 = input.nextInt();
                    switch(op2){
                        case 1: case 2: case 3: l.crud(op1, op2); op2 = 0; break;
                        case 0: System.out.println("\n"); break;
                        default: System.out.println("Opção inválida! :/");
                    }
                }while(op2 != 0);
            }else if(op1 == 0){
                System.out.println("Fim da execução!");
            }else{
                System.out.println("Opção inválida! :/");
            }
            System.out.println("\n\n");
        }while(op1 != 0);
    }
}
