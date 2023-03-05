package version2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class King extends ChessFigure{

    public King(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.KINGWHITE.image : (Image) Items.KINGBLACK.image;
    }

    @Override
    protected boolean canKill(ChessList chessList, boolean canMove) {
        return canMove;
    }

    @Override
    protected boolean canMove(CoordOnField newCoord, boolean isFigurePresentBetweenPosition) {
        int lastYPosition = getCoord().getyP();
        int lastXPosition = getCoord().getXp();
        int newXPosition = newCoord.getXp();
        int newYPosition = newCoord.getyP();

        return newXPosition - lastXPosition <= 1 && newXPosition - lastXPosition >= -1 && newYPosition - lastYPosition >= -1
            && newYPosition - lastYPosition <= 1;
    }

    @Override
    protected List<CoordOnField> getCoordsOnTheWay(CoordOnField newCoord) {
        return new ArrayList<>(List.of(newCoord));
    }
}
