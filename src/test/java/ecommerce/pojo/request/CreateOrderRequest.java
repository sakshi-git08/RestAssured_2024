package ecommerce.pojo.request;

import java.util.List;

public class CreateOrderRequest {
    public List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
