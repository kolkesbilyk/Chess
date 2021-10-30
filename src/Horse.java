import java.awt.*;
import java.util.ArrayList;

public class Horse extends Figure{

    public Horse(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return getIsWhite() == true? (Image) Items.HORSEWHITE.image : (Image) Items.HORSEBLACK.image;
    }
}
