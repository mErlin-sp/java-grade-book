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
            // For each work in workList
            for (String workName : Main.workList) {
                System.out.println("Manager " + this.name + " starts putting grades for work: " + workName + ".");

                this.gb.lock.lock();
                try {
                    // If work is not present in grade book - add it
                    if (!this.gb.workPresent(workName)) {
                        this.gb.addStudentWork(new StudentWork(workName));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    this.gb.lock.unlock();
                }

                // Get work instance from grade book
                StudentWork work = gb.getStudentWork(workName);

                Random r = new Random();

                // For each student in studentList
                for (Student s : Main.studentList) {
                    work.lock.lock();
                    try {
                        // If student has no grade for this work
                        if (!work.hasGrade(s)) {
                            // Add new grade for this work for this student
                            Grade gr = new Grade((short) r.nextInt(100));
                            System.out.println("Manager " + this.name + " puts Grade: " + gr.grade() + " for student: " + s.toString() + " for work: " + workName + ".");
                            work.addGrade(s, gr);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        work.lock.unlock();
                    }
                    Thread.sleep(r.nextInt(100) + 50);
                }

                System.out.println("Manager " + this.name + " ends putting grades for work: " + workName + ".");
                Thread.sleep(r.nextInt(1000) + 500);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
