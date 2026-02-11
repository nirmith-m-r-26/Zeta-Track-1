package collections.map;


import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
//        customKey();
        basics();
    }

    private static void basics() {
        Map<String, String> map = new HashMap<>();
        map.put("1","value1");
        System.out.println(map.put("1","value2"));
        System.out.println(map.put(null,"value3"));
//        map.remove(null);
        System.out.println(map);
        map.forEach((k,v)-> System.out.println(k));
    }

    private static void customKey() {
        Map<MyKey, String> map = new HashMap<>();
        MyKey key = new MyKey(1);
        MyKey key1 = new MyKey(1);
        map.put(key, "value1");
        map.put(key1, "value2");
        System.out.println(map.get(key));
        map.forEach((k,v)-> System.out.println(k));
    }
}
