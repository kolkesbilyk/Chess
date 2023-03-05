import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure{

    public Rook(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public boolean canKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition) {
        return false;
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.ROOKWHITE.image : (Image) Items.ROOKBLACK.image;
    }

    @Override
    public boolean move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        if (lastXPosition == newXPosition && !isFigurePresentBetweenPosition){
            setYPosition(newYPosition);
        }else if (lastYPosition == newYPosition && !isFigurePresentBetweenPosition){
            setXPosition(newXPosition);
        }else return false;
        return true;
    }

    @Override
    public void moveToKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        move(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, List<ChessFigure> items) {
        List<CoordOnField> coords = getCoordsOnTheWay(newXPosition, newYPosition, lastXPosition, lastYPosition);
        if (!coords.isEmpty()){
            ChessFigure chessFigure = getFigure(coords, items);
            return chessFigure != null;
        }
        return false;
    }

    @Override
    public List<CoordOnField> getCoordsOnTheWay(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition){
        List<CoordOnField> coords = new ArrayList<>();
        if (Math.abs(newXPosition - lastXPosition) > 1 || Math.abs(newYPosition - lastYPosition) > 1){
            if (newXPosition == lastXPosition){
                for (int i = Math.min(newYPosition, lastYPosition) + 1; i < Math.max(newYPosition, lastYPosition); i++) {
                    coords.add(new CoordOnField(newXPosition, i));
                }
            }else if (newYPosition == lastYPosition){
                for (int i = Math.min(newXPosition, lastXPosition) + 1; i < Math.max(newXPosition, lastXPosition); i++) {
                    coords.add(new CoordOnField(i, newYPosition));
                }
            }
        }
        return coords;
    }
}
