package employeeDetail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class updationOfEmployeeDetail implements ActionListener{
        static JFrame frame, f, f1;
        static JTextField t1 , t2, t3, t4, t5, txt;
        static JButton b, b1, b2, b3, b4, b5, bt;
        static JPanel p, panel;
        static JLabel l1 , l2, l3, l4, l5, l;
        static boolean Initialised = false;
        static int ch = 0;
        updationOfEmployeeDetail(){

            if(!Initialised) {
                initializeComponent();
                Initialised = true;
            }
            frame.setVisible(true);
        }
        public void initializeComponent(){

            frame = new JFrame("Employee Entry");

            p = new JPanel();
            p.setVisible(true);
            p.setLayout(new GridBagLayout());

            GridBagConstraints gbl = new GridBagConstraints();
            gbl.fill = GridBagConstraints.BOTH;
            gbl.insets = new Insets(10, 10, 10, 10);

            l1 = new JLabel("UPDATE DETAILS");

            gbl.gridx = 0;
            gbl.gridy = 0;
            gbl.gridwidth = 4;
            gbl.anchor = GridBagConstraints.CENTER;
            p.add(l1, gbl);

            b = new JButton();
            b.setText("Go back to home page");
            b.setBounds(150, 150, 150, 150);
            b.setForeground(Color.BLUE);
            b.addActionListener(this);

            b1 = new JButton();
            b1.setText("Update Designation");
            b1.setBounds(150, 150, 150, 150);
            b1.setForeground(Color.BLUE);
            b1.addActionListener(this);

            b2 = new JButton();
            b2.setText("Update Department");
            b2.setBounds(150, 150, 150, 150);
            b2.setForeground(Color.BLUE);
            b2.addActionListener(this);

            b3 = new JButton();
            b3.setText("Update Contact");
            b3.setBounds(150, 150, 150, 150);
            b3.setForeground(Color.BLUE);
            b3.addActionListener(this);

            gbl.gridx = 0;
            gbl.gridy = 5;
            gbl.gridwidth = 2;
            gbl.anchor = GridBagConstraints.CENTER;

            p.setBackground(Color.WHITE);

            gbl.gridwidth = 1;
            gbl.gridy = 1;
            p.add(b1,gbl);
            gbl.gridy = 2;
            p.add(b2,gbl);
            gbl.gridy = 3;
            p.add(b3,gbl);
            gbl.gridy = 4;
            p.add(b, gbl);

            frame.add(p);
            frame.setBackground(Color.WHITE);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.setVisible(true);
            frame.setSize(750, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void updateEntry(){
            try{
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/javaswing","postgres","akash1234"
                );
                PreparedStatement preparedStatement = null;

                String query = null;

                if (ch == 1){
                    query = "update employee set emp_design = ? where emp_id = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, t3.getText());
                } else if (ch == 2) {
                    query = "update employee set emp_dept = ? where emp_id = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, t4.getText());
                } else if (ch == 3) {
                    query = "update employee set emp_contact = ? where emp_id = ?";
                    preparedStatement = connection.prepareStatement(query);

                    long contact = Long.parseLong(t5.getText());
                    preparedStatement.setLong(1, contact);

                }

                preparedStatement.setString(2,t1.getText());

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null,"Data have been successfully updated.");
                } else {
                    JOptionPane.showMessageDialog(null, "Update failed.");
                }

                preparedStatement.close();
                connection.close();

                new menuMethod();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                System.out.println(e);
            }
        }

    public void searchUpdate() {
            f = new JFrame("Employee Details");
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
            panel.add(l, gbl);
            gbl.gridx = 1;
            panel.add(txt, gbl);

            gbl.gridx = 0;
            gbl.gridy = 5;
            gbl.gridwidth = 2;
            gbl.anchor = GridBagConstraints.CENTER;

            b4 = new JButton("Search");
            b4.setVisible(true);
            b4.setBounds(150, 100, 100, 50);
            b4.addActionListener(this);
            b4.setForeground(Color.BLUE);
            panel.add(b4, gbl);

            gbl.gridx = 2;

            bt = new JButton("Go back to home page.");
            bt.setVisible(true);
            bt.setForeground(Color.BLUE);
            bt.addActionListener(this);
            bt.setBounds(200, 100, 100, 50);
            panel.add(bt, gbl);

            f.add(panel, BorderLayout.CENTER);
            f.setBackground(Color.WHITE);
            f.setLayout(new FlowLayout(FlowLayout.CENTER));
            f.setVisible(true);
            f.setSize(750, 700);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }
        public void createUpdate(){

            f1 = new JFrame("Employee Details");
            panel = new JPanel();
            panel.setVisible(true);
            panel.setLayout(new GridBagLayout());
            panel.setBackground(Color.WHITE);

            GridBagConstraints gbl = new GridBagConstraints();
            gbl.fill = GridBagConstraints.HORIZONTAL;
            gbl.insets = new Insets(10, 10, 10, 10);

            t1 = new JTextField(30); t2 = new JTextField(30); t3 = new JTextField(30);
            t4 = new JTextField(30); t5 = new JTextField(30);

            l1 = new JLabel("ID : "); l2 = new JLabel("Name : "); l3 = new JLabel("Designation : ");
            l4 = new JLabel("Department :"); l5 = new JLabel("Contact : ");

            t1.setEditable(false); t1.setEnabled(false);

            gbl.gridx = 0; gbl.gridy = 0; panel.add(l1, gbl);
            gbl.gridx = 1; panel.add(t1, gbl);

            gbl.gridx = 0; gbl.gridy = 1; panel.add(l2, gbl);
            gbl.gridx = 1;panel.add(t2, gbl);

            gbl.gridx = 0; gbl.gridy = 2; panel.add(l3, gbl);
            gbl.gridx = 1; panel.add(t3, gbl);

            gbl.gridx = 0; gbl.gridy = 3; panel.add(l4, gbl);
            gbl.gridx = 1; panel.add(t4, gbl);

            gbl.gridx = 0; gbl.gridy = 4; panel.add(l5, gbl);
            gbl.gridx = 1; panel.add(t5, gbl);

            if (ch == 1) {
                t3.setEnabled(true);
                t4.setEnabled(false);
                t5.setEnabled(false);
            } else if (ch == 2) {
                t3.setEnabled(false);
                t4.setEnabled(true);
                t5.setEnabled(false);
            } else if (ch == 3) {
                t3.setEnabled(false);
                t4.setEnabled(false);
                t5.setEnabled(true);
            }

            gbl.gridx = 0;
            gbl.gridy = 5;
            gbl.gridwidth = 2;
            gbl.anchor = GridBagConstraints.CENTER;

            b5 = new JButton();
            b5.setText("Submit");
            b5.setBounds(150, 150, 150, 150);
            b5.setForeground(Color.BLUE);
            b5.addActionListener(this);

            panel.setBackground(Color.WHITE);
            panel.add(b5, gbl);

            f1.add(panel);
            f1.setBackground(Color.WHITE);
            f1.setLayout(new FlowLayout(FlowLayout.CENTER));
            f1.setVisible(true);
            f1.setSize(750, 700);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/javaswing", "postgres", "akash1234"
                );

                String query = "SELECT * FROM employee WHERE emp_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, txt.getText());

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    t1.setText(resultSet.getString("emp_id"));
                    t2.setText(resultSet.getString("emp_name"));
                    t3.setText(resultSet.getString("emp_design"));
                    t4.setText(resultSet.getString("emp_dept"));
                    t5.setText(resultSet.getString("emp_contact"));
                } else {
                    JOptionPane.showMessageDialog(null, "Employee with ID " +
                            txt.getText() + " not found.");
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b) {
                frame.dispose();
                new menuMethod();
            }else if (e.getSource() == b1){
                ch = 1;
                frame.dispose();
                searchUpdate();
            } else if (e.getSource() == b2) {
                ch = 2;
                frame.dispose();
                searchUpdate();
            } else if (e.getSource() == b3) {
                ch = 3;
                frame.dispose();
                searchUpdate();
            }
            else if (e.getSource() == bt){
                f.dispose();
                new menuMethod();
            }else if (e.getSource() == b4){
                f.dispose();
                createUpdate();
            }else if (e.getSource() == b5){
                updateEntry();
                f1.dispose();
            }
        }
}