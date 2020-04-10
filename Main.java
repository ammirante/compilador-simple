import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Main {
    private static String simpleCodePath = "D:\\Faculdade Douglas\\compiladores\\programa_simple.txt";

    public static void main(String[] args) {
        System.out.println("Início da análise léxica");
        BufferedReader source = null;

        try {
            source = new BufferedReader(new FileReader(new File(simpleCodePath)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        AnaliseLexica lexical = new AnaliseLexica();
        if (!lexical.parser(source)) {
            for (String key : lexical.getSimboloTable().keySet()) {
                System.out.println(lexical.getSimboloTable().get(key) + " : " + key);
            }

            for (Token token : lexical.getTokens()) {
                System.out.println(token);
            }
        }

        AnaliseSemantica analisadorSemantico = new AnaliseSemantica();
        System.out.println(analisadorSemantico.analiseSemantica(simpleCodePath));
    }
}
// Ler todas as linhas, dar um split no " ", remover o primeiro índice de cada
// linha, inserir em uma lista e verificar se estão na ordem crescente.