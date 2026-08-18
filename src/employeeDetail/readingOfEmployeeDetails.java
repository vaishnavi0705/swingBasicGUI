package employeeDetail;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
public class readingOfEmployeeDetails extends WindowAdapter implements ActionListener {
    static JFrame frame, f;
    static JPanel panel, p;
    static JLabel l;
    static JTextField txt;
    static JTable t;
    static JButton b,bt,b1, b2, b3;
    static boolean Initialised = false;
    readingOfEmployeeDetails(){
        if(!Initialised) {
            initializeComponents();
            Initialised = true;
        }
        frame.setVisible(true);
    }
    public void initializeComponents(){
        frame = new JFrame("Employee Details");

        panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbl = new GridBagConstraints();
        gbl.fill = GridBagConstraints.HORIZONTAL;
        gbl.insets = new Insets(10, 10, 10, 10);

        txt = new JTextField(20);
        l = new JLabel("Search Using ID");

        gbl.gridx = 0;
        gbl.gridy = 1;
        panel.add(l,gbl);
        gbl.gridx = 1;
        panel.add(txt,gbl);

        gbl.gridx = 0;
        gbl.gridy = 5;
        gbl.gridwidth = 2;
        gbl.anchor = GridBagConstraints.CENTER;

        b = new JButton("Search");
        b.setVisible(true);
        b.setBounds(150,100,100,50);
        b.addActionListener(this);
        b.setForeground(Color.BLUE);
        panel.add(b,gbl);

        gbl.gridx = 2;

        bt = new JButton("Go back to home page.");
        bt.setVisible(true);
        bt.setForeground(Color.BLUE);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menuMethod mm = new menuMethod();
            }
        });
        bt.setBounds(200,100,100,50);
        panel.add(bt,gbl);

        frame.add(panel, BorderLayout.CENTER);
        frame.setBackground(Color.WHITE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setVisible(true);
        frame.setSize(750, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void displayEntry(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javaswing","postgres","akash1234"
            );

            String query = "select * from employee where emp_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,txt.getText());
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

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

                System.out.println("ID --> " + id);
                System.out.println("Name --> " + name);
                System.out.println("Designation --> " + design);
                System.out.println("Department --> " + dept);
                System.out.println("Contact --> " + contact);

                table.addRow(new Object[]{id,name,design,dept,contact});
            }

            resultSet.close();
            preparedStatement.close();

            t = new JTable(table);
            JScrollPane sp = new JScrollPane(t);

            f = new JFrame("Employee Details");

            p = new JPanel();
            p.setLayout(new FlowLayout());

            b1 = new JButton("Go back to home page");
            b1.setVisible(true);
            b1.setBounds(150,150,150,150);
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new menuMethod();
                }
            });
            b1.setForeground(Color.BLUE);

            b2 = new JButton("Update the record");
            b2.setVisible(true);
            b2.setBounds(150,150,150,150);
            b2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    f.dispose();
                    new updateSearched();
                }
            });
            b2.setForeground(Color.BLUE);

            b3 = new JButton("Delete the record");
            b3.setVisible(true);
            b3.setBounds(150,150,150,150);
            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    int selectedRow = t.getSelectedRow();
                    if (selectedRow != -1) {
                        String empId = t.getValueAt(selectedRow, 0).toString();
                        int r = JOptionPane.showConfirmDialog(f, "Do you want to delete it?",
                                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        if (r == JOptionPane.YES_OPTION) {
                            deletionOfEmployeeDetail(empId);
                        }else{
                            displayEntry();
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please select a record to delete.");
                    }
                }
            });
            b3.setForeground(Color.BLUE);

            p.add(b1);
            p.add(b2);
            p.add(b3);

            f.setVisible(true);
            f.setSize(750,750);
            f.add(sp, BorderLayout.CENTER);
            f.add(p, BorderLayout.SOUTH);

            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            connection.close();

            txt.setText("");
        }
        catch (Exception e){
            System.out.println(e);

        }
    }

    public void deletionOfEmployeeDetail(String emp_id){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javaswing", "postgres", "akash1234"
            );

            String query = "delete from employee where emp_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, emp_id);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (txt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter ID");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else{
            frame.dispose();
            displayEntry();
        }
    }
}