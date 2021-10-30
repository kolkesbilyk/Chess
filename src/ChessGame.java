import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

public class ChessGame extends JFrame {
    public static ChessFigure selectFigure = null;
    public static Map<CoordOnField, ChessFigure> items = new HashMap<>(32);
    static {
        items.put(new King(3,0, true).getCoordOnField(),new King(3,0, true));
        items.put(new King(3,7, false).getCoordOnField(),new King(3,7, false));
        items.put(new Queen(4,0,true).getCoordOnField(), new Queen(4,0,true));
        items.put(new Queen(4,7,false).getCoordOnField(), new Queen(4,7,false));
        items.put(new Officer(2, 0, true).getCoordOnField(), new Officer(2, 0, true));
        items.put(new Officer(5, 0, true).getCoordOnField(), new Officer(5, 0, true));
        items.put(new Officer(2, 7, false).getCoordOnField(), new Officer(2, 7, false));
        items.put(new Officer(5, 7, false).getCoordOnField(), new Officer(5, 7, false));
        items.put(new Horse(1,0, true).getCoordOnField(), new Horse(1,0, true));
        items.put(new Horse(6,0, true).getCoordOnField(), new Horse(6,0, true));
        items.put(new Horse(1,7, false).getCoordOnField(), new Horse(1,7, false));
        items.put(new Horse(6,7, false).getCoordOnField(), new Horse(6,7, false));
        items.put(new Rook(0, 0, true).getCoordOnField(), new Rook(0, 0, true));
        items.put(new Rook(7, 0, true).getCoordOnField(), new Rook(7, 0, true));
        items.put(new Rook(0, 7, false).getCoordOnField(), new Rook(0, 7, false));
        items.put(new Rook(7, 7, false).getCoordOnField(), new Rook(7, 7, false));
        for (int i = 0; i < 8; i++) {
            items.put(new Pawn(i, 1, true).getCoordOnField(), new Pawn(i, 1, true));
            items.put(new Pawn(i, 6, false).getCoordOnField(), new Pawn(i, 6, false));
        }
    }
    public static List<ChessFigure> figures = new ArrayList<>(items.values());
    public static Set<CoordOnField> coord = items.keySet();
    JPanel panel;
    private static final int IMAGE_SIZE = 64;
    public static String[][] chessBoard = new String[8][8];
    public static void main(String[] args) {
        new ChessGame();
    }
    public ChessGame(){
        setImage();
        initPanel();
        initFrame();
    }
    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < chessBoard.length; i++) {
                    for (int j = 0; j < chessBoard[0].length; j++) {
                        if ((i + j) % 2 == 0) chessBoard[i][j] = "W" + chessBoardCoord(i, j);
                        else chessBoard[i][j] = "B" + chessBoardCoord(i, j);
                        if (Character.toString(chessBoard[i][j].charAt(0)).equalsIgnoreCase("W")) g.setColor(Color.WHITE.darker());
                        else g.setColor(Color.GRAY.darker());
                        g.fillRect(i * IMAGE_SIZE, j * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
                    }
                }
//                for (int i = 0; i < chessBoard.length; i++) {
//                    for (int j = 0; j < chessBoard[0].length; j++) {
//                        System.out.print(" " + chessBoard[i][j] + " ");
//                    }
//                    System.out.println();
//                }
                for (Map.Entry entry: items.entrySet()){
                    ChessFigure figure = (ChessFigure) entry.getValue();
                    g.drawImage(figure.getImageFigure(), figure.getXPosition() * IMAGE_SIZE,
                            figure.getYPosition() * IMAGE_SIZE, this);
                }
                System.out.println(coord);
            }
        };
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println((getFigure(e.getX(), e.getY())
                        .getIsWhite()? "white " : "black ") + getFigure(e.getX(), e.getY())
                        .getClass().getSimpleName() + " " + chessBoard[e.getX() / 64][e.getY() / 64] + " "
                        + getFigure(e.getX(), e.getY()).getCoordOnField());
                selectFigure = getFigure(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectFigure.setXPosition(e.getX());
                selectFigure.setYPosition(e.getY());
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectFigure != null){
                    selectFigure.setXPosition(e.getX());
                    selectFigure.setYPosition(e.getY());
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        add(panel);
    }
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess");
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setSize(8 * IMAGE_SIZE + 15, 8 * IMAGE_SIZE + 38);
    }
    private void setImage(){
        for (Items items: Items.values()){
            items.image = getIMage(items.name());
        }
    }

    public Image getIMage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
    public static ChessFigure getFigure(int x, int y){
        int xp = x / IMAGE_SIZE;
        int yp = y / IMAGE_SIZE;
        for (ChessFigure figure: figures)
            if (figure.getXPosition() == xp && figure.getYPosition() == yp) return figure;
        return null;
   }
   public  static String chessBoardCoord(int a, int b){
        String letters = "abcdefgh";
        String numbers = "87654321";
        if ((a > 7) || (b > 7)) return null;
        else return (Character.toString(letters.charAt(a)) + numbers.charAt(b));
   }
}
