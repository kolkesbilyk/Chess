import java.awt.*;
import java.util.ArrayList;

public class Horse implements ChessFigure{
    int xp;
    int yp;
    boolean isWhite;
    ArrayList<ChessFigure> chessFigures;
    int[] coord;
    int[] lastCoord = new int[2];

    public Horse(int xp, int yp, boolean isWhite, ArrayList<ChessFigure> chessFigures) {
        this.xp = xp;
        this.yp = yp;
        this.isWhite = isWhite;
        this.chessFigures = chessFigures;
    }

    @Override
    public Image getImageFigure() {
        return isWhite == true? (Image) Items.HORSEWHITE.image : (Image) Items.HORSEBLACK.image;
    }

    @Override
    public int getXp() {
        return xp;
    }

    @Override
    public int getYp() {
        return yp;
    }

    @Override
    public boolean getIsWhite() {
        return isWhite;
    }
    @Override
    public void setXp(int x) {
        this.xp = x / 64;
    }
    @Override
    public void setYp(int y) {
        this.yp = y / 64;
    }
    @Override
    public void move(int x, int y) {
        xp = x / 64;
        yp = y / 64;
        if (ChessGame.getFigure(xp, yp) != null){
            if (ChessGame.getFigure(xp, yp).getIsWhite() != isWhite){
                ChessGame.getFigure(xp, yp).kill();
            } else {
                this.setXp(x);
                this.setYp(y);
                return;
            }
        }
        this.setXp(x);
        this.setYp(y);
    }

    @Override
    public void kill() {
        chessFigures.remove(this);
    }

    @Override
    public int[] getCoord() {
        return this.coord = new int[]{getXp(), getYp()};
    }
    @Override
    public int[] getLastCoord() {
        return this.lastCoord;
    }

    @Override
    public void setLastCoord(int x, int y) {
        this.lastCoord = new int[]{x / 64, y / 64};
    }
}
