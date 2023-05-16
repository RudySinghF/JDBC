import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class JDBC extends JFrame implements ActionListener {
    private JTextField nameField, ageField,courses,idField;
    private JScrollPane scrollPane,spScrollPane;
    private ButtonGroup bg;

    private String selected,language,stateSelected,spSelected;
    private JList<String> list,sp;

    private JButton addButton, cancel;
    private JLabel messageLabel;


    private JRadioButton male,female;

    private JCheckBox cb1,cb2,cb3;

    private JComboBox<String> state;


    private Connection connection;
    public  JDBC(){
        super("Student Form");
        setLayout(new BorderLayout());
        // Panels
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new GridLayout(11,2,5,5));
        p2.setLayout(new GridLayout(1,2));
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(2,3));

//        label = new JLabel("Check");

        //List

        String states[]= {"--Select--","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar", "Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand", "West Bengal"};
        String specialization[]= {"--Select--","Artificial Intelligence", "Gaming", "Cyber Security","Cloud Computing", "Health Informatics","None"};
        list = new JList(states);
        sp = new JList(specialization);
//        state = new JComboBox<>(states);
        scrollPane = new JScrollPane(list);
        spScrollPane = new JScrollPane(sp);
        // Create text fields
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        idField = new JTextField(20);
        courses = new JTextField(20);
        // Create buttons
        addButton = new JButton("Submit");
        addButton.addActionListener(this);
        cancel = new JButton("Cancel");


//        Create Checkbox
        cb1 = new JCheckBox("C++ ");
        cb1.addActionListener(this);
        cb2 = new JCheckBox("Java ");
        cb2.addActionListener(this);
        cb3 = new JCheckBox("Python");
        cb3.addActionListener(this);



        // Create message label
        messageLabel = new JLabel("");


        p3.add(new JLabel(""));
        p3.add(new JLabel("Student Registration Form"));
        p3.add(new JLabel(""));
        p3.add(new JLabel(""));
        p3.add(new JLabel(""));
        p3.add(new JLabel(""));



        p1.add(new JLabel("Name:"));
        p1.add(nameField);
        p1.add(new JLabel("Age:"));
        p1.add(ageField);
        p1.add(new JLabel("Reg No:"));
        p1.add(idField);
        p1.add(new JLabel("Select gender:"));
        bg = new ButtonGroup();
        male = new JRadioButton("Male");
        male.addActionListener(this);
        female = new JRadioButton("Female");
        male.addActionListener(this);
        bg.add(male);
        bg.add(female);
        p1.add(male);
        p1.add(new JLabel(""));
        p1.add(female);
        p1.add(new JLabel("Select state:"));
//        p1.add(state);
        p1.add(scrollPane);
        p1.add(new JLabel("Select Programming:"));
        p1.add(cb1);
        p1.add(new JLabel(""));
        p1.add(cb2);
        p1.add(new JLabel(""));
        p1.add(cb3);
        p1.add(new JLabel("Course"));
        p1.add(courses);
        p1.add(new JLabel("Specialization (if any):"));
        p1.add(spScrollPane);


        p2.add(addButton);
        p2.add(cancel);

//        p2.add(updateButton);
//        p2.add(deleteButton);
        add(messageLabel);
        add(p3,BorderLayout.NORTH);
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
        // Connect to MySQL database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "rudyxGG@komradez101");
        } catch (Exception ex) {
            messageLabel.setText("Error connecting to database: " + ex.getMessage());
        }

        // Set window size and visibility
        setSize(400, 400);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            Statement statement = connection.createStatement();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            int id = Integer.parseInt(idField.getText());
            if(e.getSource()==male) {
                selected = male.getActionCommand();
            }
            if(e.getSource()==female) {
                selected = female.getActionCommand();
            }
            if(e.getSource()==cb1){
                language = cb1.getActionCommand();
            }
            if(e.getSource()==cb2){
                language = cb2.getActionCommand();
            }
            if(e.getSource()==cb3){
                language = cb3.getActionCommand();
            }
            if(e.getActionCommand().equals("Submit")){
                stateSelected = list.getSelectedValue();
                spSelected = sp.getSelectedValue();
            }
            String course = courses.getText();
            if (e.getSource() == addButton) {
                statement.executeUpdate("INSERT INTO student (name, age, id, gender, state, programming, course, specialization) VALUES ('" + name + "', " + age + ", " + id + ",'" + selected + "','" + stateSelected + "','" + language + "','" + course + "','" + spSelected + "')");
                messageLabel.setText("Record added successfully");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }
    public static void main(String[] args) {
        new JDBC();
    }


}

