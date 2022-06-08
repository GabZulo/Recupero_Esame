package it.itsrizzoli;

public class Auto {
    int id;
    String brand;
    double price;

    public Auto(int id, String brand, double price) {
        this.id = id;
        this.brand = brand;
        this.price = price;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "brand='" + brand + '\'' +
                ", id=" + id +
                ", price=" + price +
                '}';
    }
}
