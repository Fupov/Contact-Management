import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Projet Réaliser par Aymane Tchich.
 * addClass est la Class qui contient les differents methode et evenement pour ajouter un contact,
 * Il existe 2 methode pour ajouter des contacts:
    * Soit d'ajouter contact par contact depuis l'application,
    * Soit d'ajouter Plusieurs contacts a la fois depuis un fichier text.
 * L'application Gere les differents erreurs et exceptions possible d'une maniere dynamique.
 */

public class addClass  extends Frame {
    Label Name;
    Label Prenom;
    Label email;
    Label Numero;
    Label ump;
    Label esto;
    Label author;
    Label text;
    Label instruction1;
    Label instruction2;
    TextField textData;
    TextField nom;
    TextField surname;
    TextField adresse;
    TextField phone;
    Button show;
    Button textAdd;
    Label err,err2,success;

    public addClass() {

        super("Ajouter Contact");
        setSize(800, 400);setLayout(null);setVisible(true);setResizable(false);
        setBackground(Color.lightGray);

        ump = new Label("Université Mohammed Premier - Oujda", Label.CENTER);
        esto = new Label("Ecole Supérieure de Technologie", Label.CENTER);
        Name = new Label("Nom: ",Label.LEFT);
        Prenom = new Label("Prenom: ",Label.LEFT);
        email = new Label("Email: ",Label.LEFT);
        Numero = new Label("Telephone: ",Label.LEFT);
        instruction1 = new Label("Pour ajouter plusieur Contact a partir d'un fichier",Label.LEFT);
        instruction2 = new Label("Veuillez saisir le chemin du fichier",Label.LEFT);
        text = new Label("Nom du fichier: ",Label.LEFT);


        surname = new TextField();
        nom = new TextField();
        adresse = new TextField();
        phone = new TextField();
        textData = new TextField();

        ump.setBounds(250, 50, 300, 30);
        esto.setBounds(250, 70, 300, 30);
        Name.setBounds(100, 140, 150, 30);
        nom.setBounds(175, 140, 200, 30);
        Prenom.setBounds(100, 180, 150, 30);
        surname.setBounds(175, 180, 200, 30);
        email.setBounds(100, 220, 150, 30);
        adresse.setBounds(175, 220, 200, 30);
        Numero.setBounds(100, 260, 150, 30);
        phone.setBounds(175, 260, 200, 30);
        instruction1.setBounds(425, 140, 400, 10);
        instruction2.setBounds(425, 157, 400, 10);
        text.setBounds(425, 180, 150, 30);
        textData.setBounds(550, 180, 200, 30);

        textAdd = new Button("Ajouter");
        show = new Button("Ajouter");

        textAdd.setBounds(550,220,200,30);
        show.setBounds(175,300,200,30);

        author = new Label("Made by Aymane Tchich", Label.CENTER);
        author.setBounds(250, 350, 300, 30);


        add(esto);add(ump);add(nom);add(Name);add(surname);add(Prenom);add(adresse);
        add(email);add(phone);add(Numero);add(textAdd);add(textData);add(text);
        add(show);add(author);add(instruction1);add(instruction2);

        err = new Label("error");
        err2=new Label("error");
        success = new Label("ok");

        show.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    err2.setVisible(false);
                    String url="jdbc:oracle:thin:@localhost:1521:XE";
                    Connection conn= DriverManager.getConnection(url,"hr","hr");
                    Statement st=conn.createStatement();
                    ResultSet rs = st.executeQuery("select * from contacts where nom='"+nom.getText()+"' and prenom='"+surname.getText()+"' and email='"+adresse.getText()+"' and numero_telephone='"+phone.getText()+"'");
                    if(rs.next()){
                        success.setVisible(false);
                        err = new Label("Information Contact existe deja");
                        err.setForeground(Color.red);
                        err.setBounds(200,330,300,30);
                        add(err);
                    }else{
                        err.setVisible(false);
                        err2.setVisible(false);
                        ResultSet rst = st.executeQuery("insert into contacts values('"+nom.getText()+"','"+surname.getText()+"','"+adresse.getText()+"','"+phone.getText()+"')");
                        success = new Label("Ajout effectuer avec Success");
                        success.setForeground(Color.BLUE);
                        success.setBounds(200,330,300,30);
                        add(success);}
                } catch (Exception et) {
                    success.setVisible(false);
                    err2 = new Label("Veuillez remplir tout les Champs");
                    err2.setForeground(Color.red);
                    err2.setBounds(200,330,300,30);
                    add(err2);

                }
            }
        });

        textAdd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    err.setVisible(false);
                    String url="jdbc:oracle:thin:@localhost:1521:XE";
                    Connection conn= DriverManager.getConnection(url,"hr","hr");
                    Statement st=conn.createStatement();

                        File nomFicher= new File(textData.getText());
                        FileReader c= new FileReader(nomFicher);
                        BufferedReader IN = new BufferedReader (c);
                        String ligne;
                        String [] mot;
                        int k=0;
                        while ((ligne = IN.readLine ())!= null){
                            mot = ligne.split (" ");
                            ResultSet rst = st.executeQuery("insert into contacts values('"+mot[0]+"','"+mot[1]+"','"+mot[2]+"','"+mot[3]+"')");
                        }
                        c.close();
                        success = new Label("Ajout effectuer avec Success");
                        success.setForeground(Color.BLUE);
                        success.setBounds(570,250,300,30);
                        add(success);
                } catch (Exception et) {
                    success.setVisible(false);
                    err = new Label("Fichier introuvable");
                    err.setForeground(Color.red);
                    err.setBounds(570,250,300,20);
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
