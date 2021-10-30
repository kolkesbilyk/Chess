import java.util.ArrayList;
import java.util.Arrays;

public class Logics {
    public static boolean result = true;
    public static ArrayList<int[]> chessCoords = new ArrayList<>(32);
    public static boolean isEmpty(){
        chessCoords.clear();
        for (ChessFigure figure: ChessGame.figures){
            chessCoords.add(figure.getCoord());
        }
        for (int i = 0; i < chessCoords.size(); i++) {
            for (int j = i + 1; j < chessCoords.size(); j++) {
                if (Arrays.equals(chessCoords.get(i), chessCoords.get(j))) {
                    return result = false;
                } else result = true;
            }
        }
        return result;
    }
}
