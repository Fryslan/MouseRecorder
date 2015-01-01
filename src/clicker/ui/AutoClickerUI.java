package clicker.ui;

import clicker.AutoClicker;
import clicker.domains.Coordinate;
import clicker.domains.MouseAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Piet Jetse
 * Date: 1-1-2015
 * Time: 15:33
 */

public class AutoClickerUI extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void initialize() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AutoClickerUI frame = new AutoClickerUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AutoClickerUI() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 324, 182);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final DefaultListModel actionsModel = new DefaultListModel();

        JLabel lblAction = new JLabel("Action : ");
        lblAction.setBounds(10, 11, 46, 14);
        contentPane.add(lblAction);

        JLabel lblX = new JLabel("X : ");
        lblX.setBounds(50, 36, 22, 14);
        contentPane.add(lblX);

        JLabel lblY = new JLabel("Y : ");
        lblY.setBounds(121, 36, 22, 14);
        contentPane.add(lblY);

        JLabel lblSleep = new JLabel("Sleep : ");
        lblSleep.setBounds(50, 64, 39, 14);
        contentPane.add(lblSleep);

        final JComboBox comboBox = new JComboBox();
        comboBox.setBounds(66, 8, 115, 20);
        for (MouseAction action : MouseAction.values()) {
            comboBox.addItem(action);
        }
        contentPane.add(comboBox);

        textField = new JTextField();
        textField.setBounds(72, 33, 39, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(142, 33, 39, 20);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setBounds(95, 61, 86, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JList list = new JList(actionsModel);
        list.setBounds(191, 11, 117, 135);
        contentPane.add(list);

        JButton btnNewButton = new JButton("Add Action");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                actionsModel.addElement(comboBox.getSelectedItem() + " - " + textField.getText() + "," + textField_1.getText() + " - " + textField_2.getText());
            }
        });
        btnNewButton.setBounds(10, 89, 171, 23);
        contentPane.add(btnNewButton);

        JButton btnStartScript = new JButton("Start Script");
        btnStartScript.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                for (int i = 0; i < actionsModel.size(); i++) {
                    int x = Integer.parseInt(actionsModel.getElementAt(i).toString().split(" - ")[1].split(",")[0]);
                    int y = Integer.parseInt(actionsModel.getElementAt(i).toString().split(" - ")[1].split(",")[1]);
                    int sleep = Integer.parseInt(actionsModel.getElementAt(i).toString().split(" - ")[2]);
                    String action = actionsModel.getElementAt(i).toString().split(" - ")[0].toUpperCase();

                    System.out.printf("%s - %s - %s - %s \n",x,y,action,sleep);

                    switch (action) {
                        case "LEFT":
                            AutoClicker.coordinates.add(new Coordinate(x,y,MouseAction.LEFT,sleep));
                            break;
                        case "RIGHT":
                            AutoClicker.coordinates.add(new Coordinate(x,y,MouseAction.RIGHT,sleep));
                            break;
                        case "MOVE":
                            AutoClicker.coordinates.add(new Coordinate(x,y,MouseAction.MOVE,sleep));
                            break;
                    }

                }
                AutoClicker.loaded = true;
                dispose();
            }
        });
        btnStartScript.setBounds(10, 123, 171, 23);
        contentPane.add(btnStartScript);
    }
}
