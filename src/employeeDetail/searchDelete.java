package employeeDetail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class searchDelete implements ActionListener{
    static JFrame frame;
    static JTextField txt;
    static JButton b1, bt;
    static JPanel panel;
    static JLabel l;
    static boolean Initialised = false;
    searchDelete(){

        if(!Initialised) {
            initializeComponent();
            Initialised = true;
        }
        frame.setVisible(true);
    }
    public void initializeComponent(){

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
        panel.add(l, gbl);
        gbl.gridx = 1;
        panel.add(txt, gbl);

        gbl.gridx = 0;
        gbl.gridy = 5;
        gbl.gridwidth = 2;
        gbl.anchor = GridBagConstraints.CENTER;

        b1 = new JButton("Search");
        b1.setVisible(true);
        b1.setBounds(150, 100, 100, 50);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = JOptionPane.showConfirmDialog(frame,"Do you want to delete it?","Confirm deletion",
                        JOptionPane.YES_NO_OPTION);
                if ( r == JOptionPane.YES_OPTION){
                    frame.dispose();
                    deleteEntry();
                } else if ( r == JOptionPane.NO_OPTION){
                    JOptionPane.showMessageDialog(null,"Deletion cancelled.");
                }
            }
        });
        b1.setForeground(Color.BLUE);
        panel.add(b1, gbl);

        gbl.gridx = 2;

        bt = new JButton("Go back to home page.");
        bt.setVisible(true);
        bt.setForeground(Color.BLUE);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new menuMethod();
            }
        });
        bt.setBounds(200, 100, 100, 50);
        panel.add(bt, gbl);

        frame.add(panel, BorderLayout.CENTER);
        frame.setBackground(Color.WHITE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setVisible(true);
        frame.setSize(750, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void deleteEntry(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javaswing","postgres","akash1234"
            );

            String query = "delete from employee where emp_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,txt.getText());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,"Record have been successfully deleted.");
            } else {
                JOptionPane.showMessageDialog(null, "Deletion failed.");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (txt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter ID");
        }
    }
}