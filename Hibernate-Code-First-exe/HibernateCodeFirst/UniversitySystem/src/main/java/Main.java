import entities.Course;
import entities.Student;
import entities.Teacher;
import utils.EManager;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EManager.entityManager();
        entityManager.getTransaction().begin();
        Course course1 = new Course("Java-db","Beginner course", LocalDate.now(),null,15);
        Student student1 = new Student("Gosho", "Goshov", "+354 5544 55", 5.66, 95.9);
        Student student2 = new Student("Ivan", "Petrov", "+354 5544 66", 5.76, 91.9);
        Course course2 = new Course("Java-advanced","Beginner course", LocalDate.now(),null,15);

        Teacher teacher = new Teacher("Gerasim","Georgiev", "+356 554 554 22", "gercho_kokercho@abv.bg", 10);

        course1.setTeacher(teacher);
        course2.setTeacher(teacher);

        teacher.addCourse(course1);
        teacher.addCourse(course2);

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course2);

        entityManager.persist(teacher);
        entityManager.persist(student1);
        entityManager.persist(student2);

        entityManager.getTransaction().commit();
    }
}