package com.useage.mlutithread.responsibilitychain;

public class RequestMain {

    PrintProcessor printProcessor;

    protected RequestMain() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    private void doTest(Request request) {
        printProcessor.processRequest(request);
    }
    public static void main(String[] args) {
        Request request = new Request();
        request.setName("squirrel");
        new RequestMain().doTest(request);
    }
}
