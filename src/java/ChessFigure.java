import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface ChessFigure {
    Image getImageFigure();
    int getXPosition();
    int getYPosition();
    boolean getIsWhite();
    void setXPosition(int x);
    void setYPosition(int y);
    void moveFigure(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items);
    CoordOnField getCoordOnField();
    void setCoordOnField(int x, int y);
}
