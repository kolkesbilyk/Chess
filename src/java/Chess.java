//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.util.ArrayList;
//
//public class Chess extends JFrame {
//    public static ArrayList<Piece> ps;
//    static {
//        ps.add(new Officer());
//    }
//    public static Piece selectedPiece = null;
//    JPanel panel;
//    public static final int IMAGE_SIZE = 64;
//    public static void main(String[] args) {
//        new Chess();
//    }
//    public Chess(){
//        setImage();
//        initFrame();
//        initPanel();
//    }
//    private void initFrame() {
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setTitle("Chess");
//        setLocationRelativeTo(null);
//        setResizable(true);
//        setVisible(true);
//        pack();
//        setSize(8 * IMAGE_SIZE + 15, 8 * IMAGE_SIZE + 38);
//    }
//    private void initPanel(){
//        panel = new JPanel(){
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                boolean white = true;
//                for (int y = 0; y < 8; y++) {
//                    for (int x = 0; x < 8; x++) {
//                        if (white) g.setColor(Color.WHITE.darker());
//                        else g.setColor(Color.GRAY);
//                        g.fillRect(y * IMAGE_SIZE, x * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
//                        white = !white;
//                    }
//                    white = !white;
//                }
//                Officer officer = new Officer();
//                g.drawImage((Image) officer.image, officer.startPositionX, officer.startPositionY, this);
//            }
//        };
//        addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                selectedPiece = getPiece(e.getX(), e.getY());
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                selectedPiece.move(e.getX() / IMAGE_SIZE, e.getY() / IMAGE_SIZE);
//                repaint();
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//        addMouseMotionListener(new MouseMotionListener() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                if (selectedPiece != null){
//                    selectedPiece.x = e.getX();
//                    selectedPiece.y = e.getY();
//                    repaint();
//                }
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//
//            }
//        });
//        add(panel);
//    }
//    private void setImage(){
//        for (Items items: Items.values()){
//            items.image = getIMage(items.name());
//        }
//    }
//
//    public Image getIMage(String name) {
//        String filename = "img/" + name.toLowerCase() + ".png";
//        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
//        return icon.getImage();
//    }
//    public static Piece getPiece(int x, int y){
//        int xp = x / IMAGE_SIZE;
//        int yp = y / IMAGE_SIZE;
//        for (Piece p: ps) if (p.xp == xp && p.yp == yp) return p;
//        return null;
//    }
//}
