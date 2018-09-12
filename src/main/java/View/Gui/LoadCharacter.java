/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui;

import Model.Characters.Hero;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author spencer
 */
public class LoadCharacter extends javax.swing.JPanel {

    private ArrayList<String> list;
    private static Hero hero;

    /**
     * Creates new form LoadCharacter
     */
    public LoadCharacter() {
        initComponents();

        addItems();
    }

    private void addItems() {
//        ArrayList<String> list = new ArrayList<>();

        String fileName = System.getProperty("user.dir") + "/src/main/resources/saves/";
        File folder = new File(fileName);
        File[] listOfFiles = folder.listFiles();

        //TODO: check for exception
        for (File file : listOfFiles) {
            loadCharacterComboBox.addItem(file.getName());
        }
    }

    public static Hero getHero() {
        return hero;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        loadCharacterComboBox = new javax.swing.JComboBox<>();
        heroInfoScrollPanel = new javax.swing.JScrollPane();
        heroInfoTextArea = new javax.swing.JTextArea();
        loadButton = new javax.swing.JButton();

        backButton.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        backButton.setText("Back");
        backButton.setMaximumSize(new java.awt.Dimension(165, 30));
        backButton.setMinimumSize(new java.awt.Dimension(165, 30));
        backButton.setPreferredSize(new java.awt.Dimension(165, 30));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        loadCharacterComboBox.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        loadCharacterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Hero" }));
        loadCharacterComboBox.setMaximumSize(new java.awt.Dimension(400, 30));
        loadCharacterComboBox.setMinimumSize(new java.awt.Dimension(400, 30));
        loadCharacterComboBox.setPreferredSize(new java.awt.Dimension(400, 30));
        loadCharacterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadCharacterComboBoxActionPerformed(evt);
            }
        });

        heroInfoTextArea.setColumns(20);
        heroInfoTextArea.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        heroInfoTextArea.setRows(5);
        heroInfoScrollPanel.setViewportView(heroInfoTextArea);

        loadButton.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        loadButton.setText("Load");
        loadButton.setMaximumSize(new java.awt.Dimension(165, 30));
        loadButton.setMinimumSize(new java.awt.Dimension(165, 30));
        loadButton.setPreferredSize(new java.awt.Dimension(165, 30));
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(heroInfoScrollPanel)
                            .addComponent(loadCharacterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(loadCharacterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(heroInfoScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.firePropertyChange("Back", null, evt);
    }//GEN-LAST:event_backButtonActionPerformed

    private void loadCharacterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadCharacterComboBoxActionPerformed
        // TODO add your handling code here:
        heroInfoTextArea.setText(null);

        if (!(loadCharacterComboBox.getSelectedItem() == "Select Hero")) {
            String fileName = System.getProperty("user.dir")
                    + "/src/main/resources/saves/"
                    + loadCharacterComboBox.getSelectedItem();
            list = new ArrayList<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("\nThat file does not exist.");
//            loadCharacter();
            }

            heroInfoTextArea.setText(
                    "Hero Attributes\n"
                    + "- - - - - - - - - - - - - - - -"
                    + "\nName:   " + list.get(0)
                    + "\nClass:    " + list.get(1)
                    + "\nLevel:    " + list.get(2)
                    + "\nEXP:       " + list.get(3)
                    + "\nATT:       " + list.get(4)
                    + "\nDEF:      " + list.get(5)
                    + "\nHP:         " + list.get(6)
                    + "\n\nHero Artifacts"
                    + "\n- - - - - - - - - - - - - -"
                    + "\nHelmet:    " + list.get(7) + " HP"
                    + "\nArmor:      " + list.get(8) + " DEF"
                    + "\nWeapon:  " + list.get(9) + " ATT\n"
            );
        }
    }//GEN-LAST:event_loadCharacterComboBoxActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        // TODO add your handling code here:
        if (loadCharacterComboBox.getSelectedItem() == "Select Hero") {
            this.firePropertyChange("Default", null, evt);
        } else {
            hero = new Hero(list);
            this.firePropertyChange("Load", null, evt);
        }
    }//GEN-LAST:event_loadButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane heroInfoScrollPanel;
    private javax.swing.JTextArea heroInfoTextArea;
    private javax.swing.JButton loadButton;
    private javax.swing.JComboBox<String> loadCharacterComboBox;
    // End of variables declaration//GEN-END:variables
}
