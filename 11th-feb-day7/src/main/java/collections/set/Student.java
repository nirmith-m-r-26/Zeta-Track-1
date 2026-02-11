package collections.set;

public class Student implements Comparable<Student>{
    private int rollNo;
    private String name;

    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return this.rollNo-o.rollNo;
    }

    @Override
    public String toString() {
        return "Roll: "+rollNo+"\tName: "+name;
    }
}
