import java.util.List;

public class Instrucoes {
    String conteudoLinha;
    List<Token> tokens;

    public Instrucoes(String conteudoLinha, List<Token> tokens) {
        this.conteudoLinha = conteudoLinha;
        this.tokens = tokens;
    }

    /**
     * Retorna o conteúdo da linha.
     * 
     * @return
     */
    public String getConteudoLinha() {
        return this.conteudoLinha;
    }

    /**
     * Retorna a lista de tokens.
     * 
     * @return
     */
    public List<Token> getTokens() {
        return this.tokens;
    }
}