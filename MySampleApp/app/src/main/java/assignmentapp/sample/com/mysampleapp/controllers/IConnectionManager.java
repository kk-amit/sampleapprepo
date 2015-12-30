package assignmentapp.sample.com.mysampleapp.controllers;

/**
 * Interface for Connection manager.
 */
public interface IConnectionManager {
    /**
     * Check internet connection.
     *
     * @return true if connection found otherwise false.
     */
    boolean checkInternetConnection();
}

