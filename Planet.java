import javafx.event.EventHandler;
import javafx.scene.shape.Circle;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.input.MouseEvent;

/**
 * Planet class responsible for Planet objects which Circle shapes.
 * Class includes methods for movement and geometry
 */

class Planet {
    private Circle planet = new Circle();
    private int radius;
    private String audio;
    private Color fillColor;
    Planet(int radius, Color fillColor) {
        planet.setFill(fillColor);
        planet.setRadius(radius);
        this.radius = radius;
        this.fillColor = fillColor;
    }

    void onMouseEntered() {
         planet.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent h) {
                planet.setRadius(radius * 1.5);
            }
        });
    }

    void onMouseExit() {
         planet.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent h) {
                planet.setRadius(radius);
            }
        });
    }

    void onMouseClicked() {
         planet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent h) {
                if (audio != null) {
                    playSound();
                }
            }
        });
    }

    void playSound() {
        AudioClip sound = new AudioClip(this.getClass().getResource(audio).toString());
        assert(sound != null);
        sound.play();
    }

    void setAudio(String audio) {
        assert(audio != null);
        this.audio = audio;
    }

    // Placed in game loop to calculate the orbit of a planet around a set point
    void orbit(double orbitCenterX, double orbitCenterY, int orbitRadius, double speedModifier, double time) {
        planet.setCenterX(orbitCenterX + (orbitRadius * Math.cos(time * speedModifier)));
        planet.setCenterY(orbitCenterY + (orbitRadius * Math.sin(time * speedModifier)));
    }

    Circle getNode() {
        return planet;
    }

    int getRadius() {
        return radius;
    }
    
    Color getColor() {
        return fillColor;
    }
}