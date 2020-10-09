/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author titi
 */
public class ConfigBox extends JDialog{
    private Config config;
    private JComboBox nombre, gravite, ajout;
    private JLabel nombreLabel, graviteLabel, ajoutLabel;
    
    public ConfigBox(JFrame parent, String title, boolean modal, Config config){
        super(parent, title, modal);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.config = config;
        this.initComponent();
    }
    
    public void setConfig(Config config){
        this.config = config;
    }
    
    private void initComponent(){
        //Le type de block
        JPanel panelConfig = new JPanel();
        panelConfig.setBackground(Color.white);
        panelConfig.setPreferredSize(new Dimension(200, 140));
        panelConfig.setBorder(BorderFactory.createTitledBorder("Configuration"));
        nombre = new JComboBox();
        nombre.addItem(3);
        nombre.addItem(4);
        nombre.addItem(5);
        nombre.addItem(6);
        nombre.addItem(7);
        nombre.addItem(8);
        nombre.setSelectedItem(config.nombreDeCouleur);
        nombreLabel = new JLabel("Nombre de couleur");
        panelConfig.add(nombreLabel);
        panelConfig.add(nombre);
        
        gravite = new JComboBox();
        gravite.addItem("on");
        gravite.addItem("off");
        gravite.setSelectedItem(this.onOff(config.gravite));
        graviteLabel = new JLabel("Effet de gravité");
        panelConfig.add(graviteLabel);
        panelConfig.add(gravite);
        
        ajout = new JComboBox();
        ajout.addItem("on");
        ajout.addItem("off");
        ajout.setSelectedItem(this.onOff(config.ajoutCol));
        ajoutLabel = new JLabel("Ajout de colonne");
        panelConfig.add(ajoutLabel);
        panelConfig.add(ajout);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panelConfig);

        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");
    
        okBouton.addActionListener((ActionEvent arg0) -> {
            int nombreDeCouleur = (int) nombre.getSelectedItem();
            boolean avec_gravite = this.onOff((String) gravite.getSelectedItem());
            boolean avec_ajout = this.onOff((String) ajout.getSelectedItem());
            config = new Config(nombreDeCouleur, avec_gravite, avec_ajout);
        
            System.out.println(config.getNombreDeCouleur() + " - " + config.getGravite() + " - " + config.getAjoutCol());
            setVisible(false);
        });

        JButton cancelBouton = new JButton("Annuler");
        cancelBouton.addActionListener((ActionEvent arg0) -> {
            setVisible(false);      
        });

        control.add(okBouton);
        control.add(cancelBouton);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }
    
    public Config afficher(){
        this.setVisible(true);      
        return config;
    }
    
    public boolean onOff(String value){
        return "on".equals(value);
    }
    
    public String onOff(boolean value){
        if(value){
            return "on";
        } else {
            return "off";
        }
    }
}
