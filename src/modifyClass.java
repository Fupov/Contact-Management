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
 * modifyClass est la Class Chargé de la modification d'un contact qui existe dans la base de données,
 *  * L'utilisateur dois saisir le numero de telephone du contact pour le modifier vu que le numero est une valeur unique pour chaque contact
 * L'application Gere les differents erreurs et exceptions possible d'une maniere dynamique.
 */

public class modifyClass extends Frame{
    Label nameRequest;
    Label Name;
    Label Prenom;
    Label email;
    Label Numero;
    Label ump;
    Label esto;
    Label author;
    Label err,success;
    TextField requestData;
    TextField nom;
    TextField surname;
    TextField adresse;
    TextField phone;
    Button show;
    Label separator;
    public modifyClass() {

        super("Modifier Contact");
        setSize(500, 500);setLayout(null);setVisible(true);setResizable(false);
        setBackground(Color.lightGray);

        ump = new Label("Université Mohammed Premier - Oujda", Label.CENTER);
        esto = new Label("Ecole Supérieure de Technologie", Label.CENTER);
        separator = new Label("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -",Label.LEFT);
        nameRequest = new Label("Contact Phone: ",Label.LEFT);
        Name = new Label("Nom: ",Label.LEFT);
        Prenom = new Label("Prénom: ",Label.LEFT);
        email = new Label("Email: ",Label.LEFT);
        Numero = new Label("Telephone: ",Label.LEFT);

        requestData = new TextField();
        nom = new TextField();
        surname = new TextField();
        adresse = new TextField();
        phone = new TextField();

        ump.setBounds(100, 50, 300, 30);
        esto.setBounds(100, 70, 300, 30);
        separator.setBounds(100, 180, 300, 10);
        nameRequest.setBounds(100, 140, 150, 30);
        requestData.setBounds(185, 140, 200, 30);
        Name.setBounds(100, 200, 150, 30);
        nom.setBounds(185, 200, 200, 30);
        Prenom.setBounds(100, 240, 150, 30);
        surname.setBounds(185, 240, 200, 30);
        email.setBounds(100, 280, 150, 30);
        adresse.setBounds(185, 280, 200, 30);
        Numero.setBounds(100, 320, 150, 30);
        phone.setBounds(185, 320, 200, 30);


        show = new Button("Modifier");
        show.setBounds(185,360,200,30);


        author = new Label("Made by Aymane Tchich", Label.CENTER);
        author.setBounds(100, 450, 300, 30);

        err = new Label("error");
        success = new Label("ok");

        add(esto);add(ump);add(nom);add(Name);add(surname);add(Prenom);
        add(adresse);add(email);add(phone);add(Numero);add(requestData);
        add(nameRequest);add(separator);add(show);add(author);

        show.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    String url="jdbc:oracle:thin:@localhost:1521:XE";
                    Connection conn= DriverManager.getConnection(url,"hr","hr");
                    Statement st=conn.createStatement();
                    ResultSet rs = st.executeQuery("select numero_telephone from contacts where numero_telephone='"+requestData.getText()+"'");

                    if (rs.next()){
                        err.setVisible(false);
                        ResultSet rst = st.executeQuery("update contacts set nom='"+nom.getText()+"' ,prenom='"+surname.getText()+"' ,email='"+adresse.getText()+"' , numero_telephone='"+phone.getText()+"' where numero_telephone='"+requestData.getText()+"'");
                        success = new Label("Mise a jour avec Success");
                        success.setForeground(Color.BLUE);
                        success.setBounds(215,400,300,30);
                        add(success);
                    }else{
                        success.setVisible(false);
                        err=new Label("Contact introuvable");
                        err.setForeground(Color.RED);
                        err.setBounds(215,110,300,20);
                        add(err);
                    }
                } catch (Exception et) {
                    success.setVisible(false);
                    err = new Label("Veuillez Remplir tour les Champs");
                    err.setForeground(Color.red);
                    err.setBounds(200,400,300,30);
                    add(err);
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
