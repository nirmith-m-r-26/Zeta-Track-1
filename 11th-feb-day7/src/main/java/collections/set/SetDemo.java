package collections.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        Set<Student> set = new TreeSet<>();
        set.add(new Student(3,"B"));
        set.add(new Student(1,"D"));
        set.add(new Student(4,"a"));
        set.add(new Student(2,"c"));

        set.forEach(student -> System.out.println(student));

        set.stream().sorted(new NameComparator()).forEach(student -> System.out.println(student));

    }
}
