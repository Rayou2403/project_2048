package observateur;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import modele.Jeu;
import ecouteur.EcouteurCase;

public class VuePlateau implements Observateur {
    private Jeu jeu;
    private GridPane gridPane;

    public VuePlateau(Jeu jeu, GridPane gridPane) {
        this.jeu = jeu;
        this.gridPane = gridPane;
    }

    // Met à jour l'affichage lorsque le modèle change
    @Override
    public void reagir() {
        gridPane.getChildren().clear();
        for (int i = 0; i < jeu.size(); i++) {
            for (int j = 0; j < jeu.size(); j++) {
                int value = jeu.getCase(i, j);
                Label caseLabel = new Label(value == 0 ? "" : Integer.toString(value));
                caseLabel.setFont(new Font(24));
                caseLabel.setMinSize(100, 100);

                // Calcul de la couleur évolutive directement dans reagir
                String color;
                if (value <= 0) {
                    color = "white";
                } else {
                    int intensity = Math.min((int) (Math.log(value) / Math.log(2)) * 20, 255);
                    int red = 238 - intensity;
                    int green = 228 - intensity / 2;
                    int blue = 218 - intensity / 3;
                    color = String.format("#%02x%02x%02x", red, green, blue);
                }

                caseLabel.setStyle("-fx-border-color: black; -fx-alignment: center; " +
                        "-fx-background-color: " + color + ";");

                // Ajout de l'écouteur pour les clics de souris
                caseLabel.setOnMouseClicked(new EcouteurCase(i, j, jeu));

                gridPane.add(caseLabel, j, i);
            }
        }
    }

}
