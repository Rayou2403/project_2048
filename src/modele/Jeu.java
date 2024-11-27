package modele;

import ecouteur.EcouteurObjectif;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import observateur.Observateur;

import java.util.*;

public class Jeu {
    private int[][] cases;  // Plateau du jeu
    public int objectif;    // Objectif du jeu
    private List<Observateur> observateurs = new ArrayList<>();
    private int nbGagnees;  // Nombre de parties gagnées
    private int nbJouees;   // Nombre de parties jouées

    public Jeu() {
        this(6);  // Par défaut, un plateau de 6x6
    }

    public Jeu(int taille) {
        cases = new int[taille][taille];
        nbGagnees = 0;
        nbJouees = 0;
        objectif = 2048;  // Objectif par défaut
    }

    // Initialise le plateau avec des valeurs 2 ou 4
    public void nouveau() {
        Random random = new Random();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j] = (random.nextBoolean()) ? 2 : 4;
            }
        }
        nbJouees++;
        notifierObservateurs();
    }

    // Rassemble les cases adjacentes en ligne ou colonne
    public void jouer(int l, int c) {
        int valeur = cases[l][c];
        int somme = valeur;

        for (int i = l - 1; i >= 0 && cases[i][c] == valeur; i--) {
            somme += cases[i][c];
            cases[i][c] = (new Random().nextInt(2) + 1) * 2;
        }
        for (int i = l + 1; i < cases.length && cases[i][c] == valeur; i++) {
            somme += cases[i][c];
            cases[i][c] = (new Random().nextInt(2) + 1) * 2;
        }
        for (int j = c - 1; j >= 0 && cases[l][j] == valeur; j--) {
            somme += cases[l][j];
            cases[l][j] = (new Random().nextInt(2) + 1) * 2;
        }
        for (int j = c + 1; j < cases[0].length && cases[l][j] == valeur; j++) {
            somme += cases[l][j];
            cases[l][j] = (new Random().nextInt(2) + 1) * 2;
        }

        cases[l][c] = somme;
        notifierObservateurs();

        if (cases[l][c] >= objectif) {
            nbGagnees++;
            afficherPopupVictoire();
        } else if (!peutEncoreJouer()) {
            afficherPopupDefaite();
        }

        }


    private boolean peutEncoreJouer() {
        // Check each cell for potential moves
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                int valeur = cases[i][j];
                // Check if there are adjacent cells with the same value
                if ((i > 0 && cases[i - 1][j] == valeur) ||
                        (i < cases.length - 1 && cases[i + 1][j] == valeur) ||
                        (j > 0 && cases[i][j - 1] == valeur) ||
                        (j < cases[i].length - 1 && cases[i][j + 1] == valeur)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void afficherPopupVictoire() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Victoire !");
        alert.setHeaderText("Félicitations, vous avez atteint l'objectif !");
        alert.setContentText("Souhaitez-vous relancer une nouvelle partie ou quitter ?");

        ButtonType relancerButton = new ButtonType("Relancer");
        ButtonType quitterButton = new ButtonType("Quitter");

        alert.getButtonTypes().setAll(relancerButton, quitterButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == relancerButton) {
            nouveau();
        } else if (result.isPresent() && result.get() == quitterButton) {
            Platform.exit();
        }
    }


    private void afficherPopupDefaite() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Défaite");
        alert.setHeaderText("Vous n'avez plus de mouvements possibles !");
        alert.setContentText("Souhaitez-vous relancer une nouvelle partie ou quitter ?");

        ButtonType relancerButton = new ButtonType("Relancer");
        ButtonType quitterButton = new ButtonType("Quitter");

        alert.getButtonTypes().setAll(relancerButton, quitterButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == relancerButton) {
            nouveau();
        } else if (result.isPresent() && result.get() == quitterButton) {
            Platform.exit();
        }
    }

    public void ajouterObservateur(Observateur obs) {
        observateurs.add(obs);
    }

    public void notifierObservateurs() {
        for (Observateur obs : observateurs) {
            obs.reagir();
        }
    }

    public int size() {
        return cases.length;
    }

    public int getCase(int l, int c) {
        if (l >= 0 && l < cases.length && c >= 0 && c < cases[0].length) {
            return cases[l][c];
        }
        return -1;
    }

    public int getNbGagnees() {
        return nbGagnees;
    }

    public int getNbJouees() {
        return (nbJouees - 1);
    }

    public void setTaille(int taille) {
        cases = new int[taille][taille];
        notifierObservateurs();
    }

    public void setObjectif(int objectif) {
        this.objectif = objectif;
        notifierObservateurs();
    }
}
