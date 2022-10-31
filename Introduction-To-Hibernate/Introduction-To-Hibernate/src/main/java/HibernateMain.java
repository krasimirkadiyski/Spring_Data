import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {


        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
//Създаваме нов студент
//        Student example = new Student();
//Сетваме му име
//        example.setName("Gosho");
//Пращаме го в базата
//        session.persist(example);
//Взимаме студента (вторият показател е Id)
//        Student student = session.get(Student.class, 2);
//        System.out.println(student.getId() + " " + student.getName());
//Заявка към базата
//Може да взимаме данните под формата на list
        List<Student> from_student = session.createQuery("FROM Student", Student.class).list();
        for (Student student : from_student) {
            System.out.println(student.getName());
        }

        session.getTransaction().commit();
        session.close();

    }
}