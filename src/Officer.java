import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Officer implements ChessFigure{
    int xp;
    int yp;
    boolean isWhite;
    ArrayList<ChessFigure> chessFigures;
    int[] coord = new int[2];
    volatile int[] lastCoord = new int[2];

    public Officer(int xp, int yp, boolean isWhite, ArrayList<ChessFigure> chessFigures) {
        this.xp = xp;
        this.yp = yp;
        this.isWhite = isWhite;
        this.chessFigures = chessFigures;
        this.lastCoord = new int[]{xp, yp};
    }
    @Override
    public Image getImageFigure() {
        return isWhite == true? (Image) Items.OFFICERWHITE.image : (Image) Items.OFFICERBLACK.image;
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
            //if (!Logics.isEmpty() && ChessGame.getFigure(xp, yp).getIsWhite() == this.getIsWhite()){
            if (ChessGame.getFigure(xp, yp).getIsWhite() == getIsWhite()){
                this.setXp(getLastCoord(0));
                this.setYp(getLastCoord(1));
                return;
            } else if (!ChessGame.getFigure(xp, yp).getIsWhite() == getIsWhite()){
                this.setXp(getLastCoord(0));
                this.setYp(getLastCoord(1));
                return;
            }
        }
        this.setXp(x);
        this.setYp(y);
        System.out.println(Arrays.toString(getLastCoord()));
    }
    @Override
    public void kill() {
        ChessGame.figures.remove(this);
    }
    @Override
    public int[] getCoord() {
        return this.coord = new int[]{getXp(), getYp()};
    }

    @Override
    public int[] getLastCoord() {
        return lastCoord;
    }
    public int getLastCoord(int i) {
        return lastCoord[i];
    }

    @Override
    public void setLastCoord(int x, int y) {
        this.lastCoord = new int[]{x, y};
    }
}
