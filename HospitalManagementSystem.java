import java.util.*;

// Main Class
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("City Hospital");
        hospital.start();
    }
}

// Hospital Class
class Hospital {
    private String name;
    private List<Patient> patients;
    private List<Staff> staff;
    private Inventory inventory;
    private Scanner scanner;

    public Hospital(String name) {
        this.name = name;
        this.patients = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.inventory = new Inventory();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to " + name + " Management System");
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. View Electronic Health Records (EHR)");
            System.out.println("4. Billing and Invoicing");
            System.out.println("5. Manage Inventory");
            System.out.println("6. Manage Staff");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    viewEHR();
                    break;
                case 4:
                    billingAndInvoicing();
                    break;
                case 5:
                    manageInventory();
                    break;
                case 6:
                    manageStaff();
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void registerPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter patient gender: ");
        String gender = scanner.nextLine();

        Patient patient = new Patient(name, age, gender);
        patients.add(patient);

        System.out.println("Patient registered successfully! Patient ID: " + patient.getId());
    }

    private void scheduleAppointment() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Patient patient = findPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(patient, date);
        System.out.println("Appointment scheduled for " + patient.getName() + " on " + date);
    }

    private void viewEHR() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Patient patient = findPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println(patient.getEHR());
    }

    private void billingAndInvoicing() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Patient patient = findPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter amount to bill: ");
        double amount = scanner.nextDouble();

        System.out.println("Invoice generated for " + patient.getName() + ": $" + amount);
    }

    private void manageInventory() {
        System.out.println("Inventory Management:");
        System.out.println("1. View Items");
        System.out.println("2. Add Item");
        System.out.println("3. Remove Item");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                inventory.viewItems();
                break;
            case 2:
                System.out.print("Enter item name: ");
                String itemName = scanner.nextLine();
                System.out.print("Enter item quantity: ");
                int quantity = scanner.nextInt();
                inventory.addItem(itemName, quantity);
                break;
            case 3:
                System.out.print("Enter item name to remove: ");
                String itemToRemove = scanner.nextLine();
                inventory.removeItem(itemToRemove);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void manageStaff() {
        System.out.println("Staff Management:");
        System.out.println("1. View Staff");
        System.out.println("2. Add Staff");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                for (Staff s : staff) {
                    System.out.println(s);
                }
                break;
            case 2:
                System.out.print("Enter staff name: ");
                String name = scanner.nextLine();
                System.out.print("Enter staff role: ");
                String role = scanner.nextLine();
                Staff newStaff = new Staff(name, role);
                staff.add(newStaff);
                System.out.println("Staff member added successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }
}

// Patient Class
class Patient {
    private static int idCounter = 1;
    private int id;
    private String name;
    private int age;
    private String gender;

    public Patient(String name, int age, String gender) {
        this.id = idCounter++;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEHR() {
        return "EHR for " + name + ":\nAge: " + age + "\nGender: " + gender;
    }
}

// Appointment Class
class Appointment {
    private Patient patient;
    private String date;

    public Appointment(Patient patient, String date) {
        this.patient = patient;
        this.date = date;
    }
}

// Inventory Class
class Inventory {
    private Map<String, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(String item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
        System.out.println("Item added to inventory.");
    }

    public void removeItem(String item) {
        if (items.containsKey(item)) {
            items.remove(item);
            System.out.println("Item removed from inventory.");
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    public void viewItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory Items:");
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}

// Staff Class
class Staff {
    private String name;
    private String role;

    public Staff(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff Name: " + name + ", Role: " + role;
    }
}
