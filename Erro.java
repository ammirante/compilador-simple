/**
 * Classe responsável por armazenar os atributos dos tokens 
 * 
 * Desenvolvido por: Douglas Ammirante da Cunha (douglas.ammirante@automatizai.com.br) e Gabriel Bueno Yassunaga (gabriel.yassunaga@automatizai.com.br) 
 */
public class Erro {
    Integer linha;
    Integer coluna;
    Integer instrucao;

    /**
     * 
     */
    public Erro() {
        this.linha = -1;
        this.coluna = -1;
        this.instrucao = -1;
    }

    /**
     * 
     * @param token
     */
    public Erro(Token token) {
        this.linha = token.getLine();
        this.coluna = token.getColumn();
        this.instrucao = token.getType();
    }

    /**
     * 
     * @return
     */
    public Integer getLinha() {
        return linha;
    }

    /**
     * 
     * @param linha
     */
    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    /**
     * 
     * @return
     */
    public Integer getColuna() {
        return this.coluna;
    }

    /**
     * 
     * @param coluna
     */
    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    /**
     * 
     * @return
     */
    public Integer getInstrucao() {
        return this.instrucao;
    }

    /**
     * 
     * @param instrucao
     */
    public void setInstrucao(Integer instrucao) {
        this.instrucao = instrucao;
    }
}