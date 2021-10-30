//import java.util.ArrayList;
//
//public class Piece {
//    int xp;
//    int yp;
//    int x;
//    int y;
//    boolean isWhite;
//    ArrayList<Piece> ps;
//
//    public Piece(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
//        this.xp = xp;
//        this.yp = yp;
//        x = xp * 64;
//        y = yp * 64;
//        this.isWhite = isWhite;
//        this.ps = ps;
//    }
//
//    public void move(int xp, int yp){
//        if (Chess.getPiece(xp * Chess.IMAGE_SIZE, yp * Chess.IMAGE_SIZE) != null){
//            if (Chess.getPiece(xp * Chess.IMAGE_SIZE, yp * Chess.IMAGE_SIZE).isWhite != isWhite){
//                Chess.getPiece(xp * Chess.IMAGE_SIZE, yp * Chess.IMAGE_SIZE).kill();
//            }else {
//                x = this.xp * Chess.IMAGE_SIZE;
//                y = this.yp * Chess.IMAGE_SIZE;
//                return;
//            }
//        }
//        this.xp = xp;
//        this.yp = yp;
//    }
//    public void kill(){
//        ps.remove(this);
//    }
//}
