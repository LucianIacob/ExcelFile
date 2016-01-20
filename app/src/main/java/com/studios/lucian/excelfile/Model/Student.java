package com.studios.lucian.excelfile.Model;

/**
 * Created with love by Lucian and @Pi on 20.01.2016.
 */
public class Student {
    private String name;
    private String faculty;
    private String average;
    private String yearOfStudy;
    private String crt;

    public Student() {
    }

    public Student(String name, String faculty, String average, String yearOfStudy, String crt) {

        this.name = name;
        this.faculty = faculty;
        this.average = average;
        this.yearOfStudy = yearOfStudy;
        this.crt = crt;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", average='" + average + '\'' +
                ", yearOfStudy='" + yearOfStudy + '\'' +
                ", crt='" + crt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (faculty != null ? !faculty.equals(student.faculty) : student.faculty != null)
            return false;
        if (average != null ? !average.equals(student.average) : student.average != null)
            return false;
        if (yearOfStudy != null ? !yearOfStudy.equals(student.yearOfStudy) : student.yearOfStudy != null)
            return false;
        return !(crt != null ? !crt.equals(student.crt) : student.crt != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        result = 31 * result + (average != null ? average.hashCode() : 0);
        result = 31 * result + (yearOfStudy != null ? yearOfStudy.hashCode() : 0);
        result = 31 * result + (crt != null ? crt.hashCode() : 0);
        return result;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        this.crt = crt;
    }
}
