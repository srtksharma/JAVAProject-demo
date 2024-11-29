import java.util.*;

class HospitalManagementSystem {
    private static Map<String, Patient> patients = new HashMap<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static Map<String, Staff> staffMembers = new HashMap<>();
    private static int patientIdCounter = 1;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. View Patient Records");
            System.out.println("4. Manage Billing");
            System.out.println("5. Manage Staff");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    scheduleAppointment(scanner);
                    break;
                case 3:
                    viewPatientRecords(scanner);
                    break;
                case 4:
                    manageBilling(scanner);
                    break;
                case 5:
                    manageStaff(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        Patient patient = new Patient("P" + patientIdCounter++, name);
        patients.put(patient.getId(), patient);
        System.out.println("Patient registered: " + patient);
    }
    private static void scheduleAppointment(Scanner scanner) {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        if (!patients.containsKey(patientId)) {
            System.out.println("Patient not found!");
            return;
        }
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        Appointment appointment = new Appointment(patientId, date);
        appointments.add(appointment);
        System.out.println("Appointment scheduled: " + appointment);
    }

    private static void viewPatientRecords(Scanner scanner) {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = patients.get(patientId);
        if (patient != null) {
            System.out.println("Patient Records: " + patient);
            System.out.println("Appointments: " + getAppointmentsForPatient(patientId));
        } else {
            System.out.println("Patient not found!");
        }
    }
    private static void manageBilling(Scanner scanner) {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        if (!patients.containsKey(patientId)) {
            System.out.println("Patient not found!");
            return;
        }
        System.out.print("Enter amount for billing: $");
        double amount = scanner.nextDouble();
        System.out.println("Billing recorded for patient " + patientId + ": $" + amount);
    }

    private static void manageStaff(Scanner scanner) {
        System.out.print("Enter staff member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter staff position: ");
        String position = scanner.nextLine();
        Staff staff = new Staff(name, position);
        staffMembers.put(staff.getId(), staff);
        System.out.println("Staff registered: " + staff);
    }

    private static List<Appointment> getAppointmentsForPatient(String patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }
}

class Patient {
    private String id;
    private String name;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}
class Appointment {
    private String patientId;
    private String date;

    public Appointment(String patientId, String date) {
        this.patientId = patientId;
        this.date = date;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "Appointment for Patient ID: " + patientId + " on " + date;
    }
}

class Staff {
    private static int staffIdCounter = 1;
    private String id;
    private String name;
    private String position;
    public Staff(String name, String position) {
        this.id = "S" + staffIdCounter++;
        this.name = name;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Staff ID: " + id + ", Name: " + name + ", Position: " + position;
    }
}









            
