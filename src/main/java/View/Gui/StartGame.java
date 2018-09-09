/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui;

import Controller.Main;
import Model.Artifacts.Armor;
import Model.Artifacts.Helm;
import Model.Artifacts.Weapon;
import Model.Characters.Empty;
import Model.Characters.Hero;
import Model.Characters.Villain;
import Model.Map;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author spencer
 */
public class StartGame extends javax.swing.JPanel {

    private static Hero hero;
    private Map map;
    private static Armor tempArmor;
    private static Helm tempHelm;
    private static Weapon tempWeapon;
    private Random rand;
    private JDialog dialogWindow;

    /**
     * Creates new form StartGame
     */
    public StartGame() {
        initComponents();
        map = View.getMap();
        hero = View.getHero();
        Armor tempArmor = null;
        Helm tempHelm = null;;
        Weapon tempWeapon = null;;
        rand = new Random();
        displayMap();
    }

    public static Hero getHero() {
        return hero;
    }

    public Map getMap() {
        return map;
    }

    public static void setHelm(Helm helm) {
        tempHelm = helm;
    }

    public static void setArmor(Armor armor) {
        tempArmor = armor;
    }

    public static void setWeapon(Weapon weapon) {
        tempWeapon = weapon;
    }

    private void displayMap() {
        mapDisplayArea.setText(null);
        for (int y = 0; y < map.getSize(); ++y) {
            for (int x = 0; x < map.getSize(); ++x) {
                if (hero.getX() == x && hero.getY() == y) {
                    if (x == 0) {
                        mapDisplayArea.append("o ");
                    } else if (x == map.getSize() - 1) {
                        mapDisplayArea.append(" o");
                    } else {
                        mapDisplayArea.append(" o ");
                    }
                    continue;
                }
                mapDisplayArea.append(map.getCharacter(y, x).getName());
            }

            mapDisplayArea.append("\n");
        }
    }

    public void battle() {

//        Model.Characters.Character villain = map.getCharacter(hero.getY(), hero.getX());
        if (map.getCharacter(hero.getY(), hero.getX()).getClass().getSimpleName().equals("Villain")) {

            dialogWindow = new JDialog();
            HeroBattle battlePanel = new HeroBattle();

            battlePanel.addPropertyChangeListener(new BattleListener());
            dialogWindow.setTitle("Villain Encountered");
            dialogWindow.setModal(true);
            dialogWindow.setContentPane(battlePanel);
            dialogWindow.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dialogWindow.pack();
            dialogWindow.setLocationRelativeTo(null);
            dialogWindow.setVisible(true);

        }
    }

    private class BattleListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            System.out.println("0");
            if (source == "Fight") {
                if (!battleFight(hero, map.getCharacter(hero.getY(), hero.getX()))) {
                    dialogWindow.dispose();
                    JOptionPane.showMessageDialog(null, "Game Over!", "DEFEAT",
                            JOptionPane.ERROR_MESSAGE);
                    mainMenuButton.doClick();
                } else {
                    dialogWindow.dispose();
                    JOptionPane.showMessageDialog(null, "You are victorious!",
                            "VICTORY", JOptionPane.INFORMATION_MESSAGE);
                    hero.increaseExperience();
                    dropArtifact(hero.getLevel());
                    map.removeVillain();

                }
            } else if (source == "Run") {
                battleRun();
            }
        }
    }

    private boolean battleFight(Model.Characters.Character hero,
            Model.Characters.Character villain) {

        int attack;
        int heroHitPoints = hero.getHitPoints();
        int villainHitPoints = villain.getHitPoints();

        System.out.println("\nHero     | Villain2");
        System.out.println("--------------------");
        System.out.println("HP : " + hero.getHitPoints() + " | " + villain.getHitPoints());
        System.out.println("DEF:  " + hero.getDefense() + " | " + villain.getDefense());
        System.out.println("ATT:  " + hero.getAttack() + " | " + villain.getAttack());

        while (heroHitPoints > 0 && villainHitPoints > 0) {

//          hero's attack damage on villain
            attack = hero.getAttack() / ((rand.nextInt(5) + 1));

            if ((villainHitPoints -= attack) <= 0) {
                return true;
            }

//          villain's attack damage on hero
            attack = villain.getAttack() / ((rand.nextInt(5) + 1));
            if ((rand.nextInt(20) + 1) == 1) {
                attack *= 1.5;
            }

            if ((heroHitPoints -= attack) <= 0) {
                return false;
            }
        }
        return true;
    }

    private void battleRun() {
        int outcome = rand.nextInt(5) + 1;
        if (outcome != 1 && outcome != 2) {
            JOptionPane.showMessageDialog(null, "You successfulyl evaded the"
                    + " battle!", "VICTORY", JOptionPane.INFORMATION_MESSAGE);
            hero.setCharacterPosition(hero.getPreviousX(), hero.getPreviousY());
            dialogWindow.dispose();
            displayMap();
            battle();
        } else {
            JOptionPane.showMessageDialog(null, "You were unable to evade"
                    + " the battle.\nPrepare to fight!", "UNLUCKY",
                    JOptionPane.ERROR_MESSAGE);
            battleFight(hero, map.getCharacter(hero.getY(), hero.getX()));
        }
    }

    private void dropArtifact(int level) {
        int dropChance = rand.nextInt(6) + 1;
        int option;

        if (dropChance == 1 || dropChance == 2 || dropChance == 3) {
            Armor armor = new Armor(hero);
            Helm helm = new Helm(hero);
            Weapon weapon = new Weapon(hero);

            dialogWindow = new JDialog();
            SelectArtifact panel = new SelectArtifact();

            panel.addPropertyChangeListener(new ArtifactListener());
            dialogWindow.setTitle("Artifact Dropped.");
            dialogWindow.setModal(true);
            dialogWindow.setContentPane(panel);
            dialogWindow.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dialogWindow.pack();
            dialogWindow.setLocationRelativeTo(null);
            dialogWindow.setVisible(true);

        }
    }

    private class ArtifactListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            System.out.println("1");
            if (source == "Equip") {
                if (tempHelm != null) {
                    hero.newHelm(tempHelm);
                    tempHelm = null;
                    tempArmor = null;
                    tempWeapon = null;
                } else if (tempArmor != null) {
                    hero.newArmor(tempArmor);
                    tempHelm = null;
                    tempArmor = null;
                    tempWeapon = null;
                } else if (tempWeapon != null) {
                    hero.newWeapon(tempWeapon);
                    tempHelm = null;
                    tempArmor = null;
                    tempWeapon = null;
                }
                dialogWindow.dispose();
            } else if (source == "Decline") {
                dialogWindow.dispose();
            }
        }
    }

    public void victory() {
        if ((hero.getX() == 0) || (hero.getX() == (map.getSize() - 1)) || (hero.getY() == 0) || (hero.getY() == (map.getSize() - 1))) {
            System.out.println("Level Complete!\n");
            if (checkHeroLevel()) {
                dialogWindow.dispose();
                JOptionPane.showMessageDialog(null, "You have successfully"
                        + " defeated all levels.\nCongratulations! You, "
                        + hero.getName() + ", have ascended to LEGENDARY"
                        + " status!\n", "GAME WON!",
                        JOptionPane.INFORMATION_MESSAGE);
                mainMenuButton.doClick();
            }
            this.hero.levelUp();
            map = new Map(hero);
        }
    }

    private boolean checkHeroLevel() {
        if (this.hero.getLevel() >= this.hero.getMAX_LEVEL()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapScrollPane = new javax.swing.JScrollPane();
        mapDisplayArea = new javax.swing.JTextArea();
        heroStatsDisplay = new javax.swing.JTextArea();
        northButton = new javax.swing.JButton();
        westButton = new javax.swing.JButton();
        eastButton = new javax.swing.JButton();
        southButton = new javax.swing.JButton();
        mainMenuButton = new javax.swing.JButton();
        heroAttributesButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();

        mapDisplayArea.setColumns(20);
        mapDisplayArea.setFont(new java.awt.Font("Courier 10 Pitch", 0, 15)); // NOI18N
        mapDisplayArea.setRows(5);
        mapScrollPane.setViewportView(mapDisplayArea);

        heroStatsDisplay.setColumns(20);
        heroStatsDisplay.setRows(5);
        heroStatsDisplay.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        northButton.setText("North");
        northButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        northButton.setMaximumSize(new java.awt.Dimension(165, 30));
        northButton.setMinimumSize(new java.awt.Dimension(165, 30));
        northButton.setPreferredSize(new java.awt.Dimension(165, 30));
        northButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northButtonActionPerformed(evt);
            }
        });

        westButton.setText("West");
        westButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        westButton.setMaximumSize(new java.awt.Dimension(165, 30));
        westButton.setMinimumSize(new java.awt.Dimension(165, 30));
        westButton.setPreferredSize(new java.awt.Dimension(165, 30));
        westButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westButtonActionPerformed(evt);
            }
        });

        eastButton.setText("East");
        eastButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eastButton.setMaximumSize(new java.awt.Dimension(60, 50));
        eastButton.setMinimumSize(new java.awt.Dimension(60, 50));
        eastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastButtonActionPerformed(evt);
            }
        });

        southButton.setText("South");
        southButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        southButton.setPreferredSize(new java.awt.Dimension(60, 50));
        southButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southButtonActionPerformed(evt);
            }
        });

        mainMenuButton.setText("Main Menu");
        mainMenuButton.setMaximumSize(new java.awt.Dimension(165, 30));
        mainMenuButton.setMinimumSize(new java.awt.Dimension(165, 30));
        mainMenuButton.setPreferredSize(new java.awt.Dimension(165, 30));
        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuButtonActionPerformed(evt);
            }
        });

        heroAttributesButton.setText("Hero Attributes");
        heroAttributesButton.setMaximumSize(new java.awt.Dimension(165, 30));
        heroAttributesButton.setMinimumSize(new java.awt.Dimension(165, 30));
        heroAttributesButton.setPreferredSize(new java.awt.Dimension(165, 30));
        heroAttributesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heroAttributesButtonActionPerformed(evt);
            }
        });

        helpButton.setText("Help");
        helpButton.setPreferredSize(new java.awt.Dimension(165, 30));
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mapScrollPane)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(heroAttributesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(heroStatsDisplay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(westButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(northButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(southButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heroAttributesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(heroStatsDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(northButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(westButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(southButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuButtonActionPerformed
        // TODO add your handling code here:
        View.setHero(hero);
        this.firePropertyChange("MainMenu", null, evt);
    }//GEN-LAST:event_mainMenuButtonActionPerformed

    private void heroAttributesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heroAttributesButtonActionPerformed
        // TODO add your handling code here:
        View.setHero(hero);
        this.firePropertyChange("HeroAttributes", null, evt);
    }//GEN-LAST:event_heroAttributesButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        // TODO add your handling code here:
        View.setHero(hero);
        this.firePropertyChange("Help", null, evt);
    }//GEN-LAST:event_helpButtonActionPerformed

    private void northButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_northButtonActionPerformed
        // TODO add your handling code here:
//        this.firePropertyChange("North", null, evt);
        if (hero.getY() != 0) {
            hero.setPreviousPosition(hero.getX(), hero.getY());
            hero.setY(hero.getY() - 1);
            displayMap();
            battle();
            victory();
        }
    }//GEN-LAST:event_northButtonActionPerformed

    private void southButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_southButtonActionPerformed
        // TODO add your handling code here:
//        this.firePropertyChange("South", null, evt);
        if (hero.getY() != hero.getMapLimit() - 1) {
            hero.setPreviousPosition(hero.getX(), hero.getY());
            hero.setY(hero.getY() + 1);
            displayMap();
            battle();
            victory();
        }
    }//GEN-LAST:event_southButtonActionPerformed

    private void westButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_westButtonActionPerformed
        // TODO add your handling code here:
//        this.firePropertyChange("West", null, evt);
        if (hero.getX() != 0) {
            hero.setPreviousPosition(hero.getX(), hero.getY());
            hero.setX(hero.getX() - 1);
            displayMap();
            battle();
            victory();
        }
    }//GEN-LAST:event_westButtonActionPerformed

    private void eastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eastButtonActionPerformed
        // TODO add your handling code here:
//        this.firePropertyChange("East", null, evt);
        if (hero.getX() != hero.getMapLimit() - 1) {
            hero.setPreviousPosition(hero.getX(), hero.getY());
            hero.setX(hero.getX() + 1);
            displayMap();
            battle();
            victory();
        }
    }//GEN-LAST:event_eastButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eastButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton heroAttributesButton;
    private javax.swing.JTextArea heroStatsDisplay;
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JTextArea mapDisplayArea;
    private javax.swing.JScrollPane mapScrollPane;
    private javax.swing.JButton northButton;
    private javax.swing.JButton southButton;
    private javax.swing.JButton westButton;
    // End of variables declaration//GEN-END:variables
}
