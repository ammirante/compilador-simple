import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeArquivo {
    StringBuilder sb = new StringBuilder();
    String pathArquivo;

    public GerenciadorDeArquivo(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

    public void abrirArquivo() {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(pathArquivo));

            String linha;
            while ((linha = br.readLine()) != null) {
                sb.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo. " + e.getMessage());
        }
    }

    /**
     * Método responsável por retornar o texto de acordo com a linha do arquivo
     * 
     * @param linha
     * @return
     */
    public String getInstrucao(Integer linha) {
        return sb.toString().split("\n")[linha - 1];
    }

    /**
     * Método responsável por retornar uma lista de inteiros contendo o número das
     * instruções.
     * 
     * @return
     */
    public List<Integer> getNumeroInstrucoes() {
        String[] instrucoes = sb.toString().split("\n");

        List<Integer> lstInstrucoes = new ArrayList<>(instrucoes.length);
        String[] linha;
        for (int i = 0; i < instrucoes.length; i++) {
            linha = instrucoes[i].split(" ");
            lstInstrucoes.add(Integer.parseInt(linha[0]));
        }

        return lstInstrucoes;
    }
}