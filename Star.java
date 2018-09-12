/**
 * Star class containing star shape and methods to animate for a SPARKLE effect
 */

import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;;


class Star {
    private Rectangle star = new Rectangle(1, 1, Color.WHITE);
    private int sparkly;
    //sparke states
    final double SPARKLE = 3;
    final double DARKER = 2;
    final double RESET = 1;
    
    Star(double x, double y) {
        sparkly = 0;
        star.setX(x);
        star.setY(y);
    }

    void sparkle() {
        star.setScaleX(SPARKLE);
        star.setScaleY(SPARKLE);
        sparkly = 2;
    }

    void darker() {
        star.setScaleX(DARKER);
        star.setScaleY(DARKER);
        sparkly = 1;
    }

    void reset() {
        star.setScaleX(RESET);
        star.setScaleY(RESET);
        sparkly = 0;
    }

    int isSparkly() {
        return sparkly;
    }

    Rectangle getNode() {
        return star;
    }

    double getXscale() {
        return star.getScaleX();
    }

    double getYscale() {
        return star.getScaleY();
    }

}