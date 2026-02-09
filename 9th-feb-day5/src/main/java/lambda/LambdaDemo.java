package lambda;

import java.util.ArrayList;
import java.util.function.Predicate;

public class LambdaDemo {
    public static void main(String[] args) {
        //assignment 1
        Calculator calculator = (x,y)->x+y;

        System.out.println(calculator.add(1,3));


        // assignment 2

        ArrayInc arrayInc = (arr)->{
            for (int i = 0; i < arr.size(); i++) {
                arr.set(i, ((int) arr.get(i) + 2));
            }
            return arr;
        };

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        System.out.println("Array increment by 2 \narray->[1,2,3,4]\n"+arrayInc.increment(arr));


        // assignment 3
        int inc = 5;
        ArrayIncEven arrayIncEven = (array, val)->{
            for (int i = 0; i <array.size(); i++) {
                if (((int)array.get(i) % 2) == 0) {
                    array.set(i, (int)array.get(i)+val);
                }
            }
            return array;
        };
        System.out.println(String.format("Array increment by %d  array->",inc)+arr+"\n\n"+arrayIncEven.increment(arr,inc));
    }
}
