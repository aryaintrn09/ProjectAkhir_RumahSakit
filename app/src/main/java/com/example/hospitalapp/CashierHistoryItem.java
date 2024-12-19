package com.example.hospitalapp;

public class CashierHistoryItem {
    private String patientName;
    private String doctorName;
    private double billAmount;
    private String paymentMethod;

    public CashierHistoryItem(String patientName, String doctorName, double billAmount, String paymentMethod) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.billAmount = billAmount;
        this.paymentMethod = paymentMethod;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}