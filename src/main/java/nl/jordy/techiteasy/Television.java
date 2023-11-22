package nl.jordy.techiteasy;

import java.util.HashMap;

public class Television {

    private final String brand;
    private final String type;
    private final int size;
    private final int price;
    String SerialNumber;

    public Television(String brand, String type, int size, int price, String serialNumber) {
        this.brand = brand;
        this.type = type;
        this.size = size;
        this.price = price;
        this.SerialNumber = serialNumber;
    }

    public HashMap<String, String> getTelevisionInfo() {
        HashMap<String, String> television = new HashMap<>();
        television.put("brand", this.brand);
        television.put("type", this.type);
        television.put("size", String.valueOf(this.size));
        television.put("price", String.valueOf(this.price));
        television.put("SerialNumber", this.SerialNumber);
        return television;
    }
}
