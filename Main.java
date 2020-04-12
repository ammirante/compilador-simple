import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * Classe responsável pela execução do compilador.
 * 
 * Desenvolvido por: Douglas Ammirante da Cunha
 * (douglas.ammirante@automatizai.com.br) e Gabriel Bueno Yassunaga
 * (gabriel.yassunaga@automatizai.com.br)
 */
public class Main {
    private static String simpleCodePath = "D:\\Faculdade Douglas\\compiladores\\programa_simple.txt";

    public static void main(String[] args) {
        BufferedReader source = null;

        try {
            source = new BufferedReader(new FileReader(new File(simpleCodePath)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        System.out.println("Início da análise léxica");
        AnaliseLexica lexical = new AnaliseLexica();
        if (!lexical.parser(source)) {
            for (String key : lexical.getSimboloTable().keySet()) {
                System.out.println(lexical.getSimboloTable().get(key) + " : " + key);
            }

            for (Token token : lexical.getTokens()) {
                System.out.println(token);
            }
        }
        System.out.println("Fim da análise léxica");

        System.out.println("Início da análise sintática.");
        AnsaliseSintatica analisadorSintatico = new AnsaliseSintatica(simpleCodePath, lexical.getTokens());
        analisadorSintatico.inicializarAnaliseSintatica();
        System.out.println("Fim da análise sintática.");

        System.out.println("Início da análise semântica.");
        AnaliseSemantica analisadorSemantico = new AnaliseSemantica();
        if (!analisadorSemantico.analiseSemantica(simpleCodePath)) {
            System.out.println("Erro na análise semântica, as instruções devem estar em ordem crescente");
        }
        System.out.println("Fim da análise semântica.");
    }
}