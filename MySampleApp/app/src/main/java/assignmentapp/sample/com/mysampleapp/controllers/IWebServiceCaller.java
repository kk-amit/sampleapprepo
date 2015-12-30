package assignmentapp.sample.com.mysampleapp.controllers;

/**
 * Interface for Web-service caller.
 */
public interface IWebServiceCaller {

    void setWebServiceCallBackListener(IWebServiceCallBackListener webServiceCallBackListener);

    void removeWebServiceCallBackListener();

    void executeAsync();
}
