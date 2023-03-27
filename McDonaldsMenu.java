import java.util.ArrayList;
import java.util.Scanner;

public class McDonaldsMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> order = new ArrayList<Item>(); // create an ArrayList to store items ordered
        double totalCost = 0.0;

        // Display menu options
        System.out.println("Welcome to McDonald's! Please take a look at our menu below and let us know what you'd like to order.\n");
        Menu menu = new Menu();
        menu.displayMenu();

        //TODO: Allow user to choose a product they want - DONE
        while (true) {
            System.out.print("\nEnter the number of the item you'd like to order (0 to finish or -'number' to remove item from your order): ");
            String orderItem = scanner.nextLine();

            //first check if user input is empty
            if(orderItem.isEmpty()){
                System.out.println("Invalid selection. Please try again.");
                continue;
            }

            int parsed = Integer.parseInt(orderItem);
            Item item = menu.getItem(parsed); // get the Item object corresponding to user's input
            if (orderItem.equals("0")){
                break;
            }
            //TODO: If number is negative remomve it from order ELSE add it - DONE
            if(parsed < 0){
                Item itemToRemove = menu.getItem(-parsed);
                //if user chooses to remove an item and this item is on the list we remove it
                if(itemToRemove != null && order.contains(itemToRemove)){
                    order.remove(itemToRemove);
                    totalCost -= itemToRemove.getCost();
                    System.out.println("\nYour order:");
                    displayOrder(order);
                    System.out.println();
                    System.out.printf("Total cost: £%.2f", totalCost);
                }else{
                    System.out.println("The item you want to remove is not on your list");
                }
            }
            //If number is too high then item is null, this is to prevent NumberFormat exception
            if (item == null) {
                System.out.println("Invalid selection. Please try again.");
                continue;
            }
            order.add(item); // add the Item object to the order ArrayList
            totalCost += item.getCost(); // add the cost of the item to the total cost
            System.out.println("\nYour order:");
            displayOrder(order);
            System.out.println();
            System.out.printf("Total cost: £%.2f", totalCost);

        }
        scanner.close();
    }
    public static void displayOrder(ArrayList<Item> order){
        for (Item item : order) {
            System.out.println("- " + item.getName() + " (£" + item.getCost() + ")");
        }
    }
}
//TODO: Class to store all menu options - DONE
class Menu {
    private ArrayList<Item> items;

    public Menu() {
        // Initialize the items ArrayList with McDonald's menu items
        items = new ArrayList<Item>();
        items.add(new Item("Big Mac", 5.99));
        items.add(new Item("Quarter Pounder with Cheese", 4.99));
        items.add(new Item("McDouble", 2.99));

        items.add(new Item("Chicken Wrap Spicy", 2.99));
        items.add(new Item("Chicken Wrap BBQ", 3.99));

        items.add(new Item("Fries", 0.99));
        items.add(new Item("Salad", 0.99));
        items.add(new Item("6 Nugets", 4));

        items.add(new Item("Coca Cola", 0.99));
        items.add(new Item("Sprite", 0.99));
        items.add(new Item("Oasis", 4));
    }

    public void displayMenu() {
        String[] categories = {"Burgers", "Wraps", "Sides", "Drinks"};
        int[] categoryIndexes = {0, 3, 5, 8}; //Where categories should be for example from item 1 to 3 = Burgers
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String category = "";

            for (int j = 0; j < categories.length; j++) {
                if (i == categoryIndexes[j]) {//if element i is equal to element j in category index, change category
                    category = categories[j] + ":";
                    System.out.println(category);//here pint category name once
                    break;
                }
            }
            System.out.println((i + 1) + ". " + item.getName() + " - £" + item.getCost());
        }
    }
    //Error message if user input too big or too small number of order
    public Item getItem(int itemNumber) {
        if (itemNumber < 1 || itemNumber > items.size()) {
            return null;
        }
        return items.get(itemNumber - 1);
    }
}
//TODO: Class to create an Item - DONE
class Item {
    private String name;
    private double cost;

    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
}
