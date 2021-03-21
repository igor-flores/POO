package Model;

abstract class Midia {
    private static int totalMidias = 0;
    private final int codigo;
    private String caminhoArquivo, titulo, descricao;

    Midia(String caminhoArquivo, String titulo, String descricao){
        this.codigo = 1000 + totalMidias;
        this.caminhoArquivo = caminhoArquivo;
        this.titulo = titulo;
        this.descricao = descricao;
        totalMidias++;
    }

    public static int getTotalMidias() { return totalMidias; }
    public int getCodigo() { return codigo; }
    public String getCaminhoArquivo() { return caminhoArquivo; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }

    public void setCaminhoArquivo(String caminhoArquivo) { this.caminhoArquivo = caminhoArquivo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
