package version2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessFigure{

    public Rook(int x, int y, FigureColor figureColor) {
        super(x, y, figureColor);
    }

    @Override
    public Image getImageFigure() {
        return getFigureColor() == FigureColor.WHITE ? (Image) Items.ROOKWHITE.image : (Image) Items.ROOKBLACK.image;
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

        if (lastXPosition == newXPosition && !isFigurePresentBetweenPosition){
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
