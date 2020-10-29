import java.io.Serializable;
import java.rmi.server.UID;

class User implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    transient String password;
}
