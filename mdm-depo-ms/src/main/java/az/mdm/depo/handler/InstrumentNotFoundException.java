package az.mdm.depo.handler;

public class InstrumentNotFoundException extends RuntimeException{


    private static final long serialVersionUID = -3042686055658047285L;

    public InstrumentNotFoundException() {
        super("Instrument not found.");
    }

    public InstrumentNotFoundException(long id) {
        super(String.format("Instrument with id %d not found.", id));
    }
}
