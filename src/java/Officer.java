import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Officer extends Figure{

    public Officer(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public boolean canKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition) {
        return false;
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.OFFICERWHITE.image : (Image) Items.OFFICERBLACK.image;
    }

    @Override
    public boolean move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        if (Math.abs(newXPosition - lastXPosition) == Math.abs(newYPosition - lastYPosition) && !isFigurePresentBetweenPosition){
            setXPosition(newXPosition);
            setYPosition(newYPosition);
            return true;
        }else return false;
    }

    @Override
    public void moveToKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        move(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, List<ChessFigure> items) {
        List<CoordOnField> coords = getCoordsOnTheWay(newXPosition, newYPosition, lastXPosition, lastYPosition);
        if (!coords.isEmpty()) {
            ChessFigure chessFigure = getFigure(coords, items);
            return chessFigure != null;
        }
        return false;
    }

    @Override
    public List<CoordOnField> getCoordsOnTheWay(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition){
        List<CoordOnField> coords = new ArrayList<>();
        if (Math.abs(newXPosition - lastXPosition) > 1 && Math.abs(newYPosition - lastYPosition) > 1){
            for (int i = lastXPosition;newXPosition > lastXPosition ? i < newXPosition : i > newXPosition;) {
                coords.add(new CoordOnField(i, 0));
                if (newXPosition > lastXPosition) i++;
                else i--;
            }
            int index = 0;
            for (int i = lastYPosition;newYPosition > lastYPosition ? i < newYPosition : i > newYPosition;) {
                coords.get(index).setyPosition(i);
                index++;
                if (newYPosition > lastYPosition) i++;
                else i--;
            }
        }
        if (!coords.isEmpty()) coords.remove(0);
        return coords;
    }
}
