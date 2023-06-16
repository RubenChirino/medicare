package com.example.medicare.Utils;

import com.example.medicare.Models.TurnModel;

import java.util.HashMap;
import java.util.Map;

public class MedicalSpecialtyUtil {
    private static Map<TurnModel.MedicalSpecialty, String> specialtyLocations = new HashMap<>();

    static {
        specialtyLocations.put(TurnModel.MedicalSpecialty.CARDIOLOGY, "av. bartolomé mitre 1151, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.DERMATOLOGY, "Arenales 1818, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.ENDOCRINOLOGY, "Av. Rivadavia 4781, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.GASTROENTEROLOGY, "Herrera 541, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.NEUROLOGY, "C. Lincoln 3789, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.OPHTHALMOLOGY, "Av. Pueyrredón 1486, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.OTOLARYNGOLOGY, "Bartolomé Mitre 137, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.PEDIATRICS, "Av. Sta. Fe 2485, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.PSYCHIATRY, "San Martín de Tours 2980, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.TRAUMATOLOGY, "Av. Sta. Fe 3700, caba");
        specialtyLocations.put(TurnModel.MedicalSpecialty.UROLOGY, "25 de Mayo 264, caba");
    }

    public static String getLocationForSpecialty(TurnModel.MedicalSpecialty specialty) {
        return specialtyLocations.get(specialty);
    }
}
