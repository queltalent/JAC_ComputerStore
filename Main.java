package Assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final String PASSWORD = "password";
    private static Computer[] inventory;
//    private static int currentComputers = 0;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to our Computer Store!");
        int choice = 0;
        int maxComputers = 0;
        while (maxComputers <= 0) {
            System.out.print("Please enter the maximum number of computers the store can contain: ");
            try {
                maxComputers = kb.nextInt();
                if (maxComputers <= 0) {
                    System.out.println("Number must be positive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                kb.next();
            }
        }
        inventory = new Computer[maxComputers];

        do {
            try {
                System.out.println("\nWhat do you want to do?");
                System.out.println("1. Enter new computers (password required)");
                System.out.println("2. Change information of a computer (password required)");
                System.out.println("3. Display all computers by a specific brand");
                System.out.println("4. Display all computers under a certain price.");
                System.out.println("5. Quit");
                System.out.print("Please enter your choice > ");
                choice = kb.nextInt();
                kb.nextLine();

                switch (choice) {
                    case 1:
                        inputNewComputers(kb);
                        break;
                    case 2:
                        changeComputerInfo(kb);
                        break;
                    case 3:
                        findComputersBy(kb);
                        break;
                    case 4:
                        findCheaperThan(kb);
                        break;
                    case 5:
                        System.out.println("Thank you for using our Computer Store Inventory Manager. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number(1-5).");
                kb.next();
            }
        }while (choice != 5) ;
            kb.close();
    }

    private static void inputNewComputers(Scanner scanner) {
        int numComputersToAdd = -1;
        boolean pass = authenticate(scanner);
        while (pass) {
            while (numComputersToAdd < 0){
                System.out.print("\nHow many computers do you want to add? ");
                numComputersToAdd = scanner.nextInt();
                scanner.nextLine();
                if (numComputersToAdd < 0){
                    System.out.println("Number must be positive.");
                }
            }
            int spacesLeft = inventory.length - Computer.findNumberOfComputers();
            if (numComputersToAdd <= spacesLeft) {
                for (int i = 0; i < numComputersToAdd; i++) {
                    addComputer(scanner);
                    System.out.println();
                }
                System.out.println("Successfully added " + numComputersToAdd + " computer(s).");
                pass = false;
            } else {
                System.out.println("Error: There are only " + spacesLeft + " empty slots available. Cannot add " + numComputersToAdd + " computers.");
                break;
            }
        }
    }
    private static boolean authenticate(Scanner scanner) {
        int times = 0;
        System.out.print("\nPlease enter password: ");
        while (times < 3) {
            String inputPassword = scanner.nextLine();
            if (PASSWORD.equals(inputPassword)) {
                return true;
            } else if (times < 2) {
                System.out.print("Invalid password! You have " + (2 - times) + " tries left! Please enter your password:");
                times++;
            } else {
                System.out.println("Invalid password! You have 0 tries left! ");
                times++;
            }
        }
        return false;
    }

    private static void addComputer(Scanner scanner) {
        try{
            System.out.print("Computer #" + (Computer.findNumberOfComputers() + 1) + " - Enter brand: ");
            String brand = scanner.nextLine();
            System.out.print("Computer #" + (Computer.findNumberOfComputers() + 1) + " - Enter model: ");
            String model = scanner.nextLine();
            double price = 0;
            do {
                System.out.print("Computer #" + (Computer.findNumberOfComputers() + 1) + " - Enter price: ");
                if (scanner.hasNextDouble()){
                    price = scanner.nextDouble();
                    if (price <= 0){
                        System.out.println("Invalid input. Price must be positive.");
                    }
                }else {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.next();
                }
                scanner.nextLine();
            }while (price <= 0);

            int index = Computer.findNumberOfComputers();
            inventory[index] = new Computer(brand, model, price);
        }catch (InputMismatchException e){
            System.out.println("An error occurred: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void changeComputerInfo(Scanner scanner) {
        if (authenticate(scanner)) {
            if (Computer.findNumberOfComputers() == 0){
                System.out.print("Contain is empty, no computer can be changed . enter -1 to exit. ");
            }else {
                System.out.print("Which computer number do you like to edit? [1 - " + Computer.findNumberOfComputers() + "] or enter -1 to exit. ");
            }

            int computerIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            if (computerIndex >= 0 && computerIndex < Computer.findNumberOfComputers() && inventory[computerIndex] != null) {
                Computer computer = inventory[computerIndex];
                System.out.println("Computer #" + (computerIndex + 1));
                System.out.println(computer);

                int attributeChoice = 0;
                do {
                    try {
                        System.out.println("What information would you like to change?");
                        System.out.println("1. brand");
                        System.out.println("2. model");
                        System.out.println("3. SN");
                        System.out.println("4. price");
                        System.out.println("5. Quit");
                        System.out.print("Enter your choice > ");

                        attributeChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (attributeChoice) {
                            case 1:
                                System.out.print("Enter new brand: ");
                                computer.setBrand(scanner.nextLine());
                                break;
                            case 2:
                                System.out.print("Enter new model: ");
                                computer.setModel(scanner.nextLine());
                                break;
                            case 3:
                                System.out.print("Enter new SN: ");
                                computer.setSerialNumber(scanner.nextLong());
                                scanner.nextLine();
                                break;
                            case 4:
                                System.out.print("Enter new price: ");
                                double price = getUserInput();
                                computer.setPrice(price);
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                                break;
                        }

                    System.out.println("\nEdit successfully!\n");
                    System.out.println("Computer #" + (computerIndex + 1));
                    System.out.println(computer);

                    }catch (InputMismatchException e){
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                        attributeChoice = 0;
                        scanner.nextLine();
                    }
                } while (attributeChoice != 5);
            } else if ((computerIndex + 1) != -1){
                System.out.print("No valid computer found at the specified number. Would you like to enter another computer number? (y/n)");
                String decision = scanner.nextLine();
                if (decision.equalsIgnoreCase("y")) {
                    changeComputerInfo(scanner);
                }
            }
        } else {
            System.out.println("Incorrect password. Returning to main menu.");
        }
    }

    private static void findComputersBy(Scanner scanner) {
        System.out.print("What brand name are you searching for? ");
        String brand = scanner.next();
        boolean found = false;
        for (int i = 0; i< inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getBrand().equalsIgnoreCase(brand)) {
                System.out.println("Computer #" + (i + 1));
                System.out.println(inventory[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No entries matched your query !");
        }
    }
    private static void findCheaperThan(Scanner scanner) {
        System.out.print("Enter the price limit for your search:");
        double price = scanner.nextDouble();
        boolean found = false;
        for (int i = 0; i< inventory.length; i++) {
            if (inventory[i] != null && inventory[i] .getPrice() < price) {
                System.out.println("Computer #" + (i + 1));
                System.out.println(inventory[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No computers found under the price $" + price);
        }
    }

    public static double getUserInput() {
        Scanner scanner = new Scanner(System.in);
        double value = 0.0;
        boolean validInput = false;

        while (!validInput) {
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value > 0) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Price must be positive.");
                    System.out.print("Please input again: ");
                }
            } else {
                scanner.next();
                System.out.println("Invalid input. Please enter a numeric value.");
                System.out.print("Please input again: ");
            }
        }
        return value;
    }
}
