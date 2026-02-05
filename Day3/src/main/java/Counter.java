public class Counter {
    int count=0;

    void increment(){
        synchronized (this){
            count++;
        }
        System.out.println("inside "+count);
    }

    synchronized int getValue(){
        return count;
    }

}

class Worker1 extends Thread{
    private Counter counter;

    public Worker1 (Counter counter){
        this.counter=counter;
    }

    @Override
    public void run() {
        counter.increment();
    }
}