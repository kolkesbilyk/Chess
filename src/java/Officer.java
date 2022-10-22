import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Officer extends Figure{

    public Officer(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return super.getIsWhite() ? (Image) Items.OFFICERWHITE.image : (Image) Items.OFFICERBLACK.image;
    }

    @Override
    public boolean isFigurePresentBetweenPosition(int newXPosition, int newYPosition, int lastXPosition, int lastYPosition, Map<CoordOnField, ChessFigure> items) {
        return false;
    }
}
