package Assignment1;

public class Computer {
    private String brand;
    private String model;
    private double price;
    private long serialNumber;
    private static long serialNumberCounter = 1000000;
    private static int numOfComputers = 0;

    public Computer() {
        brand = "Apple";
        model = "Mac";
        price = 2000;
        serialNumber = 11111111;
        numOfComputers++;
    }

    public Computer(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        serialNumber = serialNumberCounter;
        serialNumberCounter++;
        numOfComputers++;
    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setSerialNumber(long serialNumber){
        this.serialNumber = serialNumber;
    }

    public static int findNumberOfComputers(){
        return numOfComputers;
    }

    @Override
    public String toString() {
        return "brand :" + brand + "\n" +
                "model :" + model + "\n" +
                "SN:" + serialNumber + "\n" +
                "price:" + price + "\n";
    }

    public boolean equals(Computer c) {
        if (this == c) {
            return true;
        }
        if (c == null || getClass() != c.getClass()) {
            return false;
        }
//        Computer c = (Computer) c;
        if (this.brand.equals(c.brand) && this.model.equals(c.model) && this.price == c.price){
            return true ;
        }else {
            return false ;
        }

    }




}