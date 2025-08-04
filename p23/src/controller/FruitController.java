package controller;

import java.util.*;
import model.Customer;
import model.Fruit;
import model.Order;
import utils.InputHandle;
import view.ProgramView;

public class FruitController {

    private final ProgramView view = new ProgramView();
    private final InputHandle inputHandle = new InputHandle();

    private List<Fruit> fruits;
    private List<Customer> customers;
    private List<Order> orders;

    private final Map<String, List<Order>> customerOrderBuckets = new HashMap<>();
    private final Map<Integer, Fruit> fruitMap = new HashMap<>(); // Map for fast fruit lookup

    public void getMainMenuView() {
        view.printMenuTitle("fruit shop system".toUpperCase());
        view.printMenu();
    }

    private List<Fruit> generateFruitDB() {
        return List.of(
                new Fruit(1, "Banana", 2, "Vietnam"),
                new Fruit(2, "Apple", 5, "USA"),
                new Fruit(3, "Orange", 8, "Spain"),
                new Fruit(4, "Mango", 10, "India"),
                new Fruit(5, "Pineapple", 12, "Philippines"),
                new Fruit(6, "Grapes", 14, "France"),
                new Fruit(7, "Kiwi", 16, "New Zealand"),
                new Fruit(8, "Watermelon", 17, "Thailand"),
                new Fruit(9, "Papaya", 18, "Brazil"),
                new Fruit(10, "Strawberry", 20, "Korea")
        );
    }

    private List<Customer> generateCustomerDB() {
        return List.of(
                new Customer("HE181446", "Nguyen Van A"),
                new Customer("HE181447", "Duong Duc B"),
                new Customer("HE181448", "Le Phuong C")
        );
    }

    private List<Order> generateOrderDB() {
        return List.of(
                new Order("HE181446", new int[]{1, 4, 5}),
                new Order("HE181447", new int[]{10, 3, 1}),
                new Order("HE181448", new int[]{4, 5, 2})
        );
    }

    public void setupData() {
        // Reset trước khi tạo mới
        fruitMap.clear();
        customerOrderBuckets.clear();

        fruits = new ArrayList<>(generateFruitDB());
        customers = new ArrayList<>(generateCustomerDB());
        orders = new ArrayList<>(generateOrderDB());

        // Map fruitID to Fruit object
        for (Fruit fruit : fruits) {
            fruitMap.put(fruit.getId(), fruit);
        }

        // Map customerID to their orders
        for (Order order : orders) {
            customerOrderBuckets
                    .computeIfAbsent(order.getCustomerID(), k -> new ArrayList<>())
                    .add(order);
        }
    }

    public void createFruit() {
        view.printTitle("Create Fruit");
        String name = "Duc Anh";
        int price = 100;
        String origin = "China";

        addController(name, price, origin);
    }

    private void addController(String name, int price, String origin) {
        int id = fruits.isEmpty() ? 1 : fruits.get(fruits.size() - 1).getId() + 1;
        try {
            fruits.add(new Fruit(id++, name, price, origin));
            System.out.println("Create Fruit Succeed");
        } catch (Exception e) {
            System.err.println("Create Fail!");
        }
    }

    public void viewOrder() {
        view.printTitle("Customer Orders");

        for (Customer customer : customers) {
            String customerID = customer.getId();
            System.out.println("Customer: " + customer.getName() + " [" + customerID + "]");
            List<Order> ordersForCustomer = customerOrderBuckets.get(customerID);

            if (ordersForCustomer == null || ordersForCustomer.isEmpty()) {
                System.out.println("  No orders found.");
                continue;
            }

            view.printView("%-15s%-15s%-15s", "Product", "Price", "Origin");
            for (Order order : ordersForCustomer) {
                int total = 0;
                for (int fruitId : order.getFruitID()) {
                    Fruit fruit = fruitMap.get(fruitId);
                    if (fruit != null) {
                        view.printView("%-15s%-15s%-15s", fruit.getName(),
                                String.format("%s$", String.valueOf(fruit.getPrice())),
                                fruit.getOrigin());
                        total += fruit.getPrice();
                    }
                }
                view.printView("%20s: %d$", "Total", total);
            }
            System.out.println(); // Separator between customers
        }
    }

    public void Shopping() {
        view.printTitle("Shopping");
        int no = 0;

        if (fruits.isEmpty()) {
            System.out.println("No fruits available!");
            return;
        }

        view.printView("%-15s%-15s%-15s%-15s", "Item", "Fruit Name", "Origin", "Price");
        for (Fruit fruit : fruits) {
            view.printView("%-15d%-15s%-15s%-15s", ++no, fruit.getName(), fruit.getOrigin(),
                    String.format("%s$", String.valueOf(fruit.getPrice())));
        }

        int noInput = 8;
        Fruit selected = fruits.get(noInput - 1);

        System.out.println("Selected: " + selected.getName() + " from " + selected.getOrigin());

        fruits.remove(noInput - 1);
        if (fruitMap != null && fruitMap.containsKey(selected.getId())) {
            fruitMap.remove(selected.getId(), selected);
        }

        System.out.println("Fruit removed. Updated list:");

    }

    public void displayAfterDelete() {
        int no = 0;
        if (fruits.isEmpty()) {
            System.out.println("No fruits to display.");
            return;
        }

        view.printView("%-15s%-15s%-15s%-15s", "Item", "Fruit Name", "Origin", "Price");
        for (Fruit fruit : fruits) {
            view.printView("%-15d%-15s%-15s%-15s", ++no, fruit.getName(), fruit.getOrigin(),
                    String.format("%s$", String.valueOf(fruit.getPrice())));
        }
    }

    public void display() {
        int no = 0;
        if (fruits.isEmpty()) {
            System.out.println("No fruits to display.");
            return;
        }

        view.printView("%-15s%-15s%-15s%-15s", "Item", "Fruit Name", "Origin", "Price");
        for (Fruit fruit : fruits) {
            view.printView("%-15d%-15s%-15s%-15s", ++no, fruit.getName(), fruit.getOrigin(),
                    String.format("%s$", String.valueOf(fruit.getPrice())));
        }
    }

    // ------------------------------- INPUT -------------------------------
    public int getUserChoice(String msg, int min, int max) {
        return inputHandle.getUserLimitChoice(msg, min, max);
    }
}
