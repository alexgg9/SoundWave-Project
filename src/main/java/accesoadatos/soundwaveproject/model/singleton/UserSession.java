package accesoadatos.soundwaveproject.model.singleton;

import accesoadatos.soundwaveproject.model.Usuario;

public class UserSession {
    private static UserSession instance;
    private Usuario currentUser;

    private UserSession() {

    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void loginUser(Usuario user) {
        currentUser = user;
    }

    public void logoutUser() {
        currentUser = null;
    }

    public Usuario getUsuarioActual() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}

