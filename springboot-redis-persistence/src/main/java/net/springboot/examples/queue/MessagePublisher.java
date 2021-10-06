package net.springboot.examples.queue;

public interface MessagePublisher {
    public void publish(final String message);
}
