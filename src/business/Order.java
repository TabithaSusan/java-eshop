package business;

public class Order {
    int customerID;
    String item;
    int price;
    boolean completed;

    public Order(int customerID, String item, int price, boolean completed) {
        this.customerID = customerID;
        this.item = item;
        this.price = price;
        this.completed = completed;
    }

    /**
     * Method to fetch the ID of Costumer saved in the database table costumer
     * @return int of ID of costumer
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Method to fetch the Item saved in the database table article
     * @return String of Item TODO werden hier alle parameter übergeben, wenn ja wozu noch price einzeln abrufen? Oder wrd hier nur der Name abgerufen?
     */
    public String getItem() {
        return item;
    }

    /**
     * Method to fetch the total price of Order
     * @return int of price of order
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method to confirm if order has been filed successfully or not
     * @return true if order has been filed, false if order has not been saved
     */
    public boolean isCompleted() {
        return completed;
    }
}
