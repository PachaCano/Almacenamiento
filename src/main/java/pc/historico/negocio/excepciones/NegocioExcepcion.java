package pc.historico.negocio.excepciones;

public class NegocioExcepcion extends Exception{

    public NegocioExcepcion() {

    }

    public NegocioExcepcion(Throwable cause) {
        super(cause);
    }

    public NegocioExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

    public NegocioExcepcion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
