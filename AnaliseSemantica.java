import java.util.ArrayList;

/**
 * Classe responsável pela análise semântica.
 * 
 * Desenvolvido por: Douglas Ammirante da Cunha (douglas.ammirante@automatizai.com.br) e Gabriel Bueno Yassunaga (gabriel.yassunaga@automatizai.com.br) 
 */
public class AnaliseSemantica {

    private GerenciadorDeArquivo fileManager;

    /**
     * 
     * @param path
     * @return
     */
    public boolean analiseSemantica(String path) {
        fileManager = new GerenciadorDeArquivo(path);
        fileManager.abrirArquivo();
        ArrayList<Integer> listaNumeros = new ArrayList<>(fileManager.getNumeroInstrucoes());
        return verificaNumeros(listaNumeros);
    }

    /**
     * 
     * @param lista
     * @return
     */
    private boolean verificaNumeros(ArrayList<Integer> lista) {
        for (int i = 1; i < lista.size(); i++) {
            System.out.println("Elemento: " + lista.get(i));
            if (lista.get(i - 1) < lista.get(i)) {
                continue;
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}