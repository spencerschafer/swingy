/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Main;
import Model.Characters.*;
import Model.Map;
import Panels.CreateHero;
import Panels.LoadCharacter;
import Panels.MainMenu;
import Panels.StartGame;
import Panels.SwitchView;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author spencer
 */
public class GuiView {

    private Map map;
    private Hero hero;
    private JFrame frame;
    private MainMenu mainMenu;
    private StartGame startGame;
    private CreateHero createHero;
    private LoadCharacter loadCharacter;
    private SwitchView switchView;

    public GuiView() {
        initialiseView();
    }

    public GuiView(Map map, Hero hero) {
        this.hero = hero;
        this.map = map;

        initialiseView();
    }

    public void initialiseView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", new Font("Courier", Font.PLAIN, 13));
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        frame = new JFrame();
        frame.addPropertyChangeListener(new MainMenuListener());

        mainMenu = new MainMenu();
        mainMenu.addPropertyChangeListener(new MainMenuListener());

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.add(mainMenu);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class MainMenuListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source.equals("StartGame")) {
                if (hero == null) {
                    JOptionPane.showMessageDialog(null, "No Hero Selected.",
                            "WARNING", JOptionPane.ERROR_MESSAGE);
                } else {
                    removeMainMenu();
                    newStartGame();
                }
            } else if (source.equals("CreateHero")) {
                removeMainMenu();
                newCreateHero();
            } else if (source.equals("LoadCharacter")) {
                removeMainMenu();
                newLoadCharacter();
            } else if (source.equals("SwitchView")) {
                removeMainMenu();
                newSwitchView();
            } else if (source.equals("Exit")) {
                System.exit(0);
            }
        }
    }

    private class StartGameListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source == "MainMenu") {
                removeStartGame();
                newMainMenu();
            } else if (source == "HeroAttributes") {
                displayHeroAttributes();
            } else if (source == "Help") {
                displayHelp();
            } else if (source.equals("Defeat")) {
                removeStartGame();
                newMainMenu();
            } else if (source.equals("Victory")) {
                JOptionPane.showMessageDialog(null, "You have successfully"
                        + " defeated all levels.\nCongratulations! You, "
                        + hero.getName() + ", have ascended to LEGENDARY"
                        + " status!\n", "GAME WON!",
                        JOptionPane.INFORMATION_MESSAGE);
                hero = startGame.getHero();
                removeStartGame();
                newMainMenu();
            }
        }
    }

    private class CreateHeroListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source == "Default") {
                JOptionPane.showMessageDialog(null, "No Class Selected.",
                        "WARNING", JOptionPane.ERROR_MESSAGE);
            } else if (source == "Load") {
                createHero();
                JOptionPane.showMessageDialog(null, "Hero '"
                        + hero.getName() + "' Created.", "CREATED",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (source == "nullName") {
                JOptionPane.showMessageDialog(null, "No Name Entered.",
                        "WARNING", JOptionPane.ERROR_MESSAGE);
            } else if (source == "nullType") {
                JOptionPane.showMessageDialog(null, "No Class Selected.",
                        "WARNING", JOptionPane.ERROR_MESSAGE);
            } else if (source == "Back") {
                removeCreateHero();
                newMainMenu();
            }
        }
    }

    private class LoadCharacterListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source == "Load") {
                loadHero();
                JOptionPane.showMessageDialog(null, "Hero '"
                        + hero.getName() + "' selected.", "LOADED",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (source == "Default") {
                JOptionPane.showMessageDialog(null, "No Hero Selected.",
                        "WARNING", JOptionPane.ERROR_MESSAGE);
            } else if (source == "Back") {
                removeLoadCharacter();
                newMainMenu();
            }
        }
    }

    private class SwitchViewListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source == "Back") {
                removeSwitchView();
                newMainMenu();
            } else if (source == "ConsoleView") {
                Main.setMap(map);
                Main.setHero(hero);
                Main.closeGui();
            }
        }
    }

    private void newMainMenu() {
        mainMenu = new MainMenu();
        mainMenu.addPropertyChangeListener(new MainMenuListener());

        frame.add(mainMenu);
        frame.pack();
    }

    private void removeMainMenu() {
        frame.remove(mainMenu);
        mainMenu = null;
    }

    private void newStartGame() {
        startGame = new StartGame(hero);
        startGame.addPropertyChangeListener(new StartGameListener());

        frame.add(startGame);
        frame.pack();
    }

    private void removeStartGame() {
        frame.remove(startGame);
        startGame = null;
    }

    private void newCreateHero() {
        createHero = new CreateHero();
        createHero.addPropertyChangeListener(new CreateHeroListener());

        frame.add(createHero);
        frame.pack();
    }

    private void removeCreateHero() {
        frame.remove(createHero);
        createHero = null;
    }

    private void newLoadCharacter() {
        loadCharacter = new LoadCharacter();
        loadCharacter.addPropertyChangeListener(new LoadCharacterListener());

        frame.add(loadCharacter);
        frame.pack();
    }

    private void removeLoadCharacter() {
        frame.remove(loadCharacter);
        loadCharacter = null;
    }

    private void newSwitchView() {
        switchView = new SwitchView();
        switchView.addPropertyChangeListener(new SwitchViewListener());

        frame.add(switchView);
        frame.pack();
    }

    private void removeSwitchView() {
        frame.remove(switchView);
        switchView = null;
    }

    private void displayHeroAttributes() {
        if (hero != null) {
            JOptionPane.showMessageDialog(null,
                    "Hero Attributes\n"
                    + "- - - - - - - - -\n"
                    + "Name:   " + hero.getName() + "\n"
                    + "Class:  " + hero.getType() + "\n"
                    + "Level:  " + hero.getLevel() + "\n"
                    + "EXP:    " + hero.getExperience() + "\n"
                    + "ATT:    " + hero.getAttack() + "\n"
                    + "DEF:    " + hero.getDefense() + "\n"
                    + "HP:     " + hero.getHitPoints() + "\n\n"
                    + "Hero Artifacts\n"
                    + "- - - - - - - -\n"
                    + "Helmet: " + hero.getHelmet().getHitPoints() + " HP\n"
                    + "Armor:  " + hero.getArmor().getDefense() + " DEF\n"
                    + "Weapon: " + hero.getWeapon().getAttack() + " ATT\n",
                    "Help", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayHelp() {
        if (hero != null) {
            JOptionPane.showMessageDialog(null,
                    "Controls\n"
                    + "- - - - -\n"
                    + "North -  Up\n"
                    + "West  -  Left\n"
                    + "South -  Down\n"
                    + "East  -  Right\n\n"
                    + "Map Key\n"
                    + "- - - - -\n"
                    + "o     -  Hero\n"
                    + "x     -  Villain\n"
                    + ".     -  Empty\n",
                    "Help", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void loadHero() {
        Hero temp = LoadCharacter.getHero();
        if (temp != null) {
            hero = temp;
            map = new Map(hero);
        }
    }

    private void createHero() {
        Hero temp = CreateHero.getHero();
        if (temp != null) {
            hero = temp;
            map = new Map(hero);
        }
    }

    public void restartGame() {
        removeStartGame();
        newMainMenu();
    }

    public void closeFrame() {
        frame.dispose();
        frame.setVisible(false);
        frame = null;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
