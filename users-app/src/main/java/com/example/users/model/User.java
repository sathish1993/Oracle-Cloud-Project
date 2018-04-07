package com.example.users.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="date_of_birth")
    private Date birthDate;
	
	@Column(name="role")
    private String role;
	
	@Column(name="department")
    private String department;
	
	@Column(name="email")
    private String email;
	
	@Column(name="image")
    private String image;

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private static final AtomicLong counter = new AtomicLong(100);
	
    public User() {

    }
    
    public User(String name, String lastName, Date birthDate, String role, String department, String email, int id, String image) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.department = department;
        this.email = email;     
        this.id = id;
        this.image = image;
    }

    public User(String name, String lastName, Date birthDate, String role, String department, String email, String image) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.department = department;
        this.email = email;     
        this.image = image;
        this.id = (int) counter.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", name=" + name + 
                ", lastName=" + lastName + ", birthDate=" + birthDate + 
                ", role=" + role + ", department=" + department + 
                ", email=" + email + '}';
    }    

}