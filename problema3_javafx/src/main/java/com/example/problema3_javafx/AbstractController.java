package com.example.problema3_javafx;


import Controller.Controller;

public class AbstractController {

    protected Controller controller;

    /**
     * constructor
     * @param controller: the application Controller
     */
    protected AbstractController(Controller controller){
        this.controller = controller;
    }
}
