package edu.kirkwood.view;

public class Animator implements Runnable {
    private final String message;
    private final String[] states = {".", "..", "...", "...."};
    private int currentStateIndex = 0;
    // volatile means that only one Thread object can
    // modify the variable at a time. No two threads
    // have simultaneous access.
    private volatile boolean running = true;

    public Animator(String message) {
        this.message = message;
    }

    /**
     * Signals to the Animator thread to stop after the current cycle
     */
    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while(running) {
            System.out.print("\r" + message + states[currentStateIndex]);
            currentStateIndex = (currentStateIndex + 1) % states.length;
            try {
                // Pause the animation for 4/10ths of a second
                Thread.sleep(400);
            } catch (InterruptedException e) {
                // If another thread finishes first, stop this thread next
                running = false;
                Thread.currentThread().interrupt();
            }
        }
        // When finished, remove the message to clear the screen
        String cleanup = " ".repeat(message.length() + states.length);
        System.out.print("\r" + cleanup + "\r");
    }
}
