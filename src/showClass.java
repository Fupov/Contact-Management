import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Projet Réaliser par Aymane Tchich.
 * showClass est la Class Chargé de l'affichage des contacts qui existe dans la base de données, suivant un filtre de recherche.
 * L'utilisateur dois saisir le Nom de famille des contacts.
 * Les informations seront stocker dans un fichier texte nommé (afficherContacts.txt);
 * L'application Gere les differents erreurs et exceptions possible d'une maniere dynamique.
 */

public class showClass extends Frame {
    Label Name;
    Label ump;
    Label esto;
    Label author;
    Label instruction1;
    Label instruction2;
    TextField nom;
    Button show;
    Label err,success;
    public showClass() {

        super("Afficher Contact");
        setSize(500, 400);setLayout(null);setVisible(true);setResizable(false);
        setBackground(Color.lightGray);

        ump = new Label("Université Mohammed Premier - Oujda", Label.CENTER);
        esto = new Label("Ecole Supérieure de Technologie", Label.CENTER);
        instruction1=new Label("NB: Afin d'afficher un contact suivant un filtre de recherche,",Label.LEFT);
        instruction2=new Label("  Veuillez saisir le nom du contact",Label.LEFT);
        Name = new Label("Nom du contact: ",Label.LEFT);

        nom = new TextField();
        ump.setBounds(100, 50, 300, 30);
        esto.setBounds(100, 70, 300, 30);
        instruction1.setBounds(100, 120, 400, 30);
        instruction2.setBounds(95, 140, 400, 30);
        Name.setBounds(175, 190, 150, 30);
        nom.setBounds(175, 220, 150, 30);

        show = new Button("Afficher");
        show.setBounds(175,260,150,30);

        author = new Label("Made by Aymane Tchich", Label.CENTER);
        author.setBounds(100, 350, 300, 30);

        add(esto);add(ump);add(instruction1);add(instruction2);
        add(nom);add(Name);add(show);add(author);

        err = new Label("error");
        success = new Label("ok");

        show.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                try{
                    String url="jdbc:oracle:thin:@localhost:1521:XE";
                    Connection conn= DriverManager.getConnection(url,"hr","hr");
                    Statement st=conn.createStatement();

                    ResultSet rs = st.executeQuery("select nom from contacts where nom='"+nom.getText()+"'");
                    if(rs.next()){
                        err.setVisible(false);
                        ResultSet tsc = st.executeQuery("select * from contacts where nom='"+nom.getText()+"'");

                        File nomFicher= new File("afficherContacts.txt");
                        FileWriter fw = new FileWriter(nomFicher);
                        while(tsc.next()){
                            String nomRequest = tsc.getString("nom");
                            String prenomRequest = tsc.getString("prenom");
                            String emailRequest = tsc.getString("email");
                            String numeroRequest = tsc.getString("numero_telephone");
                            fw.write(nomRequest); fw.write(" "+prenomRequest);fw.write(" "+emailRequest);fw.write(" "+numeroRequest+"\n");
                        }
                        fw.close();
                        success = new Label("Informations exportés avec Success");
                        success.setForeground(Color.BLUE);
                        success.setBounds(155,300,300,30);
                        add(success);
                        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "afficherContacts.txt");
                        pb.start();

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
