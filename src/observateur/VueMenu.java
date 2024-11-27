package observateur;

import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import modele.Jeu;

public class VueMenu implements Observateur {
    private MenuBar menuBar;
    private Jeu jeu;

    public VueMenu(Jeu jeu, MenuBar menuBar) {
        this.jeu = jeu;
        this.menuBar = menuBar;
    }

    @Override
    public void reagir() {

        Menu menu = new Menu("Jeu");

        // Nouveau jeu
        MenuItem newGameItem = new MenuItem("Nouveau");
        newGameItem.setOnAction(e -> jeu.nouveau());
        newGameItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        // Taille
        MenuItem tailleItem = new MenuItem("Taille : " + jeu.size());
        tailleItem.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(String.valueOf(jeu.size()));
            dialog.setTitle("Changer la taille du plateau");
            dialog.setHeaderText("Entrez la nouvelle taille pour le plateau de jeu :");
            dialog.setContentText("Taille:");

            dialog.showAndWait().ifPresent(newSize -> {
                try {
                    int taille = Integer.parseInt(newSize);
                    jeu.setTaille(taille);  // Met à jour la taille du jeu
                    tailleItem.setText("Taille : " + jeu.size());  // Met à jour l'affichage
                    jeu.nouveau();  // Démarre une nouvelle partie avec la nouvelle taille
                } catch (NumberFormatException ex) {
                    System.out.println("Veuillez entrer un nombre valide pour la taille.");
                }
            });
        });

        // Objectif
        MenuItem objectifItem = new MenuItem("Objectif : " + jeu.objectif);
        objectifItem.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(String.valueOf(jeu.objectif));
            dialog.setTitle("Changer l'objectif du jeu");
            dialog.setHeaderText("Entrez le nouvel objectif pour le jeu :");
            dialog.setContentText("Objectif:");

            dialog.showAndWait().ifPresent(newGoal -> {
                try {
                    int objectif = Integer.parseInt(newGoal);
                    jeu.setObjectif(objectif);  // Met à jour l'objectif du jeu
                    objectifItem.setText("Objectif : " + jeu.objectif);  // Met à jour l'affichage
                } catch (NumberFormatException ex) {
                    System.out.println("Veuillez entrer un nombre valide pour l'objectif.");
                }
            });
        });

        // Quitter le jeu
        MenuItem quitItem = new MenuItem("Quitter");
        quitItem.setOnAction(e -> System.exit(0));
        quitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));

        // Ajout des éléments au menu
        menu.getItems().addAll(newGameItem, tailleItem, objectifItem, quitItem);

        menuBar.getMenus().add(menu);
    }
}