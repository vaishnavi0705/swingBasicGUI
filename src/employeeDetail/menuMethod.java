package employeeDetail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuMethod implements ActionListener{
    static JFrame frame = new JFrame();
    static JMenu file,view, exit;
    static JMenuItem create, read, update, delete, viewAll, exitFrame;
    menuMethod(){
        frame.setTitle("Employee Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar  menu = new JMenuBar();

        file = new JMenu("File");
        view = new JMenu("Edit");
        exit = new JMenu("Quit");

        create = new JMenuItem("Create Entry"); create.addActionListener(this);
        read = new JMenuItem("Search Entry"); read.addActionListener(this);
        update = new JMenuItem("Update Entry"); update.addActionListener(this);
        delete = new JMenuItem("Delete Entry"); delete.addActionListener(this);

        viewAll = new JMenuItem("View all Records"); viewAll.addActionListener(this);

        exitFrame = new JMenuItem("Exit Application");
        exitFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                windowClosing(ev);
            }
        });

        file.add(create);
        file.add(read);
        file.add(update);
        file.add(delete);

        view.add(viewAll);

        exit.add(exitFrame);

        menu.add(file);
        menu.add(view);
        menu.add(exit);

        frame.setJMenuBar(menu);
        frame.setSize(600,600);
        frame.setVisible(true);

    }

    public void windowClosing(ActionEvent e) {
        int a = JOptionPane.showConfirmDialog(frame, "Are you sure?");
        if (a == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == create ) {
                frame.dispose();
                creationOfEmployeeDetail cc = new creationOfEmployeeDetail();
            } else if (e.getSource() == read) {
                frame.dispose();
                readingOfEmployeeDetails rr = new readingOfEmployeeDetails();
            } else if (e.getSource() == update) {
                frame.dispose();
                updationOfEmployeeDetail uu = new updationOfEmployeeDetail();
            } else if (e.getSource() == delete) {
                frame.dispose();
                searchDelete sd = new searchDelete();
            }else if (e.getSource() == viewAll){
                frame.dispose();
                viewAllRecords vv = new viewAllRecords();
            }
    }
}
