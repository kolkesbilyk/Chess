import java.awt.*;
import java.util.Arrays;

public abstract class Figure implements ChessFigure {
    private int xPosition;
    private int yPosition;
    private boolean isWhite;
    CoordOnField coordOnField;

    public Figure(int x, int y, boolean isWhite) {
        this.xPosition = x;
        this.yPosition = y;
        this.isWhite = isWhite;
        this.coordOnField = new CoordOnField(x, y);
    }

    public abstract Image getImageFigure();

    public int getXPosition() {
        return xPosition;
    }
    public void setXPosition(int x) {
        this.xPosition = x / 64;
    }
    public int getYPosition() {
        return yPosition;
    }
    public void setYPosition(int y) {
        this.yPosition = y / 64;
    }
    @Override
    public boolean getIsWhite() {
        return this.isWhite;
    }
    @Override
    public void move(int x, int y) {

    }

    @Override
    public CoordOnField getCoordOnField() {
        return coordOnField;
    }

    @Override
    public void setCoordOnField(int xPosition, int yPosition) {
        this.coordOnField = new CoordOnField(xPosition, yPosition);
    }

}
