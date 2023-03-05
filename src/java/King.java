import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class King extends Figure{
    public King(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public boolean canKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition) {
        return false;
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.KINGWHITE.image : (Image) Items.KINGBLACK.image;
    }

    @Override
    public boolean move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        if (newXPosition - lastXPosition <= 1 && newXPosition - lastXPosition >= -1 && newYPosition - lastYPosition >= -1 && newYPosition - lastYPosition <= 1){
            setXPosition(newXPosition);
            setYPosition(newYPosition);
        }else return false;
        System.out.println("king was gone");
        return true;
    }

    @Override
    public void moveToKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        move(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
    }

    @Override
    public List<CoordOnField> getCoordsOnTheWay(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition) {
        return new ArrayList<>();
    }
}
