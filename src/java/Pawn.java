import java.awt.*;
import java.util.List;

public class Pawn extends Figure{

    public Pawn(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.PAWNWHITE.image : (Image) Items.PAWNBLACK.image;
    }

    @Override
    public boolean move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        boolean bothX = lastXPosition == newXPosition;
        if (getFigureColor() == FigureColor.WHITE){
            if (newYPosition - lastYPosition == 2 && newYPosition == 3 && bothX && !isFigurePresentBetweenPosition){
                setYPosition(newYPosition);
            }else if (newYPosition - lastYPosition == 1 && bothX){
                setYPosition(newYPosition);
            }else return false;
        }else {
            if (newYPosition - lastYPosition == -2 && newYPosition == 4 && bothX && !isFigurePresentBetweenPosition){
                setYPosition(newYPosition);
            }else if (newYPosition - lastYPosition == -1 && bothX){
                setYPosition(newYPosition);
            }else return false;
        }
        return true;
    }

    @Override
    public void moveToKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        if (getFigureColor() == FigureColor.WHITE){
            if (newYPosition - lastYPosition == 1 && Math.abs(newXPosition - lastXPosition) == 1){
                setXPosition(newXPosition);
                setYPosition(newYPosition);
            }
        }else if (getFigureColor() == FigureColor.BLACK){
            if(newYPosition - lastYPosition == -1 && Math.abs(newXPosition - lastXPosition) == 1){
                setXPosition(newXPosition);
                setYPosition(newYPosition);
            }
        }
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition,
                                                  int newYPosition,
                                                  int lastXPosition,
                                                  int lastYPosition,
                                                  List<ChessFigure> items) {
        int selYPosition = Math.min(newYPosition, lastYPosition) + Math.abs(newYPosition - lastYPosition) / 2;
        if (newYPosition != lastYPosition) {
            ChessFigure chessFigure = items
                    .stream()
                    .filter(c -> c.getYPosition() == selYPosition && c.getXPosition() == newXPosition).findFirst().orElse(null);
            return chessFigure != null;
        }
        return false;
    }

    @Override
    public boolean canKill(int newXPosition,
                           int newYPosition,
                           int lastXPosition,
                           int lastYPosition){
        if (getFigureColor() == FigureColor.WHITE && newYPosition - lastYPosition == 1 && Math.abs(newXPosition - lastXPosition) == 1 ){
            return true;
        }else return getFigureColor() == FigureColor.BLACK && newYPosition - lastYPosition == -1 && Math.abs(newXPosition - lastXPosition) == 1;
    }

    @Override
    public List<CoordOnField> getCoordsOnTheWay(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition) {
        return null;
    }
}
