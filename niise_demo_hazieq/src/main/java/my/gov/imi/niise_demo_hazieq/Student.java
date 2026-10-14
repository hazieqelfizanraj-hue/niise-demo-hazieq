package my.gov.imi.niise_demo_hazieq;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private final Grades grades;

    public Student(Grades grades) {
        this.grades = grades;
    }

    public void retrieveGrades() {
        grades.getGrades();
    }
}
