package com.leonhard_leung.controller;

import com.leonhard_leung.model.ServerModel;

public class ServerController {
    private ServerModel model;

    public ServerController(ServerModel model) {
        this.model = model;
    }

    public Object processRequest(Object request) {
        return null;
    } // end of processRequest
} // end of ServerController class
