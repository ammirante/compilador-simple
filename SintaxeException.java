public class SintaxeException extends Exception {

    /**
     * 
     */
    private Erro erro;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param msgErro
     * @param erro
     */
    public SintaxeException(String msgErro, Erro erro) {
        super(msgErro);
        this.erro = erro;
    }

    /**
     * 
     * @return
     */
    public Erro getErro() {
        return this.erro;
    }
}