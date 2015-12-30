package assignmentapp.sample.com.mysampleapp.controllers;

/**
 * Callback for web-service caller.
 */
public interface IWebServiceCallBackListener {

    void onWebServiceProgress(int progressValue);

    void onWebServiceStatus(boolean status);

    void onWebServiceCompleted(String Data);

}
