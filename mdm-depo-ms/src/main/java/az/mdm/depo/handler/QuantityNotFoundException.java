package az.mdm.depo.handler;

public class QuantityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3042686055658047285L;

    public QuantityNotFoundException() {
        super("Quantity not found.");
    }

    public QuantityNotFoundException(long id) {
        super(String.format("Quantity with id %d not found.", id));
    }
}
