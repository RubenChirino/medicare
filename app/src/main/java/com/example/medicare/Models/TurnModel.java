package com.example.medicare.Models;

public class TurnModel {

    public enum MedicalSpecialty {
        CARDIOLOGY,
        DERMATOLOGY,
        ENDOCRINOLOGY,
        GASTROENTEROLOGY,
        NEUROLOGY,
        OPHTHALMOLOGY,
        OTOLARYNGOLOGY,
        PEDIATRICS,
        PSYCHIATRY,
        TRAUMATOLOGY,
        UROLOGY
    };

    public long id;
    public long userId;
    public String medicalSpeciality;
    public String date;
    public String time;

    public TurnModel(long id, long userId, String medicalSpeciality, String date, String time) {
        this.id = id;
        this.userId = userId;
        this.medicalSpeciality = medicalSpeciality;
        this.date = date;
        this.time = time;
    }

    public TurnModel(long id, String medicalSpeciality, String date, String time) {
        this.id = id;
        this.medicalSpeciality = medicalSpeciality;
        this.date = date;
        this.time = time;
    }

    public TurnModel(String medicalSpeciality, String date, String time) {
        this.medicalSpeciality = medicalSpeciality;
        this.date = date;
        this.time = time;
    }

}
