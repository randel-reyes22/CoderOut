package sample.Classes.Entities;

public class Product {

    private String prodName;
    private Double prodPrice;

    public Product(){
        //empty constructor
    }

    //constructor
    public Product(String prodName, Double prodPrice) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }
}
