import java.util.*;

/**
 * Created by melmo on 12/1/16.
 */
public class Inventory {
    private int CURRENT_ITEM_ID = 1000;
    private HashMap<Integer, Item> inventory = new HashMap<>();
    private Scanner input = new Scanner(System.in);


    public void whatYouWant(){

        String str = "\n\nWhat would you like to do? < 1, 2, 3, 4, 5, or 6 >\n\n" +
                "1 : Print current inventory\n" +
                "2 : Create a new inventory item\n" +
                "3 : Delete an item from inventory\n" +
                "4 : Sell an item from inventory\n" +
                "5 : Change the price of an item\n" +
                "6 : Change the quantity in stock of an item\n";

        System.out.println(str);

        int input = getIntInput(this.input.nextLine());

        while (input < 1 || input > 6){
            input = getIntInput(this.input.nextLine());
        }

        switch(input) {
            case 1:
                printAll();
                break;
            case 2:
                newItem();
                break;
            case 3:
                deleteItem();
                break;
            case 4:
                sellItem();
                break;
            case 5:
                updatePrice();
                break;
            case 6:
                updateQuantity();
                break;
            case 7:
                exit();
                break;
        }


    }

    private void newItem() {

        Item item  = new Item(inputName(), inputQuantity(), inputPrice());
        this.inventory.put(CURRENT_ITEM_ID, item);
        this.CURRENT_ITEM_ID++;

        System.out.format("\n%d <%s> @ %.2f has been added to inventory.\n", item.getQuantity(), item.getName(), item.getPrice());

        whatYouWant();
    }

    private void deleteItem(){

        int id =0;
        while (id == 0) {
            System.out.println("\nWhat is the ID of the item you would like to delete?");
            id = getIntInput(this.input.nextLine());
            if (this.inventory.get(id) == null){
                System.out.println("\nNo such item exists at that ID.\n");
                id = 0;
            }
        }

        this.inventory.remove(id);
        whatYouWant();
    }

    private void sellItem(){
        Item item = getItem();
        int itemQty = item.getQuantity();
        int qty = inputQuantity();

        while (qty > itemQty) {
            System.out.println("\nThere is not enough in stock!");
            qty = inputQuantity();
        }

        item.setQuantity(itemQty-qty);
        System.out.format("\n%d <%s> have been removed from inventory.", qty, item.getName());

        whatYouWant();
    }

    private void print(int id){
        String str = String.format("ID#%d %s\n", id, inventory.get(id).toString());

        System.out.format(str);
        for (int i=0; i<str.length(); i++){
            System.out.print('-');
        }
        System.out.print("\n");
    }

    private void printAll(){
        if (inventory.isEmpty()){
            System.out.println("\nThere are no items in the inventory.");
        }
        System.out.println("");
        for(Integer id : inventory.keySet()){
            print(id);
        }
        whatYouWant();
    }

    private void updatePrice(){
        Item item = getItem();
        double price = inputPrice();
        item.setPrice(price);
        whatYouWant();
    }

    private void updateQuantity(){
        Item item = getItem();
        int qty = inputQuantity();
        item.setQuantity(qty);
        whatYouWant();
    }

    private void exit(){
        System.exit(0);
    }

//  Helper Functions

    private Item getItem(){
        int id = -1;
        Item item = null;
        while (id < 0) {
            System.out.println("\nWhat is the ID of the item?");
            id = getIntInput(this.input.nextLine());
            item = this.inventory.get(id);
            if (item == null){
                System.out.println("\nNo such item exists at that ID.");
                id = -1;
            }
        }
        return item;
    }

    private double inputPrice(){
        System.out.println("\nWhat is the price?");
        double dub = getDubInput(this.input.nextLine());
        while (dub < 0){
            System.out.println("\nPlease enter a price greater than or equal to 0.");
            dub = getDubInput(this.input.nextLine());
        }
        return dub;
    }

    private int inputQuantity(){
        System.out.println("\nWhat is the quantity?");
        int qty = getIntInput(this.input.nextLine());
        while (qty < 0){
            System.out.println("\nPlease enter a quantity greater than or equal to 0.");
            qty = getIntInput(this.input.nextLine());
        }
        return qty;
    }

    private String inputName(){
        System.out.println("\nWhat is the name of the item?");
        String str = "";
        while (str.isEmpty()){
            System.out.println("\nPlease enter a name for the item?");
            str = this.input.nextLine();
        }
        return str;
    }

    private int getIntInput(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            System.out.println("\nPlease enter a valid number.");
            return -1;
        }
    }

    private double getDubInput(String str){
        try {
            return Double.parseDouble(str);
        }
        catch (NumberFormatException e){
            System.out.println("\nPlease enter a valid number.");
            return -1;
        }
    }

}
