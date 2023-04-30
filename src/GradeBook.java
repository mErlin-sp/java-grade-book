import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GradeBook {
    private final List<StudentWork> workList = new ArrayList<>();

    public GradeBook() {
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
        System.out.println("\nList of student works in GradeBook:");
        for (StudentWork work : this.workList) {
            work.printGrades();
        }
    }

}
