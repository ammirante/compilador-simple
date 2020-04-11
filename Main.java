import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Main {
    private static String simpleCodePath = "D:\\Faculdade Douglas\\compiladores\\programa_simple.txt";

    public static void main(String[] args) {
        long tempoInicial, tempoFinal;
        System.out.println("Início da análise léxica");
        BufferedReader source = null;

        try {
            source = new BufferedReader(new FileReader(new File(simpleCodePath)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        tempoInicial = System.currentTimeMillis();
        AnaliseLexica lexical = new AnaliseLexica();
        if (!lexical.parser(source)) {
            for (String key : lexical.getSimboloTable().keySet()) {
                System.out.println(lexical.getSimboloTable().get(key) + " : " + key);
            }

            for (Token token : lexical.getTokens()) {
                System.out.println(token);
            }
        }
        tempoFinal = System.currentTimeMillis();
        // System.out.println("Fim da análise léxica e o tempo para realizar a análise foi de: "
                // + (tempoFinal - tempoInicial) + " em milisegundos");

        // System.out.println("Início da análise sintática");
        tempoInicial = System.currentTimeMillis();
        AnsaliseSintatica analisadorSintatico = new AnsaliseSintatica(simpleCodePath, lexical.getTokens());
        analisadorSintatico.inicializarAnaliseSintatica();
        tempoFinal = System.currentTimeMillis();
        // System.out.println("Fim da análise sintática e o tempo para realizar a análise foi de: "
                // + (tempoFinal - tempoInicial) + " em milisegundos");

        // System.out.println("Início da análise semântica");
        tempoInicial = System.currentTimeMillis();
        AnaliseSemantica analisadorSemantico = new AnaliseSemantica();
        System.out.println(analisadorSemantico.analiseSemantica(simpleCodePath));
        tempoFinal = System.currentTimeMillis();
       // System.out.println("Fim da análise sintática e o tempo para realizar a análise foi de: "
                // + (tempoFinal - tempoInicial) + " em milisegundos");
    }//
}
// Ler todas as linhas, dar um split no " ", remover o primeiro índice de cada
// linha, inserir em uma lista e verificar se estão na ordem crescente.