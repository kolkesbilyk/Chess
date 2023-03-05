package version2;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Horse extends ChessFigure{

    public Horse(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.HORSEWHITE.image : (Image) Items.HORSEBLACK.image;
    }

    @Override
    protected boolean canKill(ChessList chessList, boolean canMove) {
        return canMove;
    }

    @Override
    protected boolean canMove(CoordOnField newCoord, boolean isFigurePresentBetweenPosition) {
        int newXPosition = newCoord.getXp();
        int newYPosition = newCoord.getyP();
        int lastXPosition = getCoord().getXp();
        int lastYPosition = getCoord().getyP();

        if (Math.abs(newXPosition - lastXPosition) == 2 && Math.abs(newYPosition - lastYPosition) == 1){
            return true;
        }else
            return Math.abs(newYPosition - lastYPosition) == 2 && Math.abs(newXPosition - lastXPosition) == 1;
    }

    @Override
    protected List<CoordOnField> getCoordsOnTheWay(CoordOnField newCoord) {
        return Collections.emptyList();
    }
}
