import java.util.Objects;

public class CoordOnField {
    private int xPosition;
    private int yPosition;

    public CoordOnField(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public String toString() {
        return "{xP=" + xPosition + ", yP=" + yPosition + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordOnField that = (CoordOnField) o;
        if (this.xPosition == that.xPosition && this.yPosition == that.yPosition) return true;
        return xPosition == that.xPosition && yPosition == that.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
}
