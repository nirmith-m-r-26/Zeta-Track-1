public class Buffer {
    private int data;
    private boolean available = false;

    synchronized void produce(int value) throws InterruptedException {
        while (available) wait();   // wait if buffer full

        data = value;
        available = true;
        System.out.println("Produced: " + value);
        notify();   // wake up consumer
    }

    synchronized int consume() throws InterruptedException {
        while (!available) wait();  // wait if buffer empty

        available = false;
        System.out.println("Consumed: " + data);
        notify();   // wake up producer
        return data;
    }
}
