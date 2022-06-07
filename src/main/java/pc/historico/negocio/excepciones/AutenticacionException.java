package pc.historico.negocio.excepciones;

public class AutenticacionException extends RuntimeException {
    public AutenticacionException(String exMensaje) {
        super(exMensaje);
    }
}
