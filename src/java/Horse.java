import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Horse extends Figure{

    public Horse(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public boolean canKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition) {
        return false;
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.HORSEWHITE.image : (Image) Items.HORSEBLACK.image;
    }

    @Override
    public boolean move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        if (Math.abs(newXPosition - lastXPosition) == 2 && Math.abs(newYPosition - lastYPosition) == 1){
            setXPosition(newXPosition);
            setYPosition(newYPosition);
        }else if (Math.abs(newYPosition - lastYPosition) == 2 && Math.abs(newXPosition - lastXPosition) == 1){
            setXPosition(newXPosition);
            setYPosition(newYPosition);
        }else return false;
        return true;
    }

    @Override
    public void moveToKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        move(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, List<ChessFigure> items) {
        return false;
    }

    @Override
    public List<CoordOnField> getCoordsOnTheWay(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition) {
        return new ArrayList<>();
    }


}
