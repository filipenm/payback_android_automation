package payback.core.errors;

public class ManagerError extends Error {
    public ManagerError(String message) {
        super("[Configuration error] " + message);
    }
}

