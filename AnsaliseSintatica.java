import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável pela análise sintática.
 * 
 * Desenvolvido por: Douglas Ammirante da Cunha (douglas.ammirante@automatizai.com.br) e Gabriel Bueno Yassunaga (gabriel.yassunaga@automatizai.com.br) 
 */
public class AnsaliseSintatica {
    private FormataToken formataToken;
    private List<Erro> listaErros = new ArrayList<>();
    private Map<Integer, Instrucoes> listaInstrucoes;

    /**
     * 
     * @param pathArquivo
     * @param tokens
     */
    public AnsaliseSintatica(String pathArquivo, List<Token> tokens) {
        this.formataToken = new FormataToken(pathArquivo, tokens);
        this.listaInstrucoes = new HashMap(formataToken.getMapInstrucoes());
    }

    /**
     * Método responsável por mostrar a lista de instruções.
     */
    public void mostrarListaInstrucoes() {
        for (Integer i : listaInstrucoes.keySet()) {
            Instrucoes inst = listaInstrucoes.get(i);
            inst.getTokens();
            System.out.println("Conteudo da linha: " + inst.conteudoLinha);
            System.out.println("Printando lista de tokens: ");
            for (Token token : inst.tokens) {
                System.out.println("Instrucao: " + token.getType());
                System.out.println("Linha: " + token.getLine());
                System.out.println("Coluna: " + token.getColumn());
            }
        }
    }

    /**
     * Metódo responsável por realizar a análise sintatica.
     */
    public void inicializarAnaliseSintatica() {
        for (Map.Entry<Integer, Instrucoes> map : listaInstrucoes.entrySet()) {
            try {
                ValidarToken.validaInstrucoes(Instrucoes.clone(map.getValue()));
            } catch (SintaxeException e) {
                //System.err.println("ERROR: " + e.getMessage());
                listaErros.add(e.getErro());
            }
        }
        mostrarErrosEncontrados();
    }

    /**
     * Método responsável por mostrar todos os erros encontrados.
     */
    public void mostrarErrosEncontrados() {
        if(!listaErros.isEmpty()) {
            System.out.println("Foram encontrados os seguintes erros: ");
            Integer i = 1;
            for(Erro erro : listaErros) {
                System.out.println("Erro #" + i);
                System.out.println("Coluna: " + erro.getColuna() + " Linha: " + erro.getLinha() + " Instrução: " + erro.getInstrucao());
            }
        } else {
            System.out.println("Não foram encontrados erros na análise sintática.");
        }
    }
}