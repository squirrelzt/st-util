package com.useage.mlutithread.responsibilitychain;

import java.util.concurrent.LinkedBlockingQueue;

public class SaveProcessor extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Request request = requests.take();
                System.out.println("begin to save request info: " + request.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }
}
