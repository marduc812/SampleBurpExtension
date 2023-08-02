package com.marduc812;

import burp.api.montoya.persistence.PersistedObject;
import burp.api.montoya.proxy.http.InterceptedRequest;
import burp.api.montoya.proxy.http.ProxyRequestHandler;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction;
import burp.api.montoya.logging.Logging;

import static com.marduc812.Main.DELAY_TIME;

public class RequestDelayer implements ProxyRequestHandler{
    Logging logging;
    PersistedObject persistence;

    public RequestDelayer(PersistedObject persistence, Logging logging) {
        this.logging = logging;
        this.persistence = persistence;
    }

    @Override
    public ProxyRequestReceivedAction handleRequestReceived(InterceptedRequest interceptedRequest) {

        Integer requestDelay = persistence.getInteger(DELAY_TIME);
        // Sleep for X second, as passed from the storage
        try {
            Thread.sleep(requestDelay);
        } catch (InterruptedException e) {
            logging.logToError(e);
        }

        // forward the delayed request
        return ProxyRequestReceivedAction.doNotIntercept(interceptedRequest);
    }

    @Override
    public ProxyRequestToBeSentAction handleRequestToBeSent(InterceptedRequest interceptedRequest) {
        return ProxyRequestToBeSentAction.continueWith(interceptedRequest);
    }
}
