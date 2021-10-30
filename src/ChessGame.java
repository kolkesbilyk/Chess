import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class ChessGame extends JFrame {
    public static ChessFigure selectFigure = null;
    public static ArrayList<ChessFigure> figures = new ArrayList<>(32);
    static {
        figures.add(new King(3, 0, true, figures));
        figures.add(new King(3, 7, false, figures));
        figures.add(new Officer(2, 0, true, figures));
        figures.add(new Officer(5, 0, true, figures));
        figures.add(new Officer(2, 7, false, figures));
        figures.add(new Officer(5, 7, false, figures));
        figures.add(new Queen(4, 0, true,figures));
        figures.add(new Queen(4, 7, false, figures));
        figures.add(new Horse(1, 0, true, figures));
        figures.add(new Horse(6, 0, true, figures));
        figures.add(new Horse(1, 7, false, figures));
        figures.add(new Horse(6, 7, false, figures));
        figures.add(new Rook(0, 0, true, figures));
        figures.add(new Rook(7, 0, true, figures));
        figures.add(new Rook(0, 7, false, figures));
        figures.add(new Rook(7, 7, false, figures));
        for (int i = 0; i < 8; i++) {
            figures.add(new Pawn(i, 1, true, figures));
            figures.add(new Pawn(i, 6, false, figures));
        }
    }
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
                for (ChessFigure figure: figures){
                    g.drawImage(figure.getImageFigure(), figure.getXp() * IMAGE_SIZE,
                            figure.getYp() * IMAGE_SIZE, this);
                }
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
                        .getClass().getSimpleName() + " " + chessBoard[e.getX() / 64][e.getY() / 64]);
                selectFigure = getFigure(e.getX(), e.getY());
                selectFigure.setLastCoord(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectFigure.move(e.getX(), e.getY());
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
                    selectFigure.setXp(e.getX());
                    selectFigure.setYp(e.getY());
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
        for (ChessFigure figure: figures) if (figure.getXp() == xp && figure.getYp() == yp) return figure;
        return null;
   }
   public  static String chessBoardCoord(int a, int b){
        String letters = "abcdefgh";
        String numbers = "87654321";
        if ((a > 7) || (b > 7)) return null;
        else return (Character.toString(letters.charAt(a)) + numbers.charAt(b));
   }
}
