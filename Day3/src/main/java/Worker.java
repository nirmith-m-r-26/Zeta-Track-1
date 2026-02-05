public class Worker extends java.lang.Thread {
    public Worker(String s) {
        super(s);
    }

    @Override
    public void run() {
        synchronized (this){
        for (int i = 0; i < 10; i++) {
            try{
                System.out.println("thread = "+Thread.currentThread().getName());
                wait(3000);
                Thread.sleep(1000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }}
    }
}
