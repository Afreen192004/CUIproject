package Java_Development;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ===============================================
// 1. File Handling Utility (FileHandler) - Unchanged
// ===============================================
class FileHandler {

    public static void writeToFile(String filename, List<String> lines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.println(line);
            }
            // System.out.println("Data saved to " + filename); // Suppress frequent save messages
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            // System.out.println("File not found or error reading " + filename + ".");
        }
        return lines;
    }
}

// ===============================================
// 2. Base Class (Person) - Unchanged
// ===============================================
abstract class Person {
    private String id;
    private String name;
    private int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public abstract String toFileString();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

// ===============================================
// 3. Derived Class (Patient) - Unchanged
// ===============================================
class Patient extends Person {
    private String ailment;
    private static final String FILENAME = "patients.txt";

    public Patient(String id, String name, int age, String ailment) {
        super(id, name, age);
        this.ailment = ailment;
    }

    public static Patient fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 4) {
            try { return new Patient(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]); }
            catch (NumberFormatException e) { System.err.println("Error parsing patient age: " + line); }
        }
        return null;
    }

    @Override
    public String toFileString() {
        return getId() + "|" + getName() + "|" + getAge() + "|" + ailment;
    }

    @Override
    public String toString() {
        return "Patient -> " + super.toString() + ", Ailment: " + ailment;
    }

    public static void savePatients(List<Patient> patients) {
        List<String> lines = new ArrayList<>();
        for (Patient p : patients) { lines.add(p.toFileString()); }
        FileHandler.writeToFile(FILENAME, lines);
    }

    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        List<String> lines = FileHandler.readFromFile(FILENAME);
        for (String line : lines) {
            Patient p = fromFileString(line);
            if (p != null) { patients.add(p); }
        }
        return patients;
    }
}

// ===============================================
// 4. Derived Class (Doctor) - Unchanged
// ===============================================
class Doctor extends Person {
    private String specialization;
    private static final String FILENAME = "doctors.txt";

    public Doctor(String id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
    }

    public static Doctor fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 4) {
             try { return new Doctor(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]); }
             catch (NumberFormatException e) { System.err.println("Error parsing doctor age: " + line); }
        }
        return null;
    }

    @Override
    public String toFileString() {
        return getId() + "|" + getName() + "|" + getAge() + "|" + specialization;
    }

    @Override
    public String toString() {
        return "Doctor -> " + super.toString() + ", Specialization: " + specialization;
    }

    public static void saveDoctors(List<Doctor> doctors) {
        List<String> lines = new ArrayList<>();
        for (Doctor d : doctors) { lines.add(d.toFileString()); }
        FileHandler.writeToFile(FILENAME, lines);
    }

    public static List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        List<String> lines = FileHandler.readFromFile(FILENAME);
        for (String line : lines) {
            Doctor d = fromFileString(line);
            if (d != null) { doctors.add(d); }
        }
        return doctors;
    }
}

// ===============================================
// 5. Appointments Class (Appointment) - Unchanged
// ===============================================
class Appointment {
    private String patientId;
    private String doctorId;
    private String date;
    private static final String FILENAME = "appointments.txt";

    public Appointment(String patientId, String doctorId, String date) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public String toFileString() {
        return patientId + "|" + doctorId + "|" + date;
    }

    public static Appointment fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 3) { return new Appointment(parts[0], parts[1], parts[2]); }
        return null;
    }

    @Override
    public String toString() {
        return "Appointment [Patient ID: " + patientId + ", Doctor ID: " + doctorId + ", Date: " + date + "]";
    }

    public static void saveAppointments(List<Appointment> appointments) {
        List<String> lines = new ArrayList<>();
        for (Appointment a : appointments) { lines.add(a.toFileString()); }
        FileHandler.writeToFile(FILENAME, lines);
    }

    public static List<Appointment> loadAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        List<String> lines = FileHandler.readFromFile(FILENAME);
        for (String line : lines) {
            Appointment a = fromFileString(line);
            if (a != null) { appointments.add(a); }
        }
        return appointments;
    }
}

// ===============================================
// 6. Billing Class (Bill) - Unchanged
// ===============================================
class Bill {
    private String billId;
    private String patientId;
    private double amount;
    private String status; // Paid or Pending
    private static final String FILENAME = "bills.txt";

    public Bill(String billId, String patientId, double amount, String status) {
        this.billId = billId;
        this.patientId = patientId;
        this.amount = amount;
        this.status = status;
    }

    public String toFileString() {
        return billId + "|" + patientId + "|" + amount + "|" + status;
    }

    public static Bill fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 4) {
            try { return new Bill(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]); }
            catch (NumberFormatException e) { System.err.println("Error parsing bill amount: " + line); }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Bill [ID: " + billId + ", Patient ID: " + patientId + ", Amount: $" + String.format("%.2f", amount) + ", Status: " + status + "]";
    }

    public static void saveBills(List<Bill> bills) {
        List<String> lines = new ArrayList<>();
        for (Bill b : bills) { lines.add(b.toFileString()); }
        FileHandler.writeToFile(FILENAME, lines);
    }

    public static List<Bill> loadBills() {
        List<Bill> bills = new ArrayList<>();
        List<String> lines = FileHandler.readFromFile(FILENAME);
        for (String line : lines) {
            Bill b = fromFileString(line);
            if (b != null) { bills.add(b); }
        }
        return bills;
    }
}

// ===============================================
// 7. Room Assignment Class (RoomAssignment) - Unchanged
// ===============================================
class RoomAssignment {
    private String patientId;
    private String roomNumber;
    private String dateAssigned;
    private static final String FILENAME = "rooms.txt";

    public RoomAssignment(String patientId, String roomNumber, String dateAssigned) {
        this.patientId = patientId;
        this.roomNumber = roomNumber;
        this.dateAssigned = dateAssigned;
    }

    public String toFileString() {
        return patientId + "|" + roomNumber + "|" + dateAssigned;
    }

    public static RoomAssignment fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 3) { return new RoomAssignment(parts[0], parts[1], parts[2]); }
        return null;
    }

    @Override
    public String toString() {
        return "Room Assignment [Patient ID: " + patientId + ", Room No: " + roomNumber + ", Assigned Date: " + dateAssigned + "]";
    }

    public static void saveRoomAssignments(List<RoomAssignment> assignments) {
        List<String> lines = new ArrayList<>();
        for (RoomAssignment ra : assignments) { lines.add(ra.toFileString()); }
        FileHandler.writeToFile(FILENAME, lines);
    }

    public static List<RoomAssignment> loadRoomAssignments() {
        List<RoomAssignment> assignments = new ArrayList<>();
        List<String> lines = FileHandler.readFromFile(FILENAME);
        for (String line : lines) {
            RoomAssignment ra = fromFileString(line);
            if (ra != null) { assignments.add(ra); }
        }
        return assignments;
    }
}

// ===============================================
// 8. Main Logic and Demonstration (HospitalManagementSystem) - UPDATED
// ===============================================
public class HospitalManagementSystem {

    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private List<Bill> bills;
    private List<RoomAssignment> roomAssignments;
    private Scanner scanner;

    public HospitalManagementSystem() {
        this.patients = Patient.loadPatients();
        this.doctors = Doctor.loadDoctors();
        this.appointments = Appointment.loadAppointments();
        this.bills = Bill.loadBills();
        this.roomAssignments = RoomAssignment.loadRoomAssignments();
        this.scanner = new Scanner(System.in);
    }

    // --- Management Methods ---
    public void addPatient(Patient patient) {
        this.patients.add(patient); Patient.savePatients(patients);
        System.out.println("Patient added: " + patient.getName());
    }
    public void addDoctor(Doctor doctor) {
        this.doctors.add(doctor); Doctor.saveDoctors(doctors);
        System.out.println("Doctor added: " + doctor.getName());
    }
    public void scheduleAppointment(String patientId, String doctorId, String date) {
        Appointment appt = new Appointment(patientId, doctorId, date);
        this.appointments.add(appt); Appointment.saveAppointments(appointments);
        System.out.println("Appointment scheduled: " + appt);
    }
    public void createBill(String billId, String patientId, double amount, String status) {
        Bill bill = new Bill(billId, patientId, amount, status);
        this.bills.add(bill); Bill.saveBills(bills);
        System.out.println("Bill created: " + bill);
    }
    public void assignRoom(String patientId, String roomNumber, String dateAssigned) {
        RoomAssignment assignment = new RoomAssignment(patientId, roomNumber, dateAssigned);
        this.roomAssignments.add(assignment); RoomAssignment.saveRoomAssignments(roomAssignments);
        System.out.println("Room assigned: " + assignment);
    }

    public void displayAll() {
        System.out.println("\n==================================");
        System.out.println("  HOSPITAL RECORDS SUMMARY");
        System.out.println("==================================");
        System.out.println("\n--- Doctors (" + doctors.size() + ") ---");
        doctors.forEach(System.out::println);
        System.out.println("\n--- Patients (" + patients.size() + ") ---");
        patients.forEach(System.out::println);
        System.out.println("\n--- Appointments (" + appointments.size() + ") ---");
        appointments.forEach(System.out::println);
        System.out.println("\n--- Bills (" + bills.size() + ") ---");
        bills.forEach(System.out::println);
        System.out.println("\n--- Room Assignments (" + roomAssignments.size() + ") ---");
        roomAssignments.forEach(System.out::println);
        System.out.println("==================================");
    }

    // --- User Input Handlers ---
    private void handleAddDoctor() {
        System.out.print("Enter Doctor ID (e.g., D002): ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        addDoctor(new Doctor(id, name, age, specialization));
    }

    private void handleAddPatient() {
        System.out.print("Enter Patient ID (e.g., P003): ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Ailment: ");
        String ailment = scanner.nextLine();
        addPatient(new Patient(id, name, age, ailment));
    }
    
    // ... (Similar handlers can be created for Appointment, Bill, and RoomAssignment)

    // --- Main Menu ---
    private void showMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n+--------------------------------+");
            System.out.println("|      HOSPITAL MANAGER MENU     |");
            System.out.println("+--------------------------------+");
            System.out.println("| 1. Add New Doctor              |");
            System.out.println("| 2. Add New Patient             |");
            System.out.println("| 3. Display All Records         |");
            System.out.println("| 4. Schedule Appointment        |");
            System.out.println("| 5. Create Bill                 |");
            System.out.println("| 6. Assign Room                 |");
            System.out.println("| 0. Exit and Save               |");
            System.out.println("+--------------------------------+");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: handleAddDoctor(); break;
                    case 2: handleAddPatient(); break;
                    case 3: displayAll(); break;
                    case 4: /* Simple demo implementation for appointment: */
                        scheduleAppointment("P999", "D999", "2026-01-01");
                        break;
                    case 5: /* Simple demo implementation for bill: */
                        createBill("B999", "P999", 50.00, "Pending");
                        break;
                    case 6: /* Simple demo implementation for room: */
                        assignRoom("P999", "Z999", "2026-01-01");
                        break;
                    case 0:
                        System.out.println("Exiting system. All data saved to respective text files.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }
        }
    }

    // --- Login System ---
    private boolean authenticate() {
        final String USER = "admin";
        final String PASS = "123";
        int attempts = 3;

        System.out.println("\n==============================================");
        System.out.println("  WELCOME TO HOSPITAL MANAGEMENT SYSTEM LOGIN");
        System.out.println("==============================================");

        while (attempts > 0) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (username.equals(USER) && password.equals(PASS)) {
                System.out.println("\nAuthentication Successful! Loading data...");
                return true;
            } else {
                attempts--;
                System.out.println("Invalid credentials. Attempts left: " + attempts);
            }
        }
        System.out.println("Too many failed attempts. Shutting down.");
        return false;
    }

    public static void main(String[] args) {
        HospitalManagementSystem manager = new HospitalManagementSystem();

        if (manager.authenticate()) {
            manager.showMenu();
        }

        // Close the scanner when the application exits
        manager.scanner.close();
    }
}