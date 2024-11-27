import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import modele.*;
import observateur.*;
import ecouteur.*;


public class   Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Initialisation du jeu
        Jeu jeu = new Jeu();

        // Configuration de la grille de plateau et vue associée
        GridPane plateauGrid = new GridPane();
        plateauGrid.setAlignment(Pos.CENTER); // Centre la grille elle-même
        VuePlateau vuePlateau = new VuePlateau(jeu, plateauGrid);

        // Initialisation des statistiques et vue associée
        Label statsLabel = new Label();
        VueStats vueStats = new VueStats(jeu, statsLabel);

        // Initialisation de la barre de menu et vue associée
        MenuBar menuBar = new MenuBar();
        VueMenu vueMenu = new VueMenu(jeu, menuBar);
        vueMenu.reagir();

        // Ajout des observateurs pour le jeu
        jeu.ajouterObservateur(vuePlateau);
        jeu.ajouterObservateur(vueStats);

        // Bouton de nouvelle partie avec écouteur
        Button nouvellePartie = new Button("Nouvelle Partie");
        nouvellePartie.setOnAction(new EcouteurNouveau(jeu));

        // Structure de la scène principale
        BorderPane root = new BorderPane();
        VBox topContainer = new VBox(menuBar, statsLabel);
        topContainer.setAlignment(Pos.CENTER); // Centre le menu et le label de stats en haut
        root.setTop(topContainer);

        HBox centerContainer = new HBox(plateauGrid);
        centerContainer.setAlignment(Pos.CENTER); // Centre le plateau au milieu
        root.setCenter(centerContainer);

        HBox buttonBox = new HBox(nouvellePartie);
        buttonBox.setAlignment(Pos.CENTER); // Centre le bouton en bas
        root.setBottom(buttonBox);

        // Création et configuration de la scène
        Scene scene = new Scene(root, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jeu 2048");
        primaryStage.setMaximized(true);
        primaryStage.show();

        // Démarrage de la première partie
        jeu.nouveau();

        // Mise à jour initiale de tous les observateurs
        jeu.notifierObservateurs();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
