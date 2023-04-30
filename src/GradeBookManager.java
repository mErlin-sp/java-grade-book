import java.util.Random;

public class GradeBookManager implements Runnable {

    private final String name;
    private final GradeBook gb;

    public GradeBookManager(String name, GradeBook gb) {
        this.name = name;
        this.gb = gb;
    }

    @Override
    public void run() {
        try {
            for (String workName : Main.workList) {
                System.out.println("Manager " + this.name + " starts putting grades for work: " + workName + ".");

                if (!this.gb.workPresent(workName)) {
                    this.gb.addStudentWork(new StudentWork(workName));
                }
                StudentWork work = gb.getStudentWork(workName);

                Random r = new Random();

                for (Student s : Main.studentList) {
                    synchronized (work) {
                        if (!work.hasGrade(s)) {
                            Grade gr = new Grade((short) r.nextInt(100));
                            System.out.println("Manager " + this.name + " put Grade: " + gr.grade() + " for student: " + s.toString() + " for work: " + workName + ".");
                            work.addGrade(s, gr);
                        }
                    }
                }

                Thread.sleep(r.nextInt(1000) + 500);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
