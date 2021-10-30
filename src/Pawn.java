import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Figure{

    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() == true? (Image) Items.PAWNWHITE.image : (Image) Items.PAWNBLACK.image;
    }
}
