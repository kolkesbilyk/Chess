import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

public class ChessGame extends JFrame {
    public static ChessFigure selectFigure = null;
    private static List<ChessFigure> selectFigures = new ArrayList<>(3);
    private static List<Step> stepList = new ArrayList<>();
    public static List<ChessFigure> items = new ArrayList<>(32);
    static {
        items.add(new King(3,0, FigureColor.WHITE));
        items.add(new King(3,7, FigureColor.BLACK));
        items.add(new Queen(4,0,FigureColor.WHITE));
        items.add( new Queen(4,7,FigureColor.BLACK));
        items.add(new Officer(2, 0, FigureColor.WHITE));
        items.add(new Officer(5, 0, FigureColor.WHITE));
        items.add(new Officer(2, 7, FigureColor.BLACK));
        items.add(new Officer(5, 7, FigureColor.BLACK));
        items.add(new Horse(1,0, FigureColor.WHITE));
        items.add(new Horse(6,0, FigureColor.WHITE));
        items.add(new Horse(1,7, FigureColor.BLACK));
        items.add(new Horse(6,7, FigureColor.BLACK));
        items.add(new Rook(0, 0, FigureColor.WHITE));
        items.add(new Rook(7, 0, FigureColor.WHITE));
        items.add(new Rook(0, 7, FigureColor.BLACK));
        items.add(new Rook(7, 7, FigureColor.BLACK));
        for (int i = 0; i < 8; i++) {
            items.add(new Pawn(i, 1, FigureColor.WHITE));
            items.add(new Pawn(i, 6, FigureColor.BLACK));
        }
    }

    public static int countStep = 1;
    public static FigureColor figureColor;
    public static Integer pressXPosition;
    public static Integer pressYPosition;
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
                for (int i = chessBoard.length - 1; i >= 0; i--) {
                    for (int j = chessBoard[0].length - 1; j >= 0; j--) {
                        if ((i + j) % 2 == 0) chessBoard[i][j] = "W" + chessBoardCoord(j, i);
                        else chessBoard[i][j] = "B" + chessBoardCoord(j, i);
                        if (Character.toString(chessBoard[i][j].charAt(0)).equalsIgnoreCase("W")) g.setColor(Color.WHITE.darker());
                        else g.setColor(Color.GRAY.darker());
                        g.fillRect(i * IMAGE_SIZE, j * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
                    }
                }
                for (ChessFigure figure: items){
                    g.drawImage(figure.getImageFigure(), figure.getXPosition() * IMAGE_SIZE,
                            figure.getYPosition() * IMAGE_SIZE, this);
                }
            }
        };
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                figureColor = whoseStep();
                System.out.println("----------------------------------------------------------");
                System.out.println(figureColor.name());
                ChessFigure chessFigure = getFigure(e.getX(), e.getY());
                if (chessFigure != null && chessFigure.getFigureColor() == figureColor) {
                    pressXPosition = e.getX() / 64;
                    pressYPosition = e.getY() / 64;
                    System.out.println("pressXPosition = " + pressXPosition + "; pressYPosition = " + pressYPosition);
                    selectFigure = chessFigure;
                    if (selectFigures.size() >= 1 && selectFigures.get(0).getFigureColor() != figureColor){
                        selectFigures.add(0, selectFigure);
                    }else if (selectFigures.isEmpty()){
                        selectFigures.add(0, selectFigure);
                    }
                    if (selectFigures.size() > 2) selectFigures.remove(2);
                    System.out.println(selectFigures);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boolean wasMoved = selectFigure.moveFigure(e.getX() / 64, e.getY() / 64, pressXPosition, pressYPosition, items, selectFigures);
                if (wasMoved) {
                    System.out.println("was moved");
                    logSteps();
                    countStep++;
                    pressXPosition = null;
                    pressYPosition = null;
                    selectFigure = null;
                }
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
            @Override   //зажата ліва клавіша
            public void mouseDragged(MouseEvent e) {
//                if (selectFigure != null){
//                    selectFigure.moveFigure(e.getX() / 64, e.getY() / 64, pressXPosition, pressYPosition, items, selectFigures);
//                    repaint();
//                }
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
        setLocation(450, 200);
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
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        return icon.getImage();
    }
    public static ChessFigure getFigure(int x, int y){
        int xp = x / IMAGE_SIZE;
        int yp = y / IMAGE_SIZE;
        for (ChessFigure figure: items)
            if (figure.getXPosition() == xp && figure.getYPosition() == yp) return figure;
        return null;
   }
   public  static String chessBoardCoord(int a, int b){
        String letters = "abcdefgh";
        String numbers = "12345678";
        if ((a > 7) || (b > 7)) return null;
        else return (Character.toString(letters.charAt(a)) + numbers.charAt(b));
   }

   private static void logSteps(){
        Step step = new Step();
        step.setId(countStep);
        step.setChessFigure(selectFigure);
        step.setFigureColor(selectFigure.getFigureColor());
        stepList.add(step);
        System.out.println(step);
   }

   private static FigureColor whoseStep(){
        if (stepList.isEmpty() || stepList.size() % 2 == 0){
            return FigureColor.WHITE;
        }else return FigureColor.BLACK;
   }
}
