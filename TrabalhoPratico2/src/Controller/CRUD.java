package Controller;

import Model.Midia;

public interface CRUD {
    /**
     * Cria uma mídia
     * @param midia = mídia à ser adicionada ao ArrayList
     * @return true: sucesso, false: erro
     */
    boolean create(Midia midia);

    /**
     * Visualização de determinada mídia
     */
    void read();

    /**
     * Atualiza determinada mídia
     * @param midia =  midia que será atualizada
     * @return true: sucesso, false: erro
     */
    boolean update(Midia midia);

    /**
     * Deleta determinada mídia
     * @param midia = midia que será deletada
     * @return true: sucesso, false: erro
     */
    boolean delete(Midia midia);
}
