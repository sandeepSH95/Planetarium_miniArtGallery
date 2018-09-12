import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;

/**
 * Class responsible for block squares that are used to trigger planets
 * that make contact to play their sound.
 * Methods for dragging and dropping the blocks are included.
 */

class PlayBlock {

    private double initSceneX, initSceneY;
    private double initTranslateX, initTranslateY;
    private Rectangle block;
    
    private String audio;

    PlayBlock(double positionX, double positionY, double width, double height, Color fillColor) {
        block = new Rectangle(width, height, fillColor);
        block.setCursor(Cursor.MOVE);
        block.setX(positionX);
        block.setY(positionY);
    }

    void onMousePressed() {
        block.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                initSceneX = t.getSceneX();
                initSceneY = t.getSceneY();
                initTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
                initTranslateY = ((Rectangle)(t.getSource())).getTranslateY();
            }   
        });
    }
    void onMouseDragged() {
        block.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                double offsetX = t.getSceneX() - initSceneX;
                double offsetY = t.getSceneY() - initSceneY;
                double newTranslateX = initTranslateX + offsetX;
                double newTranslateY = initTranslateY + offsetY;
                ((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
                ((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
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
    Rectangle getNode() {
        return block;
    }
}