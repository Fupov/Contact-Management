import java.awt.*;
import java.sql.*;
import java.awt.event.*;

/**
 * Projet Réaliser par Aymane Tchich.
 * authClass est la Class Chargé de l'authentification,
 * L'utilisateur dois saisir le Username et le Password pour pourvoir acceder au differents fonctionalité de l'application
 * L'application Gere les differents erreurs et exceptions possible d'une maniere dynamique.
 */

public class authClass extends Frame{

    Label esto; Label ump; Label username; Label password; Label author;
    TextField user; TextField mdp;
    Button send;

    public authClass(){

        super("Autentification");

        setSize(500,400); setLayout(null); setVisible(true); setResizable(false);
        setBackground(Color.lightGray);

        ump = new Label("Université Mohammed Premier - Oujda",Label.CENTER);
        esto = new Label("Ecole Supérieure de Technologie",Label.CENTER);
        username = new Label("Username",Label.CENTER);
        password = new Label("Password",Label.CENTER);

        user = new TextField();
        mdp = new TextField();

        ump.setBounds(100,50,300,30);
        esto.setBounds(100,70,300,30);
        username.setBounds(175,120,150,30);
        password.setBounds(175,180,150,30);
        user.setBounds(175,150,150,30);
        mdp.setBounds(175,210,150,30);

        send = new Button("Login");
        send.setBounds(175,270,150,30);

        author = new Label("Made by Aymane Tchich",Label.CENTER);
        author.setBounds(100,350,300,30);

        add(ump);add(esto);add(username);add(user);
        add(password);add(mdp);add(send);add(author);

        send.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try{

                    String url="jdbc:oracle:thin:@localhost:1521:XE";
                    Connection conn= DriverManager.getConnection(url,"hr","hr");
                    Statement st=conn.createStatement();

                    ResultSet rs = st.executeQuery("select username,password from admin where username='"+user.getText()+"' and password='"+mdp.getText()+"'");

                    if(rs.next()){

                        new indexClass();
                        setVisible(false);

                    }else{

                        Label err=new Label("username or password incorrect");
                        err.setForeground(Color.RED);
                        err.setBounds(155,300,300,30);
                        add(err);
                    }

                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
        });

        addWindowListener( new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
