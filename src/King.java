import java.awt.*;
import java.util.ArrayList;

public class King extends Figure{
    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() == true? (Image) Items.KINGWHITE.image : (Image) Items.KINGBLACK.image;
    }
}
