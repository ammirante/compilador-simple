import java.util.ArrayList;

public class AnaliseSemantica {

    private GerenciadorDeArquivo fileManager;

    public boolean analiseSemantica(String path) {
        fileManager = new GerenciadorDeArquivo(path);
        fileManager.abrirArquivo();
        ArrayList<Integer> listaNumeros = new ArrayList<>(fileManager.getNumeroInstrucoes());
        return verificaNumeros(listaNumeros);
    }

    private boolean verificaNumeros(ArrayList<Integer> lista) {
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i - 1) < lista.get(i)) {
                continue;
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}

// Léxica -> Sintática -> Tratamento do contexto