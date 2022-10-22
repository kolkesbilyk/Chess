import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class King extends Figure{
    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() ? (Image) Items.KINGWHITE.image : (Image) Items.KINGBLACK.image;
    }

    @Override
    public void move(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, boolean isFigurePresentBetweenPosition) {
        if (newXPosition - lastXPosition <= 1 && newXPosition - lastXPosition >= -1 && newYPosition - lastYPosition >= -1 && newYPosition - lastYPosition <= 1){
            setXPosition(newXPosition);
            setYPosition(newYPosition);
        }
        System.out.println("king was gone");
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items) {
        //No implemented
        return false;
    }
}
