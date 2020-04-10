import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormataToken {
    Map<Integer, Instrucoes> mapInstrucoes = new HashMap<>();
    String pathArquivo;
    List<Token> tokens;
    GerenciadorDeArquivo gerenciadorDeArquivo;

    public FormataToken(String pathArquivo, List<Token> tokens) {
        this.pathArquivo = pathArquivo;
        this.tokens = tokens;
        gerenciadorDeArquivo = new GerenciadorDeArquivo(pathArquivo);
        gerenciadorDeArquivo.abrirArquivo();
        delimitadorDeInstrucoes();
    }

    /**
     * Método responsável por chamar a função que delimita as instruções
     * 
     * @return
     */
    public Map<Integer, Instrucoes> getMapInstrucoes() {
        return mapInstrucoes;
    }

    /**
     * Método responsável por recuperar um pedaço da lista de acordo com os índices
     * inicial e final e recuperar o conteúdo da linha.
     * 
     * @param indiceInicial
     * @param indiceFinal
     * @param linha
     * @return
     */
    public Instrucoes montarInstrucoes(Integer indiceInicial, Integer indiceFinal, Integer linha) {
        List<Token> lstTokens = tokens.subList(indiceInicial, indiceFinal + 1);
        String conteudoLinha = gerenciadorDeArquivo.getInstrucao(linha);

        return new Instrucoes(conteudoLinha, lstTokens);
    }

    /**
     * Método responsável por delimitar as instruções de acordo com a linha.
     */
    public void delimitadorDeInstrucoes() {
        Integer indiceInicial = 0;
        Integer linha = 1;
        for (Integer i = 0; i < tokens.size(); i++) {
            try {
                if (tokens.get(i).getLine() != tokens.get(i + 1).getLine()) {
                    mapInstrucoes.put(linha, montarInstrucoes(indiceInicial, i, linha));
                    indiceInicial = i + 1;
                    linha++;
                }
            } catch (IndexOutOfBoundsException e) {
                mapInstrucoes.put(linha, montarInstrucoes(indiceInicial, i, linha));
            }
        }
    }
}