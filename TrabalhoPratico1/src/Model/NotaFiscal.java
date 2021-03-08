package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class NotaFiscal {
    private static int quantidadeNotasFiscais = 0;
    private final int codigo;
    private final Date data;
    private final HashMap<Produto, Integer> items;

    public NotaFiscal(){ this(new HashMap<>()); }
    public NotaFiscal(HashMap<Produto, Integer> items){
        this.codigo = 1000 + quantidadeNotasFiscais;
        this.data = new Date();
        this.items = items;
        quantidadeNotasFiscais++;

    }

    public double valorTotal(){
        double total = 0;
        for (Produto p : items.keySet()){
            int qtd = items.get(p);
            total += (p.getPreco() * qtd);
        }
        return total;
    }

    public void addItem(Produto produto, int quantidade){
        this.items.put(produto, quantidade);
    }
    public int getCodigo() { return codigo; }
    public String getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.data);
    }
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "#" + this.codigo + " - " + getData() + " - Total: " + valorTotal();
    }
}
