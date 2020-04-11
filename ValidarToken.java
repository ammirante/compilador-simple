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
        Token instrucaoInicial = removeTokenTopo(instrucoes.getTokens());
        if (instrucaoInicial.getType() != Simbolo.INTEGER) {
            throw new SintaxeException("A instrução não se inicia com um inteiro.", new Erro(instrucaoInicial));
        }

        for (int i = 0; i < instrucoes.getTokens().size(); i++) {
            Token tokenAtual = removeTokenTopo(instrucoes.getTokens());
            switch (tokenAtual.getType()) {
                case Simbolo.LET:
                    validaOperacao(instrucoes.getTokens());
                    break;
                case Simbolo.IF:
                    validaCondicional(instrucoes.getTokens());
                    break;
                case Simbolo.GOTO:
                    validaGoto(instrucoes.getTokens());
                    break;
                case Simbolo.END:
                    break;
                case Simbolo.LF:
                    validaLF(instrucoes.getTokens());
                case Simbolo.REM:
                    validaREM(instrucoes.getTokens());
                    break;
                case Simbolo.INPUT:
                case Simbolo.PRINT:
                    validaInputPrint(instrucoes.getTokens());
                    break;
                case Simbolo.ETX:
                    break;
                default:
                    StringBuilder erro = new StringBuilder();
                    erro.append("Era esperado um token de operação ");
                    erro.append("Erro no método: validaInstrucoes");
                    throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
            }
        }
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaInputPrint(List<Token> tokens) throws SintaxeException {
        Token token = removeTokenTopo(tokens);
        if (!validaVariavel(token)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.VARIABLE);
            erro.append(" mas o token informado foi: ");
            erro.append(token.getType());
            erro.append("Erro no método: validaInputPrint ");
            throw new SintaxeException(erro.toString(), new Erro(token));
        }
        validaLF(tokens);
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
    private static void validaLF(List<Token> tokens) throws SintaxeException {
        Token tokenAtual = removeTokenTopo(tokens);
        if (tokenAtual.getType() != Simbolo.LF) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.LF);
            erro.append(" mas o token informado foi: ");
            erro.append(tokenAtual.getType());
            erro.append("Erro no método: validaLF");
            throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
        }

        if (!tokens.isEmpty()) {
            StringBuilder erro = new StringBuilder();
            erro.append("Não era esperado nenhum token a mais, mas ainda contem: ");
            erro.append(tokens.size());
            erro.append(" tokens");
            erro.append("Erro no método: validaLF");
            throw new SintaxeException(erro.toString(), new Erro(tokens.get(0)));
        }
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaREM(List<Token> tokens) throws SintaxeException {
        validaLF(tokens);
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaGoto(List<Token> tokens) throws SintaxeException {
        Token token = removeTokenTopo(tokens);
        if (!validaInteiro(token)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.INTEGER);
            erro.append(" mas o token informado foi: ");
            erro.append(token.getType());
            erro.append(" Erro no método: validaGoto");
            throw new SintaxeException(erro.toString(), new Erro(token));
        }
        validaLF(tokens);
    }

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaCondicional(List<Token> tokens) throws SintaxeException {
        Token tokenAtual = removeTokenTopo(tokens);
        if (!validaItem(tokenAtual)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.VARIABLE);
            erro.append(" mas o token informado foi: ");
            erro.append(tokenAtual.getType());
            erro.append(" Erro no método: validaCondicional");
            throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
        }
        tokenAtual = removeTokenTopo(tokens);
        if (!validaRelacional(tokenAtual)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token relacional mas o token informado foi: ");
            erro.append(tokenAtual.getType());
            erro.append(" Erro no método: validaCondicional");
            throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
        }
        tokenAtual = removeTokenTopo(tokens);
        if (!validaItem(tokenAtual)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.VARIABLE);
            erro.append(" ou ");
            erro.append(Simbolo.INTEGER);
            erro.append(" mas o token informado foi: ");
            erro.append(tokenAtual.getType());
            erro.append(" Erro no método: validaCondicional");
            throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
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
        if (validaInteiro(token) || validaVariavel(token)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private static void validaOperacao(List<Token> tokens) throws SintaxeException {
        Token tokenAtual = removeTokenTopo(tokens);
        if (!validaVariavel(tokenAtual)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.VARIABLE);
            erro.append(" ou ");
            erro.append(Simbolo.INTEGER);
            erro.append(" mas o token informado foi: ");
            erro.append(tokenAtual.getType());
            erro.append(" Erro no método: validaCondicional");
            throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
        }
        tokenAtual = removeTokenTopo(tokens);
        if (!validaAtribuicao(tokenAtual)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.ASSIGNMENT);
            erro.append(" mas o token informado foi: ");
            erro.append(tokenAtual.getType());
            erro.append(" Erro no método: validaCondicional");
            throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
        }
        if (!validaExpressao(tokens)) {
            StringBuilder erro = new StringBuilder();
            erro.append("Era esperado o token: ");
            erro.append(Simbolo.VARIABLE);
            erro.append(" ou ");
            erro.append(Simbolo.INTEGER);
            erro.append(" mas o token informado foi: ");
            erro.append(tokenAtual.getType());
            erro.append(" Erro no método: validaCondicional");
            throw new SintaxeException(erro.toString(), new Erro(tokenAtual));
        }
        validaLF(tokens);
    }

    /**
     * 
     * @param tokens
     * @return
     */
    private static Token removeTokenTopo(List<Token> tokens) {
        try {
            return tokens.remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 
     * @param token
     * @return
     */
    private static Boolean validaInteiro(Token token) {
        if (token.getType() == Simbolo.INTEGER) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 
     * @param token
     * @return
     */
    private static Boolean validaAtribuicao(Token token) {
        if (token.getType() == Simbolo.ASSIGNMENT) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 
     * @param tokens
     */
    private static Boolean validaExpressao(List<Token> tokens) {
        if (validaItem(removeTokenTopo(tokens))) {
            if (tokens.isEmpty()) {
                return Boolean.TRUE;
            } else if (validaOperador(removeTokenTopo(tokens)) && validaItem(removeTokenTopo(tokens))) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    /**
     * 
     * @param token
     * @return
     */
    private static Boolean validaOperador(Token token) {
        switch (token.getType()) {
            case Simbolo.DIVIDE:
            case Simbolo.MULTIPLY:
            case Simbolo.SUBTRACT:
            case Simbolo.ADD:
                return Boolean.TRUE;
            default:
                return Boolean.FALSE;
        }
    }
}