package az.mdm.depo.handler;

public class CategoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3042686055658047285L;

    public CategoryNotFoundException() {
        super("Category not found.");
    }

    public CategoryNotFoundException(long id) {
        super(String.format("Category with id %d not found.", id));
    }
}
