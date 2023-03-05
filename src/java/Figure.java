import java.awt.*;
import java.util.List;

public abstract class Figure implements ChessFigure {
    private int xPosition;
    private int yPosition;
    private final FigureColor figureColor;

    public Figure(int x, int y, FigureColor figureColor) {
        this.xPosition = x;
        this.yPosition = y;
        this.figureColor = figureColor;
    }

    public int getXPosition() {
        return xPosition;
    }
    public void setXPosition(int x) {
        this.xPosition = x;
    }
    public int getYPosition() {
        return yPosition;
    }
    public void setYPosition(int y) {
        this.yPosition = y;
    }
    public FigureColor getFigureColor() {
        return figureColor;
    }

    @Override
    public boolean moveFigure(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, List<ChessFigure> items, List<ChessFigure> selectFigures){
        if (newXPosition == lastXPosition && newYPosition == lastYPosition) return false;
        System.out.println("your X- position = " + newXPosition + "; Y - position = " + newYPosition);
        System.out.println("another X- position = " + lastXPosition + "; Y - position = " + lastYPosition);
        boolean isFigurePresentBetweenPosition = isFigurePresentBetweenPosition(newXPosition, newYPosition, lastXPosition, lastYPosition, items);
        if (selectFigures.size() > 1) {
            boolean canKill = selectFigures.get(0).canKill(selectFigures.get(1).getXPosition(), selectFigures.get(1).getYPosition(), selectFigures.get(0).getXPosition(), selectFigures.get(0).getYPosition());
            System.out.println(canKill ? "can kill" : "can not kill");
            if (newXPosition < 8 && newXPosition >= 0 && newYPosition >= 0 && newYPosition < 8) {
                if (canKill) {
                    items.remove(selectFigures.get(1));
                    moveToKill(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
                    selectFigures.clear();
                    return true;
                } else {
                    return move(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
                }
            }else return false;
        }else {
            return move(newXPosition, newYPosition, lastXPosition, lastYPosition, isFigurePresentBetweenPosition);
        }
    }

    @Override
    public abstract boolean canKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition);

    public ChessFigure getFigure(List<CoordOnField> coordOnFields, List<ChessFigure> chessFigures){
        for (CoordOnField coord: coordOnFields){
            for (ChessFigure chessFigure: chessFigures){
                if (coord.getxPosition() == chessFigure.getXPosition() && coord.getyPosition() == chessFigure.getYPosition()){
                    return chessFigure;
                }
            }
        }
        return null;
    }

    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, List<ChessFigure> items){
        List<CoordOnField> coords = getCoordsOnTheWay(newXPosition, newYPosition, lastXPosition, lastYPosition);
        if (!coords.isEmpty()){
            ChessFigure chessFigure = getFigure(coords, items);
            return chessFigure != null;
        }
        return false;
    }

    public abstract Image getImageFigure();

    public abstract boolean move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition);

    public abstract void moveToKill(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition);

    public abstract List<CoordOnField> getCoordsOnTheWay(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition);

    @Override
    public String toString() {
        return getClass() + "{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", figureColor=" + figureColor.name() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure figure = (Figure) o;

        if (xPosition != figure.xPosition) return false;
        return yPosition == figure.yPosition;
    }

    @Override
    public int hashCode() {
        int result = xPosition;
        result = 31 * result + yPosition;
        return result;
    }
}
