package az.mdm.depo.handler;

public class RoleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -3042686055658047285L;

    public RoleNotFoundException() {
        super("Role not found.");
    }

    public RoleNotFoundException(long id) {
        super(String.format("Role with id %d not found.", id));
    }
    public RoleNotFoundException(String roleName) {
        super(String.format("Role with name %d not found.", roleName));
    }

}
