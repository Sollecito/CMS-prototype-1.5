import java.util.Scanner;    

public class ContactController {

     
    private ContactList list;      
    private ContactMenu menu;      
    private Scanner     input;     
    private int         nextId;    

     
     
    public ContactController() {
        input  = new Scanner(System.in);
        list   = new ContactList();
        menu   = new ContactMenu(list);    
        nextId = 1;
    }

     
     
    public void start() {
        System.out.println("   ╔══════════════════════════════╗");
        System.out.println("   ║       CONTACT MANAGER        ║");
        System.out.println("   ╚══════════════════════════════╝");
        System.out.println("   Press ENTER to start...");
        input.nextLine();    
        mainLoop();
    }

     
     
     
    public void mainLoop() {
        int choice;

        do {
            menu.showOptions();
            System.out.print("Choose: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    addAtEnd();
                    break;
                case 2:
                    addAtPosition();
                    break;
                case 3:
                    menu.showContacts();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    System.out.println("Size: " + list.getSize()
                            + ", Capacity: " + list.getCapacity());
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        } while (choice != 0);
    }

     
    private void addAtEnd() {
        Contact c = askForContact();
        list.insertAtEnd(c);
        nextId++;
        System.out.println("Contact added with ID " + c.getId() + ".");
    }

     
    private void addAtPosition() {
        menu.showContacts();
        System.out.print("Insert at position (1 = first): ");
        int position = readInt();

         
        if (position < 1 || position > list.getSize() + 1) {
            System.out.println("Invalid position! Pick 1 to " + (list.getSize() + 1) + ".");
            return;
        }

        Contact c = askForContact();
         
        if (list.insertAtPosition(position - 1, c)) {
            nextId++;
            System.out.println("Contact inserted.");
        } else {
            System.out.println("Could not insert (insertAtPosition() not written yet?).");
        }
    }

     
    private void deleteContact() {
        menu.showContacts();
        System.out.print("Delete position: ");
        int position = readInt();

        if (list.delete(position - 1)) {
            System.out.println("Deleted.");
        } else {
            System.out.println("Could not delete (bad position, or delete() not written yet).");
        }
    }

     
     
    private void searchContact() {
        System.out.println("Search using:");
        System.out.println("  1. Linear search");
        System.out.println("  2. Binary search");
        System.out.println("  3. Recursive binary search");
        System.out.print("Choose: ");
        int type = readInt();

        System.out.print("ID to find: ");
        int id = readInt();

        int result = -1;

        switch (type) {
            case 1:
                result = list.linearSearch(id);
                break;
            case 2:
                 
                if (!list.isSortedById()) {
                    System.out.println("The list is NOT sorted by ID, so binary search cannot be used.");
                    return;
                }
                result = list.binarySearch(id);
                break;
            case 3:
                if (!list.isSortedById()) {
                    System.out.println("The list is NOT sorted by ID, so binary search cannot be used.");
                    return;
                }
                result = list.recursiveBinarySearch(id);
                break;
            default:
                System.out.println("Invalid search type.");
                return;
        }

        if (result != -1) {
            System.out.println("Found:");
            menu.showContact(result + 1, list.get(result));
        } else {
            System.out.println("Not found.");
        }
    }

     

     
     
    private Contact askForContact() {
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Phone: ");
        String phone = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        return new Contact(nextId, name, phone, email);
    }

     
     
     
     
    private int readInt() {
        while (!input.hasNextInt()) {
            input.next();                                 
            System.out.print("Numbers only, try again: ");
        }
        int number = input.nextInt();
        input.nextLine();
        return number;
    }
}
