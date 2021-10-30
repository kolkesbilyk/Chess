import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Officer extends Figure{

    public Officer(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    @Override
    public Image getImageFigure() {
        return super.getIsWhite() == true? (Image) Items.OFFICERWHITE.image : (Image) Items.OFFICERBLACK.image;
    }
}
