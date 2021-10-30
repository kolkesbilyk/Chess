import java.awt.*;
import java.util.ArrayList;

public class Queen extends Figure{
    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() == true? (Image) Items.QUEENWHITE.image : (Image) Items.QUEENBLACK.image;
    }
}
