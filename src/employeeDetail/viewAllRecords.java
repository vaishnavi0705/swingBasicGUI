package employeeDetail;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
public class viewAllRecords extends WindowAdapter implements ActionListener {
    static JFrame f;
    static JTable t;
    static JButton b1;
    viewAllRecords(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javaswing","postgres","akash1234"
            );

            String query = "select * from employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            DefaultTableModel table = new DefaultTableModel();

            table.addColumn("Employee ID");
            table.addColumn("Name");
            table.addColumn("Designation");
            table.addColumn("Department");
            table.addColumn("Contact");

            while (resultSet.next()){
                String id = resultSet.getString("emp_id");
                String name = resultSet.getString("emp_name");
                String design = resultSet.getString("emp_design");
                String dept = resultSet.getString("emp_dept");
                long contact = resultSet.getLong("emp_contact");

                System.out.println("All data have been displayed.");

                table.addRow(new Object[]{id,name,design,dept,contact});

            }

            resultSet.close();
            statement.close();

            t = new JTable(table);
            JScrollPane sp = new JScrollPane(t);

            f = new JFrame("Employee Details");

            b1 = new JButton("Go back to home page");
            b1.setVisible(true);
            b1.setBounds(150,150,150,150);
            b1.addActionListener(this);
            b1.setForeground(Color.BLUE);

            f.setVisible(true);
            f.setSize(750,750);
            f.add(sp, BorderLayout.CENTER);
            f.add(b1, BorderLayout.AFTER_LAST_LINE);

            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            f.dispose();
            menuMethod mm = new menuMethod();
        }
    }
}