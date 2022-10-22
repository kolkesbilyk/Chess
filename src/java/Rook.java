import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Rook extends Figure{

    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() == true? (Image) Items.ROOKWHITE.image : (Image) Items.ROOKBLACK.image;
    }

    @Override
    public void move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        if (lastXPosition == newXPosition && !isFigurePresentBetweenPosition){
            setYPosition(newYPosition);
        }else if (lastYPosition == newYPosition && !isFigurePresentBetweenPosition){
            setXPosition(newXPosition);
        }
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items) {
        if (newXPosition == lastXPosition){
            if (lastYPosition < newYPosition) {
                for (int i = lastYPosition + 1; i < newYPosition; i++) {
                    ChessFigure chessFigure = items.get(new CoordOnField(newXPosition, i));
                    if (chessFigure != null) return true;
                }
            }else if (lastYPosition > newYPosition){
                for (int i = newYPosition + 1; i < lastYPosition; i++) {
                    ChessFigure chessFigure = items.get(new CoordOnField(newXPosition, i));
                    if (chessFigure != null) return true;
                }
            }
        }else if (newYPosition == lastYPosition){
            if (lastXPosition < newXPosition) {
                for (int i = lastXPosition + 1; i < newXPosition; i++) {
                    ChessFigure chessFigure = items.get(new CoordOnField(i, newYPosition));
                    if (chessFigure != null) return true;
                }
            }else if (lastXPosition > newXPosition){
                for (int i = newXPosition + 1; i < lastXPosition; i++) {
                    ChessFigure chessFigure = items.get(new CoordOnField(i, newYPosition));
                    if (chessFigure != null) return true;
                }
            }
        }
        return false;
    }
}
