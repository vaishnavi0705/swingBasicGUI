package employeeDetail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class creationOfEmployeeDetail extends readingOfEmployeeDetails{
    static JFrame frame;
    static JTextField t1 , t2, t3, t4, t5;
    static JButton b;
    static JPanel panel;
    static JLabel l1 , l2, l3, l4, l5;
    static boolean Initialised = false;
    creationOfEmployeeDetail(){

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
        GridBagConstraints gbl = new GridBagConstraints();
        gbl.fill = GridBagConstraints.BOTH;
        gbl.insets = new Insets(10, 10, 10, 10);

        t1 = new JTextField(30);
        t2 = new JTextField(30);
        t3 = new JTextField(30);
        t4 = new JTextField(30);
        t5 = new JTextField(30);

        l1 = new JLabel("Enter ID here: ");
        l2 = new JLabel("Enter name here: ");
        l3 = new JLabel("Enter designation here: ");
        l4 = new JLabel("Enter department here: ");
        l5 = new JLabel("Enter contact here: ");

        gbl.gridx = 0;
        gbl.gridy = 0;
        panel.add(l1, gbl);
        gbl.gridx = 1;
        panel.add(t1, gbl);

        gbl.gridx = 0;
        gbl.gridy = 1;
        panel.add(l2, gbl);
        gbl.gridx = 1;
        panel.add(t2, gbl);

        gbl.gridx = 0;
        gbl.gridy = 2;
        panel.add(l3, gbl);
        gbl.gridx = 1;
        panel.add(t3, gbl);

        gbl.gridx = 0;
        gbl.gridy = 3;
        panel.add(l4, gbl);
        gbl.gridx = 1;
        panel.add(t4, gbl);

        gbl.gridx = 0;
        gbl.gridy = 4;
        panel.add(l5, gbl);
        gbl.gridx = 1;
        panel.add(t5, gbl);

        b = new JButton();
        b.setText("Submit");
        b.setBounds(150, 150, 150, 150);
        b.setForeground(Color.BLUE);
        b.addActionListener(this);

        gbl.gridx = 0;
        gbl.gridy = 5;
        gbl.gridwidth = 2;
        gbl.anchor = GridBagConstraints.CENTER;

        panel.setBackground(Color.WHITE);
        panel.add(b, gbl);

        frame.add(panel);
        frame.setBackground(Color.WHITE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setVisible(true);
        frame.setSize(750, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void addEntry(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javaswing","postgres","akash1234"
            );

            String query = "insert into employee VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, t1.getText());
            preparedStatement.setString(2, t2.getText());
            preparedStatement.setString(3, t3.getText());
            preparedStatement.setString(4, t4.getText());

            long contact = Long.parseLong(t5.getText());
            preparedStatement.setLong(5, contact);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " "
                        + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
            }

            preparedStatement.close();

            t1.setText(""); t2.setText(""); t3.setText(""); t4.setText(""); t5.setText("");

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        addEntry();
        JOptionPane.showMessageDialog(frame, "New data entry has been successfully recorded.",
                "Message Confirmation", JOptionPane.PLAIN_MESSAGE);
        menuMethod mm = new menuMethod();
    }
}
