public class Main {
    public static void main(String[] args) {
        Worker w0 = new Worker("t1");
        w0.start();

        Worker w1 = new Worker("t2");
        w1.start();
    }
}
