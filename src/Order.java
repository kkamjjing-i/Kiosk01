import java.util.ArrayList;

public class Order {
    ArrayList<Product> cart = new ArrayList<>();

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void clearCart() {
        cart.clear();
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.price;
        }
        return total;
    }
}
