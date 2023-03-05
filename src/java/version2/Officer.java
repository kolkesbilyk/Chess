package version2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Officer extends ChessFigure{

    public Officer(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.OFFICERWHITE.image : (Image) Items.OFFICERBLACK.image;
    }

    @Override
    protected boolean canKill(ChessList chessList, boolean canMove) {
        return canMove;
    }

    @Override
    protected boolean canMove(CoordOnField newCoord, boolean isFigurePresentBetweenPosition) {
        return Math.abs(newCoord.getXp() - getCoord().getXp()) == Math.abs(newCoord.getyP() - getCoord().getyP()) && !isFigurePresentBetweenPosition;
    }

    @Override
    protected List<CoordOnField> getCoordsOnTheWay(CoordOnField newCoord) {
        int lastYPosition = getCoord().getyP();
        int lastXPosition = getCoord().getXp();
        int newXPosition = newCoord.getXp();
        int newYPosition = newCoord.getyP();

        List<CoordOnField> coords = new ArrayList<>();
        if (Math.abs(newXPosition - lastXPosition) > 1 && Math.abs(newYPosition - lastYPosition) > 1){
            for (int i = lastXPosition;newXPosition > lastXPosition ? i < newXPosition : i > newXPosition;) {
                coords.add(new CoordOnField(i, 0));
                if (newXPosition > lastXPosition) i++;
                else i--;
            }
            int index = 0;
            for (int i = lastYPosition;newYPosition > lastYPosition ? i < newYPosition : i > newYPosition;) {
                coords.get(index).setyP(i);
                index++;
                if (newYPosition > lastYPosition) i++;
                else i--;
            }
        }
        if (!coords.isEmpty()) coords.remove(0);
        return coords;
    }
}
