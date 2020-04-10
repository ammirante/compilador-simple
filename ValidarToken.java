import java.util.List;

public class ValidarToken {

    /**
     * Método recursivo responvável por valida as intruções.
     * 
     * @param instrucoes
     */
    public static void validaInstrucoes(Instrucoes instrucoes) throws SintaxeException {
        Token instrucaoInicial = instrucoes.getTokens().get(0);
        if(instrucaoInicial.getType() != Simbolo.INTEGER) {
            throw new SintaxeException("msgErro", new Erro(instrucaoInicial));
        }

        // Caso o primeiro token seja INTEGER já remove da lista e válida só os próximos tokens.
        instrucoes.getTokens().remove(0);
        for (Token token : instrucoes.getTokens()) {
            switch (token.getType()) {
                case 62:
                    validaInput(instrucoes.getTokens());
                    break;
            }
        }
    }

// [51, 0, (1, 1)] [61, , (1, 4)] [10, , (1, 51)]
// [51, 1, (2, 1)]
// [61, , (2, 4)]
// [10, , (2, 7)]
// [51, 2, (3, 1)]
// [61, , (3, 4)]
// [10, , (3, 27)]
// [51][62][41]
// [10, , (4, 11)]
// [51, 5, (5, 1)]
// [62, , (5, 4)]
// [41, 6, (5, 10)]
// [10, , (5, 12)]
// [51, 7, (6, 1)]
// [61, , (6, 4)]
// [10, , (6, 7)]
// [51, 8, (7, 1)]
// [51, 11, (9, 1)]
// [61, , (9, 4)]
// [10, , (9, 7)]

    /**
     * 
     * @param tokens
     * @throws SintaxeException
     */
    private static void validaInput(List<Token> tokens) throws SintaxeException {
        if (Simbolo.INPUT == tokens.remove(0).getType()) {
            Token variavelEsperada = tokens.remove(0);
            if (Simbolo.VARIABLE != variavelEsperada.getType()) {
                Erro erro = new Erro();
                throw new SintaxeException("Era esperado o token: " + Simbolo.VARIABLE + " mas o token informado era: " + variavelEsperada.getType(), erro);
            }
        }
    }

}