/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Mitchell Paul
 */
public class Application {

    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private double salary;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Map<String, Attachment> attachments = new LinkedHashMap<>();
    private double desiredSalary;
    private LocalDate startDate;
    private int id;
    private int jobID;
    private Instant dateTimeSubmitted;
    private boolean active;
    private String firstNameError;
    private String lastNameError;
    private String emailError;
    private String phoneError;
    private String resumeError;
    private String salaryError;
    private String startDateError;
    private String attachmentName;

    public Application(String title, String city, String state, boolean fullTime, String department, String experience, double salary, String firstName, String lastName, String email, String phone, double desiredSalary, LocalDate startDate, int id, int jobID, Instant dateTimeSubmitted, boolean active, String firstNameError, String lastNameError, String emailError, String phoneError, String resumeError, String salaryError, String startDateError, String attachmentName) {
        this.title = title;
        this.city = city;
        this.state = state;
        this.fullTime = fullTime;
        this.department = department;
        this.experience = experience;
        this.salary = salary;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.desiredSalary = desiredSalary;
        this.startDate = startDate;
        this.id = id;
        this.jobID = jobID;
        this.dateTimeSubmitted = dateTimeSubmitted;
        this.active = active;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.resumeError = resumeError;
        this.salaryError = salaryError;
        this.startDateError = startDateError;
        this.attachmentName = attachmentName;
    }

    public Application() {
        title = "";
        city = "";
        state = "";
        fullTime = false;
        department = "";
        experience = experience;
        salary = 0;
        firstName = "";
        lastName = "";
        email = "";
        phone = "";
        desiredSalary = 0;
        startDate = null;
        id = 0;
        jobID = 0;
        dateTimeSubmitted = null;
        active = true;
        firstNameError = "";
        lastNameError = "";
        emailError = "";
        phoneError = "";
        resumeError = "";
        salaryError = "";
        startDateError = "";
        attachmentName = "";
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
    
    public String getAttachmentName()
    {
        return attachmentName;
    }

    @Override
    public String toString() {
        return "Application{" + "title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAttachments(Map<String, Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setDesiredSalary(double desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public void setDateTimeSubmitted(Instant dateTimeSubmitted) {
        this.dateTimeSubmitted = dateTimeSubmitted;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public void setResumeError(String resumeError) {
        this.resumeError = resumeError;
    }

    public void setSalaryError(String salaryError) {
        this.salaryError = salaryError;
    }

    public void setStartDateError(String startDateError) {
        this.startDateError = startDateError;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public String getDepartment() {
        return department;
    }

    public String getExperience() {
        return experience;
    }

    public double getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Attachment getAttachment(String name) {
        return this.attachments.get(name);
    }

    public double getDesiredSalary() {
        return desiredSalary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getId() {
        return id;
    }

    public int getJobID() {
        return jobID;
    }

    public Instant getDateTimeSubmitted() {
        return dateTimeSubmitted;
    }

    public boolean isActive() {
        return active;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public String getResumeError() {
        return resumeError;
    }

    public String getSalaryError() {
        return salaryError;
    }

    public String getStartDateError() {
        return startDateError;
    }

    public Collection<Attachment> getAttachments() {
        return this.attachments.values();
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.put(attachment.getName(), attachment);
    }
}
