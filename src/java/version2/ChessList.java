package version2;

import java.util.Arrays;

public class ChessList {

    private ChessFigure[] chessFigures = new ChessFigure[2];
    private int size;

    public boolean add(ChessFigure chessFigure){
        if (chessFigure == null){
            return false;
        }
        if (chessFigure.getFigureColor() == FigureColor.WHITE){
            chessFigures[0] = chessFigure;
        }else{
            chessFigures[1] = chessFigure;
        }
        return true;
    }

    public ChessFigure get(FigureColor color){
        if (color == FigureColor.WHITE){
            return chessFigures[0];
        }else{
            return chessFigures[1];
        }
    }

    public int size(){
        if (chessFigures[0] == null && chessFigures[1] == null) size = 0;
        else if (chessFigures[0] != null && chessFigures[1] != null) size = 2;
        else size = 1;
        System.out.println("size chesslist = " + size);
        return size;
    }

    public void clear(){
        chessFigures[0] = null;
        chessFigures[1] = null;
        size = 0;
    }

    @Override
    public String toString() {
        return "ChessList{" +
            "chessFigures=" + Arrays.toString(chessFigures) +
            '}';
    }
}
