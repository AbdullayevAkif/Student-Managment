package com.example.studentmanagment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "can not empty")
    private String name;

@Enumerated(value = EnumType.STRING)
    private Major major;

@NotBlank(message = "can not empty")
@Email(message = "Please email format(as @email.com")
private String email;


@Pattern(regexp =  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$" , message = "At least one uppercase letter, one lowercase letter, and one digit")
    private String password;

    public Student(String name, Major major, String email, String password) {
        this.name = name;
        this.major = major;
        this.email = email;
        this.password = password;
    }

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public Student() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
