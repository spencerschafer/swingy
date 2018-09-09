/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui;

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
    JFrame frame;
    MainMenu mainMenu;
    StartGame startGame;
    LoadCharacter loadCharacter;
    SwitchView switchView;
    
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        
        frame = new JFrame();
        frame.addPropertyChangeListener(new MainMenuListener());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double x = (double) screenSize.getWidth();
        double y = (double) screenSize.getHeight();
        
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
            if (source == "StartGame") {
                removeMainMenu();
                newStartGame();
                
            }else if (source == "LoadCharacter") {
                removeMainMenu();
                newLoadCharacter();
              
            }else if (source == "SwitchView") {
                removeMainMenu();
                newSwitchView();

            }else if (source == "Exit") {
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
            }
        }
    }
    
    private class LoadCharacterListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            String source = pce.getPropertyName();
            if (source == "Back") {
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
}
