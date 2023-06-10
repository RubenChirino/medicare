package com.example.medicare.Models;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {

    public enum Roles {
        PATIENT,
        DOCTOR,
        ADMIN,
    }

    public enum Genders {
        MALE,
        FEMALE,
    }

    public long id;
    public String email;
    public String password;
    public String name;
    public String lastName;
    public String address;
    public String gender;
    public String birthDate;
    public String role;

    public UserModel(int id, String email, String password, String name, String lastName, String address, String gender, String birthDate, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.birthDate = birthDate;
        this.role = role;
    }

    public UserModel(String email, String name, String lastName, String address, String gender, String birthDate, String role) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.birthDate = birthDate;
        this.role = role;
    }

    public UserModel(String email, String name, String lastName, String address, String gender) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.role = Roles.PATIENT.toString(); // DEFAULT ROLE
    }

    // New User
    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Roles.PATIENT.toString(); // DEFAULT ROLE
    }

}
