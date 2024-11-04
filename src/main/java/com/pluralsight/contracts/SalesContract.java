package com.pluralsight.contracts;

import com.pluralsight.Vehicle;

public class SalesContract extends Contract {
    private double recordingFee;
    private double salesTaxAmount;
    private final double salesTaxPercentage =  0.05;
    private double processingFee;
    private boolean wantsToFinance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean wantsToFinance) {
        super(date, customerName, customerEmail, vehicleSold);

        this.salesTaxAmount = vehicleSold.getPrice() * salesTaxPercentage;
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 10000) ? 295 : 495;
        this.wantsToFinance = wantsToFinance;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isWantsToFinance() {
        return wantsToFinance;
    }

    public void setWantsToFinance(boolean wantsToFinance) {
        this.wantsToFinance = wantsToFinance;
    }

    @Override
    public double getTotalPrice() {
        return (super.getVehicleSold().getPrice() + this.salesTaxAmount + this.processingFee + this.recordingFee);
    }

    @Override
    public double getMonthlyPayment() {
        if(this.wantsToFinance){
            double financeRate = (super.getVehicleSold().getPrice() < 10000) ? 0.0525 : 0.0425;
            double financeTerm = (super.getVehicleSold().getPrice() < 10000) ? 24 : 48;
            return calculateLoanPayment(this.getTotalPrice(), financeRate, financeTerm);
        }
        else{
            return 0; //no financing, no monthly payment.
        }





    }

    private double calculateLoanPayment(double borrowedAmount, double loanRate, double months){
        return borrowedAmount * (loanRate/12 * Math.pow(1 + loanRate/12, months)) / (Math.pow(1 + loanRate/12, months) - 1);
    }

}
