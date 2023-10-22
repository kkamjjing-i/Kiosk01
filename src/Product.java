public class Product extends Menu {
    double price;

    public Product(String name, String description, double price) {
        super(name, description);
        this.price = price;
    }
}
