package version2;

public class Step {
    private int id;
    private FigureColor figureColor;
    private ChessFigure chessFigure;
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FigureColor getFigureColor() {
        return figureColor;
    }

    public void setFigureColor(FigureColor figureColor) {
        this.figureColor = figureColor;
    }

    public ChessFigure getChessFigure() {
        return chessFigure;
    }

    public void setChessFigure(ChessFigure chessFigure) {
        this.chessFigure = chessFigure;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", figureColor=" + figureColor +
                ", chessFigure=" + chessFigure +
                ", details='" + details + '\'' +
                '}';
    }
}
