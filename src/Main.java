public class Main {

    public static final String[] workList = {
            "Lab1",
            "Lab2",
            "Lab3",
            "Lab4",
            "Lab5",
            "Lab6",
            "Exam"
    };

    public static final Student[] studentList = {
            new Student("Ahmad Mueller", "IT-01"),
            new Student("Allan Moreno", "IT-01"),
            new Student("Enzo Richmond", "IT-01"),
            new Student("Samantha Knapp", "IT-01"),
            new Student("Brett White", "IT-01"),
            new Student("Safiyyah Lopez", "IT-02"),
            new Student("Mae Wiley", "IT-02"),
            new Student("Reuben Weiss", "IT-02"),
            new Student("Fatma Finch", "IT-02"),
            new Student("Noel Wong", "IT-03"),
            new Student("Kayleigh Wheeler", "IT-03"),
            new Student("Kylie Cortez", "IT-03"),
            new Student("Eshal Wyatt", "IT-03"),
            new Student("Morgan Rosario", "IT-03"),
            new Student("Jana O'Brien", "IT-03")
    };

    public static void main(String[] args) {
        GradeBook gb = new GradeBook();

        Thread th = new Thread(new GradeBookManager("Lector", gb));
        Thread th1 = new Thread(new GradeBookManager("Assistant 1", gb));
        Thread th2 = new Thread(new GradeBookManager("Assistant 2", gb));
        Thread th3 = new Thread(new GradeBookManager("Assistant 3", gb));

        th.start();
        th1.start();
        th2.start();
        th3.start();

        try {
            th.join();
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        gb.printGrades();
    }
}