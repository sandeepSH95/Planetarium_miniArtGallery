import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * Main class for the Planetarium application.
 * Contains initialised objects for display.
 * Initialises mousevents.
 * Contains AnimationTImer loop for animation.
 */

public class Planetarium extends Application {

    //Initialise random object and variables needed for star field
    private Random rand = new Random();
    final int NUMBER_OF_STARS = 800;
    // number of sparkes is the amount of stars changed in state per 1/60 second
    final int NUMBER_OF_SPARKLES = 1;
    int[] randSparkles = new int[NUMBER_OF_SPARKLES];

    // Initialise Planet objects
    // NOTE: planets must also be added to "planetList" ArrayList
    private Planet p = new Planet(30, Color.INDIGO);
    private Planet pOrbit = new Planet(10, Color.YELLOW);

    private Planet p1 = new Planet(40, Color.RED);
    private Planet p1Orbit = new Planet(10, Color.WHITE);
    private Planet p1OrbitOrbit = new Planet(5, Color.ORANGE);
    
    private Planet p2 = new Planet(15, Color.AZURE);

    private Planet p3 = new Planet(50, Color.BLUEVIOLET);
    private Planet p3Orbit = new Planet(3, Color.AQUAMARINE);
    private Planet p3Orbit1 = new Planet(4, Color.ALICEBLUE);
    private Planet p3Orbit2 = new Planet(2, Color.BISQUE);

    private Planet p4 = new Planet(90, Color.BEIGE);

    //Initialise planet and Star arraylists
    private ArrayList<Planet> planetList = new ArrayList<Planet>();
    private ArrayList<Star> starList = new ArrayList<Star>();

    // Initilise PlayBlocks
    PlayBlock pb = new PlayBlock(0, 210, 50, 50, Color.BISQUE);
    PlayBlock pb1 = new PlayBlock(0, 310, 80, 80, Color.GOLD);
    PlayBlock pb2 = new PlayBlock(0, 440, 50, 50, Color.ROYALBLUE);


    @Override
    public void start(Stage stage) {

        //Initialise the Pane
        Pane root = new Pane();
        Canvas canvas = new Canvas(1280, 720);

        // Initialise testing if assertions are on
        boolean testing = false;
        assert(testing = true);
        if (testing) {
            test();
        } else {

            //Initialise stars in starfield
            //Add stars to ArrayList
            for (int i = 0; i < NUMBER_OF_STARS; i++) {
                Star s = new Star(rand.nextDouble() * canvas.getWidth(), rand.nextDouble() * canvas.getHeight());
                starList.add(s);
            }
            //Initialise stars on pane
            for (int i = 0; i < NUMBER_OF_STARS; i++) {
                root.getChildren().add(starList.get(i).getNode());
            }

            //Add Planet Objects to planetList
            planetList.add(p);
            planetList.add(pOrbit);

            planetList.add(p1);
            planetList.add(p1Orbit);
            planetList.add(p1OrbitOrbit);

            planetList.add(p2);

            planetList.add(p3);
            planetList.add(p3Orbit);
            planetList.add(p3Orbit1);
            planetList.add(p3Orbit2);

            planetList.add(p4);

            //Initialise Sounds
            p.setAudio("/audio/p.wav");
            pOrbit.setAudio("/audio/pOrbit.wav");
            p1.setAudio("/audio/p1.wav");
            p1Orbit.setAudio("/audio/p1Orbit.wav");
            p1OrbitOrbit.setAudio("/audio/p1OrbitOrbit.wav");
            p2.setAudio("/audio/p2.wav");
            p3.setAudio("/audio/p3.wav");
            p3Orbit.setAudio("/audio/p3Orbit.wav");
            p3Orbit1.setAudio("/audio/p3Orbit1.wav");
            p3Orbit2.setAudio("/audio/p3Orbit2.wav");
            p4.setAudio("/audio/p4.wav");

            //Initialise Mouse Events
            //Planets
            for (int i = 0; i < planetList.size(); i++) {
                planetList.get(i).getNode().setCursor(Cursor.HAND);
                planetList.get(i).onMouseEntered();
                planetList.get(i).onMouseExit();
                planetList.get(i).onMouseClicked();
            }
            //PlayBlocks
            pb.onMousePressed();
            pb.onMouseDragged();
            pb1.onMousePressed();
            pb1.onMouseDragged();
            pb2.onMousePressed();
            pb2.onMouseDragged();

            //Initialise Images
            Image astronaut = new Image("/image/singularity.gif");
            ImageView imageView = new ImageView(astronaut);
            // 50 is half the width/height of the image
            imageView.setLayoutX((canvas.getWidth()/2)-50);
            imageView.setLayoutY((canvas.getHeight()/2)-50);

            // Add child nodes to root
            //Planets
            for (int i = 0; i < planetList.size(); i++) {
                root.getChildren().add(planetList.get(i).getNode());
            }
            //Images
            root.getChildren().add(imageView);
            //PlayBlocks
            root.getChildren().add(pb.getNode());
            root.getChildren().add(pb1.getNode());
            root.getChildren().add(pb2.getNode());

            // Set background color
            root.setStyle("-fx-background-color: black");
            Scene scene = new Scene(root, 1280, 720);
            // Initialise the window and scene
            stage.setTitle("Planetarium");
            stage.setScene(scene);
            stage.show();
            timer.start();
        }
    }

    // Animation/Game loop
    final long initNanoTime = System.nanoTime();
    AnimationTimer timer = new AnimationTimer() {
        public void handle(long now) {
            double time = (now - initNanoTime) / 1000000000.0;
            // carry out game loop functions
            onUpdate(time);
        }
    };

    // What happens when the game loop updates
    private void onUpdate(double time) {
        // Calculate Orbits
        //NOTE: all values are set to taste, depending on what looks the best
        p.orbit(640, 360, 100, 1.0, time);
        pOrbit.orbit(p.getNode().getCenterX(), p.getNode().getCenterY(), 50, 2.0, time);

        p1.orbit(640, 360, 280, 0.3, time);
        p1Orbit.orbit(p1.getNode().getCenterX(), p1.getNode().getCenterY(), 80, 1.5, time);
        p1OrbitOrbit.orbit(p1Orbit.getNode().getCenterX(), p1Orbit.getNode().getCenterY(), 25, 4.0, time);

        p2.orbit(640, 360, 150, 0.8, time);

        p3.orbit(640, 360, 400, 0.1, time);
        p3Orbit.orbit(p3.getNode().getCenterX(), p3.getNode().getCenterY(), 65, 0.5, time);
        p3Orbit1.orbit(p3.getNode().getCenterX(), p3.getNode().getCenterY(), 60, 1.0, time);
        p3Orbit2.orbit(p3.getNode().getCenterX(), p3.getNode().getCenterY(), 55, 1.6, time);

        p4.orbit(640, 360, 600, 0.05, time);

        //Randomly sparkle stars
        for (int i = 0; i < NUMBER_OF_SPARKLES; i++) {
            randSparkles[i] = rand.nextInt(NUMBER_OF_STARS - 1);
        }
        for (int i = 0; i < NUMBER_OF_SPARKLES; i++) {
            int isSparkly = starList.get(randSparkles[i]).isSparkly();
            if (isSparkly == 0) {
                starList.get(randSparkles[i]).darker();
            } else if (isSparkly == 2){
                starList.get(randSparkles[i]).reset();
            } else {
                starList.get(randSparkles[i]).sparkle();
            }
        }

    }

    //Test Class called when assertions are enabled
    // NOTE: Assertions are present in other classes and methods
    void test() {
        //Planet Testing
        Planet planet = new Planet(10, Color.ALICEBLUE);
        assert(planet.getNode() != null);
        // audio assignment testing. NOTE: assertion in in the Planet class
        planet.setAudio("/audio/p.wav");
        planet.playSound();
        assert(planet.getRadius() == 10);
        assert(planet.getColor() == Color.ALICEBLUE);

        //PlayBlock testing
        PlayBlock playBlock = new PlayBlock(0, 0, 10, 10, Color.AZURE);
        assert(playBlock.getNode() != null);
        playBlock.setAudio("/audio/p.wav");
        playBlock.playSound();

        //Star testing
        Star star = new Star(10.0, 10.0);
        assert(star.getNode() != null);
        assert(star.getXscale() == 1);
        assert(star.getYscale() == 1);
        star.sparkle();
        assert(star.getXscale() == 3);
        assert(star.getYscale() == 3);
        star.darker();
        assert(star.getXscale() == 2);
        assert(star.getYscale() == 2);
        star.reset();
        assert(star.getXscale() == 1);
        assert(star.getYscale() == 1);

        System.exit(1);
    }
}