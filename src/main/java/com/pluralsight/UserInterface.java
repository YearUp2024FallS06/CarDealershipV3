package com.pluralsight;

public class UserInterface {

    public static String filename = "inventory.csv";
    public Dealership currentDealership;

    public UserInterface(){
        currentDealership = DealershipFileManager.getFromCSV(filename);
    }


    public void display(){

        String options = """
                Please select from the following choices:
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit

                >>>\s""";

        int selection;

        // User Interface Loop
        do {
            System.out.println("Welcome to " + currentDealership.getName() + "!");
            selection = Console.PromptForInt(options);
            switch (selection) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> System.exit(0);
                default -> System.out.println("Invalid selection. Please try again.");
            }
        } while (selection != 99);



    }

    private void processRemoveVehicleRequest() {
    }

    private void processAddVehicleRequest() {
        //get lots of values from the user...
        int vin = Console.PromptForInt("Enter Vin: ");
        int year = Console.PromptForInt("Enter year: ");
        String make = Console.PromptForString("Enter make: ");
        String model = Console.PromptForString("Enter model: ");
        String vehicleType = Console.PromptForString("Enter vehicle type: ");
        String color = Console.PromptForString("Enter color:  ");
        int odometer = Console.PromptForInt("Enter odometer: ");
        double price = Console.PromptForDouble("Enter price: ");

        Vehicle v = new Vehicle(vin,year, make, model, vehicleType, color, odometer, price);

        currentDealership.addVehicleToInventory(v);
        DealershipFileManager.saveToCSV(currentDealership, filename);

    }

    private void processGetByVehicleTypeRequest() {
    }

    private void processGetByMileageRequest() {
    }

    private void processGetByColorRequest() {
    }

    private void processGetByYearRequest() {
    }

    private void processGetByMakeModelRequest() {
    }

    private void processGetByPriceRequest() {
        double min = Console.PromptForDouble("Enter min: ");
        double max = Console.PromptForDouble("Enter max: ");
        for(Vehicle v : currentDealership.getVehiclesByPrice(min, max)){
            displayVehicle(v);
        }
    }


    public void processGetAllVehiclesRequest(){
        for(Vehicle v : currentDealership.getInventory()){
            displayVehicle(v);
        }
    }

    public void displayVehicle(Vehicle v){
        System.out.println(v);
    }


}
