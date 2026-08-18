package employeeDetail;

import javax.swing.*;
import java.sql.*;

public class deletionOfEmployeeDetail{
    static JFrame frame;
    static JTextField t1;
    static boolean Initialised = false;
    deletionOfEmployeeDetail(String emp_id){
        t1 = new JTextField(emp_id);
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javaswing", "postgres", "akash1234"
            );

            String query = "delete from employee where emp_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, t1.getText());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record has been successfully deleted.");
            } else {
                JOptionPane.showMessageDialog(null, "Deletion failed.");
            }

            preparedStatement.close();
            connection.close();

            new menuMethod();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.out.println(e);
        }
    }
}