import java.awt.*;
import java.awt.event.*;

/**
 * Projet Réaliser par Aymane Tchich.
 * indexClass s'agit de notre page d'acceuil de notre application
 * il se compose de 4 menu : afficher, ajouter, supprimer, modifier;
 * il se compose de 4 bouton : afficher, ajouter, supprimer, modifier;
 * L'utilisateur doit cliser sur un bouton ou choisir un menu pour acceder a la fonctionalité souhaité
 * L'application Gere les differents erreurs et exceptions possible d'une maniere dynamique.
 */

public class indexClass extends Frame{

    /*Declaration des Variables*/
    Button ajouter;
    Button supprimer;
    Button modifier;
    Button afficher;
    Label ump;
    Label esto;
    Label author;

    /*Constructeur de la Classe*/
    public indexClass() {

        super("Page d'acceuil");
        ump = new Label("Université Mohammed Premier - Oujda",Label.CENTER);
        ump.setBounds(100,50,300,30);
        esto = new Label("Ecole Supérieure de Technologie",Label.CENTER);
        esto.setBounds(100,70,300,30);
        setSize(500,400); setLayout(null); setVisible(true); setResizable(false);
        setBackground(Color.lightGray);


        MenuBar mb = new MenuBar();
        Menu menuList = new Menu("Menu List");

        mb.add(menuList);

        MenuItem addMenu = new MenuItem("Ajouter");
        MenuItem showMenu = new MenuItem("Afficher");
        MenuItem modifyMenu = new MenuItem("modifier");
        MenuItem deleteMenu = new MenuItem("Supprimer");

        menuList.add(showMenu);
        menuList.add(addMenu);
        menuList.add(modifyMenu);
        menuList.add(deleteMenu);

        setMenuBar(mb);

        ajouter = new Button("Ajouter");
        supprimer = new Button("Supprimer");
        modifier = new Button("Modifier");
        afficher = new Button("Afficher");

        afficher.setBounds(175,150,150,30);
        ajouter.setBounds(175,190,150,30);
        modifier.setBounds(175,230,150,30);
        supprimer.setBounds(175,270,150,30);

        author = new Label("Made by Aymane Tchich",Label.CENTER);
        author.setBounds(100,350,300,30);

        add(esto);add(ump);add(ajouter);add(supprimer);
        add(modifier);add(afficher);add(author);

        afficher.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    new showClass();
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
        });

        ajouter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    new addClass();
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
        });


        modifier.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    new modifyClass();
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
        });

        supprimer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    new deleteClass();
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
        });

        addWindowListener( new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        showMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new showClass();
            }
        });
        addMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addClass();
            }
        });
        modifyMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new modifyClass();
            }
        });
        deleteMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteClass();
            }
        });
    }

}