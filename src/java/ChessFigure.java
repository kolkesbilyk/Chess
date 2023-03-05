import java.awt.*;
import java.util.List;

public interface ChessFigure {
    Image getImageFigure();
    int getXPosition();
    int getYPosition();
    FigureColor getFigureColor();
    void setXPosition(int x);
    void setYPosition(int y);
    boolean moveFigure(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, List<ChessFigure> items, List<ChessFigure> selectFigures);
    boolean canKill(int newXPosition,
                    int newYPosition,
                    int lastXPosition,
                    int lastYPosition);
}
