package payback.core.helpers;

public class ApplicationManagerError extends Error {
    public ApplicationManagerError(String message) {
        super("[Configuration error] " + message);
    }
}
