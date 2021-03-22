package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Midia {
    private static int totalMidias = 0;
    protected final int codigo;
    protected String caminhoArquivo, titulo, descricao;
    protected Date data;

    Midia(String caminhoArquivo, String titulo, String descricao, Date data){
        this.codigo = 1000 + totalMidias;
        this.caminhoArquivo = caminhoArquivo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        totalMidias++;
    }

    public static int getTotalMidias() { return totalMidias; }
    public int getCodigo() { return codigo; }
    public String getCaminhoArquivo() { return caminhoArquivo; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(this.data);
    }

    public void setCaminhoArquivo(String caminhoArquivo) { this.caminhoArquivo = caminhoArquivo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setData(Date data) { this.data = data; }
}
