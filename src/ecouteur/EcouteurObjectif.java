package ecouteur;

import javafx.event.Event;
import javafx.event.EventHandler;
import modele.Jeu;

public class EcouteurObjectif implements EventHandler<Event> {
    private Jeu jeu;
    private int nouvelObjectif;

    public EcouteurObjectif(Jeu jeu, int nouvelObjectif) {
        this.jeu = jeu;
        this.nouvelObjectif = nouvelObjectif;
    }

    @Override
    public void handle(Event e) {
        jeu.setObjectif(nouvelObjectif);
    }
}