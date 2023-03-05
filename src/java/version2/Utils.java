package version2;

import java.awt.event.MouseEvent;
import java.util.List;

public class Utils {

    public static ChessFigure getFigure(MouseEvent e, List<ChessFigure> items){
        CoordOnField coord = getCoordFromEvent(e);
        return getFigure(coord, items);
    }

    public static ChessFigure getFigure(CoordOnField coord, List<ChessFigure> items){
        for (ChessFigure figure: items)
            if (figure.getCoord().equals(coord)) return figure;
        return null;
    }

    public static boolean isFigurePresentOnTheWay(List<CoordOnField> coordOnFields, List<ChessFigure> items){
        for (CoordOnField coord: coordOnFields){
            for (ChessFigure chessFigure: items){
                if (coord.getXp() == chessFigure.getCoord().getXp() && coord.getyP() == chessFigure.getCoord().getyP()){
                    return true;
                }
            }
        }
        return false;
    }

    public static CoordOnField getCoordFromEvent(MouseEvent e){
        return new CoordOnField(e.getX() / 64, e.getY() / 64);
    }

    public static FigureColor whoseStep(List<Step> steps){
        if (steps.isEmpty() || steps.size() % 2 == 0){
            return FigureColor.WHITE;
        }else return FigureColor.BLACK;
    }
}
