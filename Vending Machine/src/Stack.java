import java.util.ArrayList;

public class Stack {
    private String partName;
    ArrayList<Item> partItems = new ArrayList<>();          // Arraylist for keeping each stack's items

    public Stack(String partName){
        this.partName = partName;
    }

    public String getPartName() {
        return partName;
    }
    public void push(Item item){                            // Push method for Stack class in order to add item to arraylist
        this.partItems.add(item);
    }
    public void pop(int amount){                            // Pop method for Stack class in order to remove items from arraylist
        int size = partItems.size();
        for (int i = size-1; i > size-amount ; i--) {
            this.partItems.remove(i);
        }
    }
}
