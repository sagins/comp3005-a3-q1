import java.sql.*;
import java.util.Scanner;

public class PostgreSQLJDBCConnection {

    // JDBC URL, username, and password of PostgreSQL server
    private static final String url = "jdbc:postgresql://localhost:5433/a3";
    private static final String user = "postgres";
    private static final String password = "student";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // Display the menu
            System.out.println("\nChoose an operation:");
            System.out.println("1. Get All Students");
            System.out.println("2. Add New Student");
            System.out.println("3. Update Student Email");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // get the next line character

            
            switch (choice) {
                case 1:
                    getAllStudents();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    updateStudentEmail();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        }
    }

    public static void getAllStudents() {
        // set up the connection to the database
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT student_id, first_name, last_name, email, enrollment_date FROM students")) {

            System.out.println("ID\tFirst Name\tLast Name\tEmail\t\t\tEnrollment Date");
            // loop through each row in the result set
            while (rs.next()) {
                System.out.println(rs.getInt("student_id") + "\t" +
                        rs.getString("first_name") + "\t\t" +
                        rs.getString("last_name") + "\t\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("enrollment_date"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting students.");
            e.printStackTrace();
        }
    }

    public static void addStudent() {
        // get the student information from the user to add to the database
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter enrollment date (YYYY-MM-DD): ");
        String enrollmentDate = scanner.nextLine();

        // query to insert the student into the database
        String SQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";

        // set up the connection to the database
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // set the parameters for the query
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollmentDate));

            // execute the query
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student added successfully.");
            } else {
                System.out.println("A student could not be added.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding student.");
            e.printStackTrace();
        }
    }

    public static void updateStudentEmail() {
        // get the student ID and new email from the user
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        // query to update the student email
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";

        // set up the connection to the database
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // set the parameters for the query
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, id);

            // execute the query
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student email updated successfully.");
            } else {
                System.out.println("Could not update student email.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student email.");
            e.printStackTrace();
        }
    }

    public static void deleteStudent() {
        // get the student ID from the user to delete
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        String SQL = "DELETE FROM students WHERE student_id = ?";

        // set up the connection to the database 
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            // set the parameters for the query
            pstmt.setInt(1, id);

            // execute the query
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Could not delete student.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student.");
            e.printStackTrace();
        }
    }
}
