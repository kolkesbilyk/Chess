import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public interface ChessFigure {
    Image getImageFigure();
    int getXPosition();
    int getYPosition();
    boolean getIsWhite();
    void setXPosition(int x);
    void setYPosition(int y);
    void move(int x, int y);
    CoordOnField getCoordOnField();
    void setCoordOnField(int x, int y);


}
