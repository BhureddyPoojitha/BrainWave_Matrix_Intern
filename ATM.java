import java.util.Scanner;

public class ATM {
    // Initial data for the user
    private static int balance = 1000; // Default balance
    private static final String USERNAME = "pooja";
    private static final String PIN = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your username: ");
        String usernameInput = scanner.nextLine();

        System.out.print("Enter your PIN: ");
        String pinInput = scanner.nextLine();

        // Authentication check
        if (USERNAME.equals(usernameInput) && PIN.equals(pinInput)) {
            System.out.println("\nLogin successful!\n");
            boolean exit = false;

            while (!exit) {
                // Display the menu
                System.out.println("ATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: // Check Balance
                        System.out.println("\nYour current balance is: $" + balance);
                        break;

                    case 2: // Deposit Money
                        System.out.print("\nEnter the amount to deposit: ");
                        int depositAmount = scanner.nextInt();
                        if (depositAmount > 0) {
                            balance += depositAmount;
                            System.out.println("Deposit successful! Your new balance is: $" + balance);
                        } else {
                            System.out.println("Invalid amount. Please enter a positive value.");
                        }
                        break;

                    case 3: // Withdraw Money
                        System.out.print("\nEnter the amount to withdraw: ");
                        int withdrawAmount = scanner.nextInt();
                        if (withdrawAmount > 0 && withdrawAmount <= balance) {
                            balance -= withdrawAmount;
                            System.out.println("Withdrawal successful! Your new balance is: $" + balance);
                        } else {
                            System.out.println("Invalid amount. Please check your balance and try again.");
                        }
                        break;

                    case 4: // Exit
                        System.out.println("\nThank you for using the ATM. Goodbye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            }
        } else {
            System.out.println("\nInvalid username or PIN. Access denied.");
        }

        scanner.close();
    }
}
