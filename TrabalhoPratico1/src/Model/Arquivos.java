package Model;

import java.io.*;
import java.util.ArrayList;

public class Arquivos {
    public static final String _DIR_ = "Arquivos/";
    public static final String _DIR_EMPRESA_ = _DIR_ + "Empresas.txt";
    public static final String _DIR_PRODUTO_ = _DIR_ + "Produtos.txt";
    public static final String _DIR_MOVIMENTACAO_ = _DIR_ + "NotasFiscais.txt";

    public static boolean criaRaiz(){
        File diretorioGrupo = new File(_DIR_);
        File arquivoPessoas = new File(_DIR_EMPRESA_);
        File arquivoContas = new File(_DIR_PRODUTO_);
        File arquivoMovimentacoes = new File(_DIR_MOVIMENTACAO_);

        try {
            if (!diretorioGrupo.exists())
                diretorioGrupo.mkdir();

            if (!arquivoPessoas.exists())
                arquivoPessoas.createNewFile();

            if (!arquivoContas.exists())
                arquivoContas.createNewFile();

            if (!arquivoMovimentacoes.exists())
                arquivoMovimentacoes.createNewFile();

            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static ArrayList<String> leArquivo(String nomeArquivo){
        ArrayList <String> dadosArray = new ArrayList<>();
        try {
            BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo));
            String linha;

            while ((linha = arquivo.readLine()) != null){
                dadosArray.add(linha);
            }

            arquivo.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return dadosArray;
    }
}
