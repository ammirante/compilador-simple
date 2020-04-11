public class Erro {
    int linha;
    int coluna;
    int instrucao;

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
    public int getLinha() {
        return linha;
    }

    /**
     * 
     * @param linha
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }

    /**
     * 
     * @return
     */
    public int getColuna() {
        return this.coluna;
    }

    /**
     * 
     * @param coluna
     */
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    /**
     * 
     * @return
     */
    public int getInstrucao() {
        return this.instrucao;
    }

    /**
     * 
     * @param instrucao
     */
    public void setInstrucao(int instrucao) {
        this.instrucao = instrucao;
    }
}