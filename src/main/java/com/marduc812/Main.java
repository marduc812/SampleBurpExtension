package com.marduc812;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.persistence.PersistedObject;


public class Main implements BurpExtension{

    MontoyaApi api;
    Logging logging;

    static final String DELAY_TIME = "REQ_DELAY_TIME";

    @Override
    public void initialize(MontoyaApi api) {
        this.api = api;
        this.logging = api.logging();
        api.extension().setName("Request Delay");
        this.logging.logToOutput("Demo extension loaded!");

        PersistedObject persist =  api.persistence().extensionData();

        Integer delTime = persist.getInteger(DELAY_TIME);

        if (delTime == null) {
            delTime = 0;
        }

        persist.setInteger(DELAY_TIME, delTime);

        api.proxy().registerRequestHandler(new RequestDelayer(persist, logging));
        api.userInterface().registerSuiteTab("Proxy Delay", new DelayGUI(persist, logging));
    }
}