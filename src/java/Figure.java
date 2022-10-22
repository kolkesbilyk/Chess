import java.awt.*;
import java.util.Map;

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
        this.xPosition = x;
    }
    public int getYPosition() {
        return yPosition;
    }
    public void setYPosition(int y) {
        this.yPosition = y;
    }
    @Override
    public boolean getIsWhite() {
        return this.isWhite;
    }

    @Override
    public void moveFigure(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items){
        boolean isFigurePresentBetweenPosition = isFigurePresentBetweenPosition(newXPosition, newYPosition, lastXPosition, lastYPosition, items);
        if (newXPosition < 8 && newXPosition >= 0 && newYPosition >= 0 && newYPosition < 8){
            move(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
        }
    }
    public void move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        setXPosition(newXPosition);
        setYPosition(newYPosition);
    }

    @Override
    public CoordOnField getCoordOnField() {
        return coordOnField;
    }

    @Override
    public void setCoordOnField(int xPosition, int yPosition) {
        this.coordOnField = new CoordOnField(xPosition, yPosition);
    }

    public abstract boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items);
}
