package ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modele.Jeu;

public class EcouteurCase implements EventHandler<MouseEvent> {
    private int lig;
    private int col;
    private Jeu jeu;

    public EcouteurCase(int lig, int col, Jeu jeu) {
        this.lig = lig;
        this.col = col;
        this.jeu = jeu;
    }

    @Override
    public void handle(MouseEvent e) {
        jeu.jouer(lig, col);
    }
}
