/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui;

import Controller.Main;
import Model.Characters.*;
import Model.Map;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author spencer
 */
public class View {

    private static Map map;
    private static Hero hero;
    private JFrame frame;
    private MainMenu mainMenu;
    private StartGame startGame;
    private CreateHero createHero;
    private LoadCharacter loadCharacter;
    private SwitchView switchView;

    public View() {
        setVariablesNull();
        initialiseView();
    }

    public View(Map map, Hero hero) {
        setVariablesNull();
        System.out.println("@1");
        this.hero = hero;
        this.map = map;

        initialiseView();
    }

    public void initialiseView() {
        System.out.println("@2");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        frame = new JFrame();
        frame.addPropertyChangeListener(new MainMenuListener());

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double x = (double) screenSize.getWidth();
//        double y = (double) screenSize.getHeight();
        mainMenu = new MainMenu();
        mainMenu.addPropertyChangeListener(new MainMenuListener());

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.add(mainMenu);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("@3");
    }

    private class MainMenuListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source == "StartGame") {
                if (hero == null) {
                    JOptionPane.showMessageDialog(null, "No Hero Selected.",
                            "WARNING", JOptionPane.ERROR_MESSAGE);
                } else {
                    removeMainMenu();
                    newStartGame();
                }
            } else if (source == "CreateHero") {
                removeMainMenu();
                newCreateHero();
            } else if (source == "LoadCharacter") {
                removeMainMenu();
                newLoadCharacter();
            } else if (source == "SwitchView") {
                removeMainMenu();
                newSwitchView();
            } else if (source == "Exit") {
                System.exit(0);
            }
        }
    }

    private class StartGameListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source == "MainMenu") {
                System.out.println("START");
                removeStartGame();
                newMainMenu();
                System.out.println("END");
            } else if (source == "HeroAttributes") {
                displayHeroAttributes();
            } else if (source == "Help") {
                displayHelp();
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
                Main.changeViewConsole(View.hero);
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
        startGame = new StartGame();
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
                    + "- - - - - - - - - - - - - - - -"
                    + "\nName:   " + hero.getName()
                    + "\nClass:    " + hero.getType()
                    + "\nLevel:    " + hero.getLevel()
                    + "\nEXP:       " + hero.getExperience()
                    + "\nATT:       " + hero.getAttack()
                    + "\nDEF:      " + hero.getDefense()
                    + "\nHP:         " + hero.getHitPoints()
                    + "\n\nHero Artifacts"
                    + "\n- - - - - - - - - - - - - -"
                    + "\nHelmet:    " + hero.getHelmet().getHitPoints() + " HP"
                    + "\nArmor:      " + hero.getArmor().getDefense() + " DEF"
                    + "\nWeapon:  " + hero.getWeapon().getAttack() + " ATT\n",
                    "Help", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayHelp() {
        if (hero != null) {
            JOptionPane.showMessageDialog(null,
                    "Controls\n"
                    + "- - - - - - - - -\n"
                    + "North -  Up\n"
                    + "West   -  Left\n"
                    + "South -  Down\n"
                    + "East     -  Right\n\n"
                    + "Map Key\n"
                    + "- - - - - - - - -\n"
                    + "o  -  Hero\n"
                    + "x   -  Villain\n"
                    + ".   -  Empty\n",
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

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        View.map = map;
    }

    public static Hero getHero() {
        return hero;
    }

    public static void setHero(Hero hero) {
        View.hero = hero;
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

    private void setVariablesNull() {
        map = null;
        hero = null;
        frame = null;
        mainMenu = null;
        startGame = null;
        createHero = null;
        loadCharacter = null;
        switchView = null;
    }
}
