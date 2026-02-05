public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i);
                    Thread.sleep(500);
                }
            } catch (Exception e) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                    Thread.sleep(700);
                }
            } catch (Exception e) {}
        });

        producer.start();
        consumer.start();
    }
}
