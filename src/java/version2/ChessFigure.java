package version2;

import java.awt.*;
import java.util.List;

public abstract class ChessFigure {
    private final FigureColor figureColor;
    private CoordOnField coord;

    public ChessFigure(int x, int y, FigureColor figureColor) {
        this.figureColor = figureColor;
        this.coord = new CoordOnField(x, y);
    }

    public abstract Image getImageFigure();

    protected abstract boolean canKill(ChessList chessList, boolean canMove);

    protected abstract boolean canMove(CoordOnField newCoord, boolean isFigurePresentBetweenPosition);

    protected abstract List<CoordOnField> getCoordsOnTheWay(CoordOnField newCoord);

    public FigureColor getFigureColor() {
        return figureColor;
    }

    public CoordOnField getCoord() {
        return coord;
    }

    public void setCoord(CoordOnField coord) {
        this.coord = coord;
    }

    public ChessFigure getAgainst(ChessList chessList){
        return getFigureColor() == FigureColor.WHITE ? chessList.get(FigureColor.BLACK) : chessList.get(FigureColor.WHITE);
    }

    public boolean move(CoordOnField lastCoord, CoordOnField nextCoord, ChessList chessList, List<ChessFigure> items){
        if (nextCoord.getXp() > 7 || nextCoord.getXp() < 0 || nextCoord.getyP() > 7 || nextCoord.getyP() < 0 || lastCoord.equals(nextCoord)) return false;
        List<CoordOnField> coordOnFieldList = getCoordsOnTheWay(nextCoord);
        boolean isFigurePresentBetweenPosition = Utils.isFigurePresentOnTheWay(coordOnFieldList, items);
        boolean canMove = canMove(nextCoord, isFigurePresentBetweenPosition);
        boolean canKill = chessList.size() == 2 && canKill(chessList, canMove);

        if (canKill) {
            items.remove(chessList.get(getFigureColor() == FigureColor.WHITE ? FigureColor.BLACK : FigureColor.WHITE));
            setCoord(nextCoord);
            return true;
        }
        if (canMove) {
            setCoord(nextCoord);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChessFigure that = (ChessFigure) o;

        return coord.equals(that.coord);
    }

    @Override
    public int hashCode() {
        return coord.hashCode();
    }
}
