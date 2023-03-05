package version2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessFigure{

    public Queen(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.QUEENWHITE.image : (Image) Items.QUEENBLACK.image;
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

        if (Math.abs(newXPosition - lastXPosition) == Math.abs(newYPosition - lastYPosition) && !isFigurePresentBetweenPosition){
            return true;
        }else if (lastXPosition == newXPosition && !isFigurePresentBetweenPosition){
            return true;
        }else
            return lastYPosition == newYPosition && !isFigurePresentBetweenPosition;
    }

    @Override
    protected List<CoordOnField> getCoordsOnTheWay(CoordOnField newCoord) {
        int lastYPosition = getCoord().getyP();
        int lastXPosition = getCoord().getXp();
        int newXPosition = newCoord.getXp();
        int newYPosition = newCoord.getyP();

        List<CoordOnField> coords = new ArrayList<>();
        if (Math.abs(newXPosition - lastXPosition) > 1 || Math.abs(newYPosition - lastYPosition) > 1) {
            if (newXPosition == lastXPosition) {
                for (int i = Math.min(newYPosition, lastYPosition) + 1; i < Math.max(newYPosition, lastYPosition); i++) {
                    coords.add(new CoordOnField(newXPosition, i));
                }
            } else if (newYPosition == lastYPosition) {
                for (int i = Math.min(newXPosition, lastXPosition) + 1; i < Math.max(newXPosition, lastXPosition); i++) {
                    coords.add(new CoordOnField(i, newYPosition));
                }
            }else {
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
                if (!coords.isEmpty()) coords.remove(0);
            }
        }
        return coords;
    }
}
