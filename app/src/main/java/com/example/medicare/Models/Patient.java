package com.example.medicare.Models;

import java.util.Date;

public class Patient {

    public enum Roles {
        PATIENT,
        DOCTOR,
        ADMIN,
    }

    public enum Genders {
        MASCULINE,
        FEMININE,
    }

    public String email;

    public String name;

    public String lastName;

    public Date birthDate;

    public String address;

}
