import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class GradeBook {
    public final ReentrantLock lock = new ReentrantLock();
    private final List<StudentWork> workList = new ArrayList<>();
    private final String disciplineName;

    public GradeBook(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public synchronized void addStudentWork(StudentWork work) {
        workList.add(work);
    }

    public synchronized StudentWork getStudentWork(String workName) throws Exception {
        for (StudentWork work : this.workList) {
            if (Objects.equals(work.getName(), workName)) {
                return work;
            }
        }
        throw new Exception("GradeBook doesnt contain a work with this name!");
    }

//    public List<StudentWork> getStudentWorksList() {
//        return workList;
//    }

    public synchronized boolean workPresent(String workName) {
        for (StudentWork work : this.workList) {
            if (Objects.equals(work.getName(), workName)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void printGrades() {
        System.out.println("\nList of student works in GradeBook for discipline: " + disciplineName);
        for (StudentWork work : this.workList) {
            work.printGrades();
        }
    }

}
