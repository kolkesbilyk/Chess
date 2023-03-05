package version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends JFrame{

    private static final String[][] CHESSBOARD = new String[8][8];
    private static final int IMAGE_SIZE = 64;

    private static ChessFigure selectFigure = null;
    private ChessList chessList = new ChessList();
    public static int countStep = 0;
    private static List<Step> stepList = new ArrayList<>();

    public static List<ChessFigure> items = new ArrayList<>(32);
    static {
        items.add(new King(3,0, FigureColor.WHITE));
        items.add(new King(3,7, FigureColor.BLACK));
        items.add(new Queen(4,0, FigureColor.WHITE));
        items.add( new Queen(4,7, FigureColor.BLACK));
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
    public static void main(String[] args) {
        new Main();
    }

    Main(){
        setImage();
        initPanel();
        initFrame();
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

    private void initPanel(){
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = CHESSBOARD.length - 1; i >= 0; i--) {
                    for (int j = CHESSBOARD[0].length - 1; j >= 0; j--) {
                        if ((i + j) % 2 == 0) CHESSBOARD[i][j] = "W" + chessBoardCoord(j, i);
                        else CHESSBOARD[i][j] = "B" + chessBoardCoord(j, i);
                        if (Character.toString(CHESSBOARD[i][j].charAt(0)).equalsIgnoreCase("W"))
                            g.setColor(Color.WHITE.darker());
                        else g.setColor(Color.GRAY.darker());
                        g.fillRect(i * IMAGE_SIZE, j * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
                    }
                }
                for (ChessFigure figure : items) {
                    g.drawImage(figure.getImageFigure(), figure.getCoord().getXp() * IMAGE_SIZE,
                            figure.getCoord().getyP() * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                FigureColor whoseStep = Utils.whoseStep(stepList);
                ChessFigure pressFigure = Utils.getFigure(e, items);
                CoordOnField coordEmpty = null;
                if (pressFigure != null && pressFigure.getFigureColor() == whoseStep){
                    selectFigure = pressFigure;
                    chessList.add(selectFigure);
                }else if (pressFigure == null){
                    coordEmpty = Utils.getCoordFromEvent(e);
                }else if (pressFigure.getFigureColor() != whoseStep && selectFigure != null){
                    chessList.add(pressFigure);
                }
                if (selectFigure != null && (coordEmpty != null || chessList.size() == 2)) {
                    CoordOnField newCoord = coordEmpty != null ? coordEmpty : pressFigure.getCoord();
                    boolean wasMove = selectFigure.move(selectFigure.getCoord(), newCoord, chessList, items);
                    if (wasMove) {
                        String details = selectFigure + " ==> " + newCoord;
                        countStep++;
                        logSteps(details);
                        selectFigure = null;
                        chessList.clear();
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(panel);
    }

    private static String chessBoardCoord(int a, int b){
        String letters = "abcdefgh";
        String numbers = "12345678";
        if ((a > 7) || (b > 7)) return null;
        else return (Character.toString(letters.charAt(a)) + numbers.charAt(b));
    }

    private void setImage(){
        for (Items items: Items.values()){
            items.image = getIMage(items.name());
        }
    }

    private Image getIMage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        return icon.getImage();
    }

    private static void logSteps(String details){
        Step step = new Step();
        step.setId(countStep);
        step.setChessFigure(selectFigure);
        step.setFigureColor(selectFigure.getFigureColor());
        step.setDetails(details);
        stepList.add(step);
        System.out.println(step);
    }
}
