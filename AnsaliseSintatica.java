import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnsaliseSintatica {
    private FormataToken formataToken;
    private List<Erro> listaErros;
    private Map<Integer, Instrucoes> listaInstrucoes;

    public AnsaliseSintatica(String pathArquivo, List<Token> tokens) {
        this.formataToken = new FormataToken(pathArquivo, tokens);
        this.listaInstrucoes = new HashMap(formataToken.getMapInstrucoes());
    }

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

    public void inicializarAnaliseSintatica() {
        for (Map.Entry<Integer, Instrucoes> map : listaInstrucoes.entrySet()) {
            try {
                ValidarToken.validaInstrucoes(map.getValue());
            } catch (SintaxeException e) {
                System.out.println(e.getMessage());
                listaErros.add(e.getErro());
            }
        }
    }
}