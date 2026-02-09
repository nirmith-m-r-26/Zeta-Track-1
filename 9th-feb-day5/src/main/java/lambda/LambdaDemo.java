package lambda;

import java.util.ArrayList;

public class LambdaDemo {
    public static void main(String[] args) {
        //assignment 0
        Calculator calculator = (x,y)->x+y;

        System.out.println(calculator.add(1,3));


        // assignment 1

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


        // assignment 2
        int inc = 5;
        ArrayIncEven arrayIncEven = (array, val)->{
            for (int i = 0; i <array.size(); i++) {
                if (((int)array.get(i) % 2) == 0) {
                    array.set(i, (int)array.get(i)+val);
                }
            }
            return array;
        };
        System.out.println(String.format("Array increment by %d  array->",2)+arr+"\n\n"+arrayIncEven.increment(arr,2));


        // assignment 3

        System.out.println(String.format("Array increment by %d  array->",inc)+arr+"\n\n"+arrayIncEven.increment(arr,inc));

        //assignment 4

        SumThird sumThird = (numbers)->{
            int sum=0;
            for (int i = 0; i < numbers.size(); i++) {
                if((i+1)%3==0){
                    sum+= (int) numbers.get(i);
                }
            }
            return sum;
        };

        System.out.println(String.format("sum of all 3rd positions  array->",inc)+arr+"\n\n"+sumThird.sum(arr));
    }
}
