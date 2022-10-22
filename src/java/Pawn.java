import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Pawn extends Figure{

    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() ? (Image) Items.PAWNWHITE.image : (Image) Items.PAWNBLACK.image;
    }

    @Override
    public void move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        boolean bothX = lastXPosition == newXPosition;
        if (getIsWhite()){
            if (newYPosition - lastYPosition == 2 && newYPosition == 3 && bothX && !isFigurePresentBetweenPosition){
                setYPosition(newYPosition);
            }else if (newYPosition - lastYPosition == 1 && bothX){
                setYPosition(newYPosition);
            }
        }else {
            if (newYPosition - lastYPosition == -2 && newYPosition == 4 && bothX){
                setYPosition(newYPosition);
            }else if (newYPosition - lastYPosition == -1 && bothX){
                setYPosition(newYPosition);
            }
        }
        System.out.println("pawn was gone");
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items) {
        ChessFigure chessFigure = items.get(new CoordOnField(newXPosition, (newYPosition - lastYPosition) / 2));
        return chessFigure != null;
    }
}
