package ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.Jeu;

public class EcouteurNouveau implements EventHandler<ActionEvent> {
    private Jeu jeu;

    public EcouteurNouveau(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void handle(ActionEvent event) {
        jeu.nouveau();
    }
}
