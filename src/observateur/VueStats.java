package observateur;

import javafx.scene.control.Label;
import modele.Jeu;

public class VueStats implements Observateur {
    private Jeu jeu;
    private Label label;

    public VueStats(Jeu jeu, Label label) {
        this.jeu = jeu;
        this.label = label;
        reagir();
    }

    // Met à jour les statistiques
    @Override
    public void reagir() {
        label.setText("Parties gagnées / jouées : " + jeu.getNbGagnees() + "/" + jeu.getNbJouees());
    }
}
