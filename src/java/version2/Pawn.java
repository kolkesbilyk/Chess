package version2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessFigure{

    public Pawn(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.PAWNWHITE.image : (Image) Items.PAWNBLACK.image;
    }

    @Override
    protected boolean canKill(ChessList chessList, boolean canMove) {
        ChessFigure against = getAgainst(chessList);

        int lastXPosition = getCoord().getXp();
        int lastYPosition = getCoord().getyP();
        int newXPosition = against.getCoord().getXp();
        int newYPosition = against.getCoord().getyP();

        if (getFigureColor() == FigureColor.WHITE && newYPosition - lastYPosition == 1 && Math.abs(newXPosition - lastXPosition) == 1 ){
            return true;
        }else return getFigureColor() == FigureColor.BLACK && newYPosition - lastYPosition == -1 && Math.abs(newXPosition - lastXPosition) == 1;
    }

    @Override
    protected boolean canMove(CoordOnField newCoord, boolean isFigurePresentBetweenPosition) {
        int lastYPosition = getCoord().getyP();
        int newYPosition = newCoord.getyP();
        boolean bothX = getCoord().getXp() == newCoord.getXp();

        if (!(bothX && !isFigurePresentBetweenPosition)) return false;

        if (getFigureColor() == FigureColor.WHITE){
            if (newYPosition - lastYPosition == 2 && newYPosition == 3){
                return true;
            }else
                return newYPosition - lastYPosition == 1;
        }else {
            if (newYPosition - lastYPosition == -2 && newYPosition == 4){
                return true;
            }else
                return newYPosition - lastYPosition == -1;
        }
    }

    @Override
    protected List<CoordOnField> getCoordsOnTheWay(CoordOnField newCoord) {
        int lastYPosition = getCoord().getyP();
        int lastXPosition = getCoord().getXp();
        int newYPosition = newCoord.getyP();

        List<CoordOnField> coordOnFields;
        if (getFigureColor() == FigureColor.WHITE){
            if (newYPosition - lastYPosition == 2 && newYPosition == 3){
                coordOnFields = new ArrayList<>();
                coordOnFields.add(new CoordOnField(lastXPosition, lastYPosition + 1));
                coordOnFields.add(new CoordOnField(lastXPosition, lastYPosition + 2));
            }else
                coordOnFields = new ArrayList<>(List.of(new CoordOnField(lastXPosition, lastYPosition + 1)));
        }else {
            if (newYPosition - lastYPosition == -2 && newYPosition == 4){
                coordOnFields = new ArrayList<>();
                coordOnFields.add(new CoordOnField(lastXPosition, lastYPosition - 1));
                coordOnFields.add(new CoordOnField(lastXPosition, lastYPosition - 2));
            }else
                coordOnFields = new ArrayList<>(List.of(new CoordOnField(lastXPosition, lastYPosition - 1)));
        }
        return coordOnFields;
    }
}
