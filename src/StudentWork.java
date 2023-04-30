import java.util.HashMap;
import java.util.Map;

public class StudentWork {
    private final String name;
    private final Map<Student, Grade> grades = new HashMap<>();

    public StudentWork(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addGrade(Student s, Grade gr) throws Exception {
        if (this.grades.containsKey(s)) {
            throw new Exception("This Student already have Grade for this work!");
        } else {
            this.grades.put(s, gr);
        }
    }

    public boolean hasGrade(Student s) {
        return this.grades.containsKey(s);
    }

    public void printGrades() {
        System.out.println("\nList of student grades for work: " + this.name + ".");
        for (Student s : this.grades.keySet()) {
            System.out.println("Student: " + s.name() + ". Grade: " + this.grades.get(s));
        }
    }
}
