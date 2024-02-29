package com.itstep.interviewform;

// Класс для хранения данных, введенные пользователем
public class Form {
    private String fullName;
    private int age;
    private int salary;
    private boolean workExperience;
    private boolean teamWork;
    private boolean businessTrip;
    private String[] answers;

    public Form(int quizCount) {
        // Конструктор для создания ответов в
        // зависимости от количества вопросов
        answers = new String[quizCount];
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(boolean workExperience) {
        this.workExperience = workExperience;
    }

    public boolean isTeamWork() {
        return teamWork;
    }

    public void setTeamWork(boolean teamWork) {
        this.teamWork = teamWork;
    }

    public boolean isBusinessTrip() {
        return businessTrip;
    }

    public void setBusinessTrip(boolean businessTrip) {
        this.businessTrip = businessTrip;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
