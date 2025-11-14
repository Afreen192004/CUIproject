Overview
This Hospital Management System is a Java-based, console-driven application designed to manage patient, doctor, appointment, billing, and room assignment records for a hospital. It uses local text files for persistent storage and supports essential CRUD (Create, Read, Update, Delete) operations via a user-friendly command-line menu.​

Features
Add and manage patient records (Name, Age, Ailment)

Add and manage doctor records (Name, Age, Specialization)

Schedule appointments between patients and doctors

Generate and track medical bills (with status)

Assign rooms to patients

Display comprehensive summary of all records

Secure authentication for system access (login required)

All data stored in easy-to-read text files​

Installation
Clone this repository to your local machine.

Ensure you have Java installed (Java 8 or higher recommended).

Place HospitalManagementSystem.java in your working directory.

Compile the project:

text
javac HospitalManagementSystem.java
Run the system:

text
java HospitalManagementSystem
Usage
Log in with credentials (username: admin, password: 123 by default).

Use the menu to add doctors, add patients, view records, schedule appointments, generate bills, and assign rooms.

Data will be saved to .txt files in the application folder for persistence.

Modify or view these text files externally for backup or auditing.​

File Structure
HospitalManagementSystem.java — Main program

patients.txt — Stores patient records

doctors.txt — Stores doctor records

appointments.txt — Stores appointment data

bills.txt — Stores billing information

rooms.txt — Stores room assignments​

Contribution
Contributions are welcome! If you find bugs or wish to add features, please open an issue or submit a pull request.

License
Specify your license here (e.g., MIT, Apache-2.0).
