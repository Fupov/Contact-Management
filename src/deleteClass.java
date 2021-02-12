import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Projet Réaliser par Aymane Tchich.
 * deleteClass est la Class Chargé de la suppression d'un contact qui existe dans la base de données,
 * L'utilisateur dois saisir le numero de telephone du contact pour le supprimer vu que le numero est une valeur unique pour chaque contact
 * L'application Gere les differents erreurs et exceptions possible d'une maniere dynamique.
 */

public class deleteClass extends Frame {
    Label Name;
    Label ump;
    Label esto;
    Label author;
    Label instruction1;
    Label instruction2;
    Label err;
    Label success;
    TextField nom;
    Button show;
    public deleteClass() {

        super("Supprimer Contact");
        setSize(500, 400);setLayout(null);setVisible(true);setResizable(false);
        setBackground(Color.lightGray);

        ump = new Label("Université Mohammed Premier - Oujda", Label.CENTER);
        esto = new Label("Ecole Supérieure de Technologie", Label.CENTER);
        instruction1=new Label("NB: Afin de supprimer un contact suivant un filtre de recherche,",Label.LEFT);
        instruction2=new Label("  Veuillez saisir le numero du contact",Label.LEFT);
        Name = new Label("Numero du contact: ",Label.LEFT);

        nom = new TextField();

        ump.setBounds(100, 50, 300, 30);
        esto.setBounds(100, 70, 300, 30);
        instruction1.setBounds(100, 120, 400, 30);
        instruction2.setBounds(95, 140, 400, 30);
        Name.setBounds(175, 190, 150, 30);
        nom.setBounds(175, 220, 150, 30);

        show = new Button("Supprimer");
        show.setBounds(175,260,150,30);

        author = new Label("Made by Aymane Tchich", Label.CENTER);
        author.setBounds(100, 350, 300, 30);

        err = new Label("error");
        success = new Label("ok");

        add(esto);add(ump);add(instruction1);add(instruction2);
        add(nom);add(Name);add(show);add(author);

        show.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    String url="jdbc:oracle:thin:@localhost:1521:XE";
                    Connection conn= DriverManager.getConnection(url,"hr","hr");
                    Statement st=conn.createStatement();
                    ResultSet rs = st.executeQuery("select numero_telephone from contacts where numero_telephone='"+nom.getText()+"'");

                    if (rs.next()){
                        err.setVisible(false);
                        ResultSet rst = st.executeQuery("delete from contacts where numero_telephone='"+nom.getText()+"'");
                        success = new Label("Suppression effectuer avec Success");
                        success.setForeground(Color.BLUE);
                        success.setBounds(155,300,300,30);
                        add(success);
                    }else{
                        success.setVisible(false);
                        err=new Label("Contact introuvable");
                        err.setForeground(Color.RED);
                        err.setBounds(195,300,300,30);
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
