
//awt
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
//swing
import javax.swing.*;
import javax.imageio.*;
//DataStructure
import java.util.HashMap;
import java.util.ArrayList;

class gui34 extends JPanel implements ActionListener {
    // layout manager
    GridBagLayout b = new GridBagLayout();
    GridBagLayout b2 = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    GridBagConstraints c2 = new GridBagConstraints();
    // frame
    JFrame frame, frame2;
    // Panels
    JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9;
    JScrollPane scrollPane, scrollPane2;
    // HashMap
    HashMap<JButton, JLabel> hm = new HashMap<>();
    ArrayList<String> history = new ArrayList<String>();// history
    // Counting task;
    int count = 0, tempCounter = 0, addedHistory = 0;
    int doneCount = 0;
    // ButtonsName
    JButton Add, Confirm, Taskbtn, tempB, History;
    // Label;
    JLabel l1, l2, tempL, rightLabel, leftLabel, oldTask, tempOldTask, historyLabel;
    // TextField
    JTextField Task;

    // Label and Button Position History
    int bx = 0;// constant
    int by = 0;// TaskButton position in grid
    int tx = 1;// constant
    int ty = 0;// label position in grid
    int oldTaskPosition = 0;

    public void changeHistroyValues() {
        c2.insets = new Insets(5, 5, 5, 5);
        c2.weightx = 0.5;
        c2.weighty = 0.5;
        c2.gridy = oldTaskPosition - 1;
        tempOldTask = oldTask;
        panel8.add(tempOldTask, c2);
        panel8.updateUI();
    }

    public void previousTasks() {
        if (addedHistory > 0) {
            changeHistroyValues();
        }
        oldTask = new JLabel((addedHistory+1)+" "+(history.get(addedHistory)), SwingConstants.CENTER);
        oldTask.setForeground(new Color(0xfaf3de));
        oldTask.setFont(new Font("Verdana", Font.PLAIN, 15));
        c2.insets = new Insets(5, 5, 5, 5);
        c2.weightx = 10;
        c2.weighty = 10;
        c2.gridy = oldTaskPosition;
        tempOldTask = oldTask;
        System.out.println("addedTaskToHistory");
        panel8.add(oldTask, c2);
        oldTaskPosition += 1;
        addedHistory += 1;
        panel8.updateUI();
    }

    public void changeValues() {// To push all Task buttons above;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = bx;
        c.gridy = by - 1;
        panel5.add(tempB, c);

        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = tx;
        c.gridy = ty - 1;
        // tempL = l1;
        panel5.add(tempL, c);
        panel5.updateUI();
    }

    public void addingTaskToRightPanel5() {
        System.out.println("Entered Adding task to Right Panel5");
        // Changing the previous values;
        if (tempCounter > 1 || count > 1) {
            changeValues();
        }
        // Adding the new Task
        // Done button
        Taskbtn = new JButton(new AbstractAction("Task - " + count) {// what should happen if task button is pressed
            public void actionPerformed(ActionEvent e) {
                ((JButton) e.getSource()).setBackground(Color.GREEN);
                hm.get((JButton) e.getSource()).setText("DONE");
                doneCount += 1;

                if (doneCount == tempCounter) {// if all work is done then reset everything
                    hm.clear();
                    System.out.println(hm.isEmpty());
                    doneCount = 0;
                    tempCounter = 0;
                    tempB = Taskbtn;
                    tempL = l1;
                    panel2.remove(scrollPane);
                    panel2.add(panel6);
                    scrollPane.setViewportView(panel5);
                    panel2.updateUI();
                    System.out.println(history);
                }
            }
        });
        Taskbtn.setFocusable(false);
        Taskbtn.setFont(new Font("Verdana", Font.BOLD, 15));
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 10;
        c.weighty = 10;
        c.gridx = bx;
        c.gridy = by;
        tempB = Taskbtn;
        System.out.println("added");
        panel5.add(Taskbtn, c);
        by += 1;

        // Task Label
        l1 = new JLabel(Task.getText());
        l1.setForeground(new Color(0xfaf3de));
        l1.setFont(new Font("Verdana", Font.PLAIN, 15));
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 10;
        c.weighty = 10;
        c.gridx = tx;
        c.gridy = ty;
        tempL = l1;
        System.out.println("added");
        panel5.add(l1, c);
        ty += 1;

        // Values and GUI updation
        hm.put(Taskbtn, l1);// every Button is Connected to Label
        // history.add(l1.getText());// all the task is added to this
        panel5.updateUI();
    }

    public void actionPerformed(ActionEvent e) {// Action after pressing buttons.
        if (e.getSource() == Add) {// If Add is pressed open Add Task Box
            System.out.println("Add Button Pressed");
            // Text
            Task = new JTextField(15);
            Task.setBounds(20, 20, 250, 25);
            Task.setLocation(0, 0);
            Task.setFont(new Font("Verdana", Font.BOLD, 15));
            Task.setHorizontalAlignment(JTextField.CENTER);

            // Panel4
            panel4 = new JPanel();
            panel4.setPreferredSize(new Dimension(300, 100));
            panel4.setBackground(new Color(0x1D1F23));
            panel4.add(Task);
            panel4.repaint();

            // addFrame Button
            Confirm = new JButton();
            Confirm.setBounds(105, 40, 80, 30);
            Confirm.setText("Confirm");
            Confirm.setFocusable(false);
            Confirm.setFont(new Font("Verdana", Font.BOLD, 10));
            Confirm.setBackground(new Color(0x33d646));
            Confirm.setForeground(Color.DARK_GRAY);
            Confirm.addActionListener(this);

            // Panel3
            panel3 = new JPanel();
            panel3.setPreferredSize(new Dimension(300, 60));
            panel3.add(Confirm);
            panel3.setBackground(new Color(0x1D1F23));
            panel3.repaint();

            // should open a frame to add task
            JFrame addFrame = new JFrame();
            addFrame.setTitle("Add The Task !");
            addFrame.setSize(300, 150);
            addFrame.setVisible(true);
            addFrame.setLayout(new BorderLayout());

            // components
            addFrame.add(panel3, BorderLayout.SOUTH);
            addFrame.add(panel4, BorderLayout.NORTH);
            addFrame.setResizable(false);
            addFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            addFrame.getContentPane().setBackground(Color.DARK_GRAY);
        }
        if (e.getSource() == Confirm) {// if confirm button is pressed then add it to scrollPane
            if (tempCounter == 0) {
                panel2.remove(panel6);
                panel2.add(scrollPane);
                panel2.updateUI();
                panel2.repaint();
            }
            count += 1;
            tempCounter += 1;
            System.out.println(count);
            System.out.println("Confirm Button Pressed");
            addingTaskToRightPanel5();
            history.add(Task.getText());
            previousTasks();
        }
        if (e.getSource() == History) {
            // previousTasks();
            if(count > 0){
                frame2.remove(panel9);
                frame2.add(scrollPane2);
            }
            frame2.setVisible(true);
        }
    }

    public void Mymain() throws Exception {

        // History FRAME***********************************************
        l2 = new JLabel("Task-History", SwingConstants.CENTER);
        l2.setBounds(0, 0, 600, 30);
        l2.setOpaque(true);
        l2.setForeground(new Color(0xfaf3de));
        l2.setBackground(new Color(0x1D1F23));
        l2.setFont(new Font("Verdana", Font.PLAIN, 15));
        l2.setVisible(true);

        panel8 = new JPanel(b2);
        panel8.setBackground(new Color(0x1D1F23));
        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.insets = new Insets(5, 5, 5, 5);
        scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(panel8);
        scrollPane2.setBackground(new Color(0, 0, 0, 55));
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(0, 30, 587, 434);

        BufferedImage myPic0 = ImageIO.read(new File("GUI/rightPanel7.png"));
        Image scaled2 = myPic0.getScaledInstance(516, 412, Image.SCALE_SMOOTH);
        historyLabel = new JLabel(new ImageIcon(scaled2));
        historyLabel.setBounds(0, 30, 587, 434);
        panel9 = new JPanel();
        panel9.setBounds(0,30,587,434);
        panel9.setBackground(new Color(0x1D1F23));
        panel9.add(historyLabel);
        panel9.updateUI();

        frame2 = new JFrame();
        frame2.setBounds(0, 0, 600, 500);
        // frame2.setBackground(Color.DARK_GRAY);
        frame2.getContentPane().setBackground(new Color(0x1D1F23));
        frame2.setLayout(null);
        frame2.add(l2);
        frame2.add(panel9);
        frame2.setVisible(false);// keeping it true means you are making frame visible
        frame2.setResizable(false);// now you can not resize the frame
        frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// by default it remains HIDE_ON_CLOSE;
        frame2.getContentPane().setBackground(new Color(0xdae1ed));// frame color
        // HISTORY FRAME
        // END***********************************************************************

        // MAIN
        // FRAME******************************************************************************

        // RIGHT VIEW
        // LABEL
        JLabel label = new JLabel("Increase Productivity and Save Time :-)");
        Dimension size = label.getPreferredSize();
        label.setBounds(121, 10, 308, size.height);
        label.setForeground(new Color(0xfaf3de));
        label.setFont(new Font("Verdana", Font.PLAIN, 15));
        label.setVisible(true);
        // Child Panel, this will lie below Label
        // ********************************************************************************
        // */

        panel5 = new JPanel(b);
        panel5.setBackground(new Color(0x1D1F23));
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(5, 5, 5, 5);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel5);
        scrollPane.setBackground(new Color(0, 0, 0, 55));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 40, 516, 412);

        BufferedImage myPic1 = ImageIO.read(new File("GUI/rightPanel7.png"));
        Image scaled1 = myPic1.getScaledInstance(516, 412, Image.SCALE_SMOOTH);
        rightLabel = new JLabel(new ImageIcon(scaled1));
        rightLabel.setBounds(0, 0, 150, 500);
        panel6 = new JPanel();
        panel6.setBounds(10, 40, 516, 412);
        panel6.setBackground(new Color(0x1D1F23));
        panel6.add(rightLabel);
        panel6.updateUI();
        // ********************************************************************************
        // */

        // Right Panel
        panel2 = new JPanel();
        panel2.setBackground(new Color(0x1D1F23));
        panel2.setBounds(150, 0, 550, 500);
        panel2.setLayout(null);
        panel2.add(label);
        panel2.add(panel6);
        panel2.repaint();

        // LEFT VIEW
        // BUTTONS
        Add = new JButton();
        Add.setBounds(10, 380, 130, 30);
        Add.setText("ADD");
        Add.setFocusable(false);// removes the black boundary around the button
        Add.setFont(new Font("Verdana", Font.BOLD, 15));
        Add.setBackground(new Color(0x1D1F23));
        Add.setForeground(new Color(0xffffff));

        History = new JButton();
        History.setBounds(10, 429, 130, 30);
        History.setText("HISTORY");
        History.setFocusable(false);// removes the black boundary around the button
        History.setFont(new Font("Verdana", Font.BOLD, 15));
        History.setBackground(new Color(0x1D1F23));
        History.setForeground(new Color(0xffffff));

        panel7 = new JPanel();
        panel7.setBounds(0, 373, 150, 90);
        panel7.setLayout(null);
        panel7.setBackground(new Color(0x000000));
        // LEFT PANEL
        // taken from Stackoverflow --
        // https://stackoverflow.com/questions/22679717/resizing-image-to-fit-in-jlabel
        BufferedImage myPic = ImageIO.read(new File("GUI/leftPanel4.png"));
        Image scaled = myPic.getScaledInstance(150, 500, Image.SCALE_SMOOTH);
        //////////////////////////////
        leftLabel = new JLabel(new ImageIcon(scaled));
        leftLabel.setBounds(0, 0, 150, 500);
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 150, 500);
        panel1.setLayout(null);
        panel1.add(Add);
        panel1.add(History);
        panel1.add(panel7);
        panel1.add(leftLabel);
        panel1.updateUI();
        panel1.repaint();
        panel1.revalidate();
        Add.addActionListener(this);
        History.addActionListener(this);

        // FRAME
        frame = new JFrame();// creating frame
        frame.setBounds(0, 0, 700, 500);// size of frame ,width x height;
        frame.setTitle("Project");
        frame.setLayout(null);
        frame.add(panel1);// Left view
        frame.add(panel2);// Right View
        frame.setBackground(new Color(0x1D1F23));
        frame.setVisible(true);// keeping it true means you are making frame visible
        frame.setResizable(false);// now you can not resize the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// by default it remains HIDE_ON_CLOSE;
        frame.getContentPane().setBackground(new Color(0xdae1ed));// frame color

        // *********************************************************************************************
        // */
    }
}

// Starter
public class test3 {
    public static void main(String[] args) throws Exception {
        gui34 project = new gui34();
        project.Mymain();
    }
}
