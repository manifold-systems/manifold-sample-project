package abc.stuff;

import manifold.ext.delegation.rt.api.link;
import manifold.ext.delegation.rt.api.part;

public class DelegationExample {
    interface Person {
        String getName();
        String getTitle();
        String getTitledName();
    }
    interface Teacher extends Person {
        String getDept();
    }
    interface Student extends Person {
        String getMajor();
    }
    interface TA extends Student, Teacher {
    }

    static @part class PersonPart  implements Person {
        private final String name;
        public PersonPart(String name) { this.name = name; }
        public String getName() { return name; }
        public String getTitle() { return "Person"; }
        public String getTitledName() { return getTitle() + " " + getName(); }
    }
    static @part class TeacherPart implements  Teacher {
        @link Person person;
        private final String dept;
        public TeacherPart(Person person, String dept) {
            this.person = person;
            this.dept = dept;
        }
        public String getTitle() { return "Teacher"; }
        public String getDept() { return dept; }
    }
    static @part class StudentPart implements Student {
        @link Person person;
        private final String major;
        public StudentPart(Person person, String major) {
            this.person = person;
            this.major = major;
        }
        public String getTitle() { return "Student"; }
        public String getMajor() { return major; }
    }
    static @part class TaPart implements TA {
        @link(share=Person.class) Student student;
        @link Teacher teacher;
        public TaPart(Student student) {
            this.student = student;
            this.teacher = new TeacherPart(student, "Math");
        }
        public String getTitle() { return "TA"; }
    }

    public static void main(String[] args) {
        Person person = new PersonPart("Milton");
        Student student = new StudentPart(person, "CS");
        TA ta = new TaPart(student);
        String titledName = ta.getTitledName();
        System.out.println(titledName);
    }
}
