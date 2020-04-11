import java.util.List;

public class ValidarToken {

    /**
     * Método recursivo responvável por valida as intruções.
     * 
     * @param instrucoes
     */
    public static void validaInstrucoes(Instrucoes instrucoes) throws SintaxeException {
        // Caso o primeiro token seja INTEGER já remove da lista e válida só os próximos
        // tokens.
        Token instrucaoInicial = instrucoes.getTokens().get(0);
        if (instrucaoInicial.getType() != Simbolo.INTEGER) {
            throw new SintaxeException("A instrução não se inicia com um inteiro.", new Erro(instrucaoInicial));
        }

        for (int i = 0; i < instrucoes.getTokens().size(); i++) {
            switch (instrucoes.getTokens().remove(0).getType()) {
                case Simbolo.IF:
                    validaCondicional(instrucoes.getTokens());
                    break;
                case Simbolo.GOTO:
                    validaGoto(instrucoes.getTokens());
                    break;
                /*case Simbolo.END:
                case Simbolo.LF:
                case Simbolo.REM:
                    validaLFREMEnd(instrucoes.getTokens());
                    break;*/
                case Simbolo.INPUT:
                case Simbolo.PRINT:
                    validaInputPrint(instrucoes.getTokens());
                    break;
            }
        }
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaInputPrint(List<Token> tokens) throws SintaxeException {
        Token token = tokens.remove(0);
        if (!validaVariavel(token)) {
            throw new SintaxeException(
                    "Era esperado o token: " + Simbolo.VARIABLE + " mas o token informado era: " + token.getType(),
                    new Erro(token));
        }
    }

    /**
     * 
     * @param token
     * @return
     */
    private static Boolean validaVariavel(Token token) {
        if (Simbolo.VARIABLE != token.getType()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaLFREMEnd(List<Token> tokens) throws SintaxeException {
        if (!tokens.isEmpty()) {
            throw new SintaxeException(
                    "Não era esperado nenhum token a mais, mas ainda contem: " + tokens.size() + " tokens",
                    new Erro(tokens.get(0)));
        }
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaGoto(List<Token> tokens) throws SintaxeException {
        Token token = tokens.remove(0);
        if (token.getType() != Simbolo.INTEGER) {
            throw new SintaxeException(
                    "Era esperado o token: " + Simbolo.INTEGER + " mas o token informado era: " + token.getType(),
                    new Erro(token));
        }
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaCondicional(List<Token> tokens) throws SintaxeException {
        Token tokenAtual;
        tokenAtual = tokens.remove(0);
        if (!validaItem(tokenAtual)) {
            throw new SintaxeException("Era esperado o token: " + Simbolo.VARIABLE + " ou " + Simbolo.INTEGER
                    + " mas o token informado era: " + tokenAtual.getType(), new Erro(tokenAtual));
        }
        tokenAtual = tokens.remove(0);
        if (!validaRelacional(tokenAtual)) {
            throw new SintaxeException(
                    "Era esperado token relacional mas o token informado foi: " + tokenAtual.getType(),
                    new Erro(tokenAtual));
        }
        tokenAtual = tokens.remove(0);
        if (!validaItem(tokenAtual)) {
            throw new SintaxeException("Era esperado o token: " + Simbolo.VARIABLE + " ou " + Simbolo.INTEGER
                    + " mas o token informado era: " + tokenAtual.getType(), new Erro(tokenAtual));
        }
    }

    /**
     * 
     * @param token
     * @return
     */
    private static Boolean validaRelacional(Token token) {
        switch (token.getType()) {
            case Simbolo.EQ:
            case Simbolo.NE:
            case Simbolo.GT:
            case Simbolo.LT:
            case Simbolo.GE:
            case Simbolo.LE:
                return Boolean.TRUE;
            default:
                return Boolean.FALSE;
        }
    }

    /**
     * 
     * @param token
     * @return
     */
    private static Boolean validaItem(Token token) {
        if (token.getType() == Simbolo.INTEGER || token.getType() == Simbolo.VARIABLE) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}