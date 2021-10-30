import java.awt.*;
import java.util.ArrayList;

public class Rook extends Figure{

    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() == true? (Image) Items.ROOKWHITE.image : (Image) Items.ROOKBLACK.image;
    }
}
