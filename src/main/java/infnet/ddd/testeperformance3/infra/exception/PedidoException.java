package infnet.ddd.testeperformance3.infra.exception;

public class PedidoException extends RuntimeException {
    public PedidoException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
