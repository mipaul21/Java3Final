/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Mitchell Paul
 */
public class Job implements Comparable<Job>   {

    //Private Instance Variables for each item.
    private int id;
    private boolean active;
    private LocalDate dateCreated;
    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private String wageCategory;
    private double salary;
    
        
    // Eventually should compare dates instead of placeholder title and wageCategory, in order to actually read in
    // all of the csv data.
    @Override
    public int compareTo(Job other) {
                int result = this.title.compareToIgnoreCase(other.title);
        if(result == 0) {
            result = this.wageCategory.compareToIgnoreCase(other.wageCategory);
        }
        return result;
    }

    // Default Constructor
    public Job()
    {
        id = 0;
        active = true;
        dateCreated = null;
        title = "";
        city = "";
        state = "";
        fullTime = true;
        department = "";
        experience = "";
        wageCategory = "";
        salary = 0.0;
        
    }

    // Regular Constructor
    public Job(int id, boolean active, LocalDate dateCreated, String title, String city, String state,
            boolean fullTime, String department, String experience, String wageCategory, double salary) {
        this.id = id;
        this.active = active;
        this.dateCreated = dateCreated;
        this.title = title;
        this.city = city;
        this.state = state;
        this.fullTime = fullTime;
        this.department = department;
        this.experience = experience;
        this.wageCategory = wageCategory;
        this.salary = salary;
    }

    // a toString method to display the title, location, department, and active status (Active or Inactive)
    /* 
    @Override
    public String toString() {
        return "Job{" + "title=" + title + ", city=" + city + "state=" + state + "department=" + department + "active=" + active + '}';
    }
    */
    
    // a compareTo method to order items by their dateCreated 
    // (newest first, oldest last). Jobs with the same date are ordered by their title.
    public LocalDate compareTo(LocalDate firstDate, LocalDate otherDate){
        LocalDate a = firstDate;   
        if(otherDate.isBefore(firstDate))
        {
            return otherDate;
        } else {
            return firstDate;
        }
    }

    // Getters and Setters for private variables.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getWageCategory() {
        return wageCategory;
    }

    public void setWageCategory(String wageCategory) {
        this.wageCategory = wageCategory;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
