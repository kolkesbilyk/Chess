import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public interface ChessFigure {
    Image getImageFigure();
    int getXp();
    int getYp();
    boolean getIsWhite();
    void setXp(int x);
    void setYp(int y);
    void move(int x, int y);
    void kill();
    int[] getCoord();
    int[] getLastCoord();
    void setLastCoord(int x, int y);

}
