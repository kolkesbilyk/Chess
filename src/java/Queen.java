import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Queen extends Figure{
    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() == true? (Image) Items.QUEENWHITE.image : (Image) Items.QUEENBLACK.image;
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items) {
        return false;
    }
}
