package com.pluralsight.contracts;

import com.pluralsight.BankingCalculations;
import com.pluralsight.Vehicle;

public class SalesContract extends Contract{
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

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double salesTaxAmount,double recordingFee, double processingFee, boolean wantsToFinance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.recordingFee = recordingFee;
        this.salesTaxAmount = salesTaxAmount;
        this.processingFee = processingFee;
        this.wantsToFinance = wantsToFinance;
    }

    public static Contract buildFromEncodedData(String encodedData) {
        //super("asdf","asdf","asdf", null);
        String[] cols = encodedData.split("\\|");

        String contractDate = cols[1];
        String contractName = cols[2];
        String contractEmail = cols[3];
        int vehicleVin = Integer.parseInt(cols[4]);
        int vehicleYear = Integer.parseInt(cols[5]);
        String vehicleMake = cols[6];
        String vehicleModel = cols[7];
        String vehicleType = cols[8];
        String vehicleColor = cols[9];
        int vehicleMiles = Integer.parseInt(cols[10]);
        double vehiclePrice = Double.parseDouble(cols[11]);
        double salesContractTaxes = Double.parseDouble(cols[12]);
        double salesContractRecordingFee = Double.parseDouble(cols[13]);
        double salesContractProcessingFee = Double.parseDouble(cols[14]);
        boolean isFinanced = Boolean.parseBoolean(cols[16]);
        Vehicle v = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleMiles, vehiclePrice);

        return new SalesContract(contractDate,contractName, contractEmail, v,salesContractTaxes,salesContractRecordingFee, salesContractProcessingFee, isFinanced);

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
            return BankingCalculations.calculateLoanPayment(this.getTotalPrice(), financeRate, financeTerm);
        }
        else{
            return 0; //no financing, no monthly payment.
        }





    }

    @Override
    public String toString(){
        return "Contract for " + super.getCustomerName() + " to PURCHASE " + super.getVehicleSold();
    }

    @Override
    public String encode() {
        return "SALE|" +
                this.getDate() + "|" +
                this.getCustomerName() + "|" +
                this.getCustomerEmail() + "|" +
                this.getVehicleSold().encode() + "|" +
                this.getSalesTaxAmount() + "|" +
                this.getRecordingFee() + "|" +
                this.getProcessingFee() + "|" +
                this.getTotalPrice() + "|" +
                (this.isWantsToFinance() ? "YES" : "NO") + "|" +
                this.getMonthlyPayment();

    }

}
