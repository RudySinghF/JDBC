import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class StudentTable extends JFrame implements ActionListener,MouseListener{

    private JTree stdTree;

    private JLabel messageLabel;


    private DefaultMutableTreeNode a,b,c,d,ee,f,g,h;


    private Connection connection;

    public StudentTable(){
        super("Student Tree");
        setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        JButton display = new JButton("Display");
        display.addActionListener(this);


        p1.setLayout(new GridLayout(1,3));
        p2.setLayout(new GridLayout(2,1));
        p3.setLayout(new GridLayout(2,3));


        messageLabel = new JLabel("");

        p1.add(new JLabel(""));
        p1.add(new JLabel("             Student Tree"));
        p1.add(new JLabel(""));

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Student");
         a = new DefaultMutableTreeNode("ID");
        top.add(a);
         b = new DefaultMutableTreeNode("Name");
        top.add(b);
         c = new DefaultMutableTreeNode("Age");
        top.add(c);
         d = new DefaultMutableTreeNode("Gender");
        top.add(d);
         ee = new DefaultMutableTreeNode("State");
        top.add(ee);
         f = new DefaultMutableTreeNode("Programming");
        top.add(f);
         g = new DefaultMutableTreeNode("Course");
        top.add(g);
         h = new DefaultMutableTreeNode("Specialization");
        top.add(h);

        stdTree = new JTree(top);
        stdTree.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(stdTree);
        p2.add(scrollPane);

        p3.add(new JLabel(""));
        p3.add(display);
        p3.add(new JLabel(""));
        p3.add(messageLabel);
        p3.add(new JLabel(""));



        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);


        // Connect to MySQL database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "rudyxGG@komradez101");

        } catch (Exception ex) {
            messageLabel.setText("Error connecting to database: " + ex.getMessage());
        }

        // Set window size and visibility
        setSize(500, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Statement statement = connection.createStatement();
            String query = "select * from student";
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rSmD = rs.getMetaData();

            DefaultMutableTreeNode id,name,age,gender,state,programming,course,specialization;

            while(rs.next()){

                id = new DefaultMutableTreeNode(rs.getString(1));
                a.add(id);
                name = new DefaultMutableTreeNode(rs.getString(2));
                b.add(name);
                age = new DefaultMutableTreeNode(rs.getString(3));
                c.add(age);
                gender = new DefaultMutableTreeNode(rs.getString(4));
                d.add(gender);
                state = new DefaultMutableTreeNode(rs.getString(5));
                ee.add(state);
                programming = new DefaultMutableTreeNode(rs.getString(6));
                f.add(programming);
                course = new DefaultMutableTreeNode(rs.getString(7));
                g.add(course);
                specialization = new DefaultMutableTreeNode(rs.getString(8));
                h.add(specialization);
            }
            messageLabel.setText("Success");
            statement.close();
            connection.close();


        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void doMouseClicked(MouseEvent me){
        TreePath tp = stdTree.getPathForLocation(me.getX(),me.getY());
        if(tp!=null){
            messageLabel.setText((tp.toString()));
        }
        else {
            messageLabel.setText(" ");
        }
    }

    public static void main(String[] args) {
        new StudentTable();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        doMouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
