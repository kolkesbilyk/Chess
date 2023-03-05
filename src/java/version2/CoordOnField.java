package version2;

public class CoordOnField {
    private int xp;
    private int yP;

    public CoordOnField(int xp, int yP) {
        this.xp = xp;
        this.yP = yP;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getyP() {
        return yP;
    }

    public void setyP(int yP) {
        this.yP = yP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordOnField that = (CoordOnField) o;

        if (xp != that.xp) return false;
        return yP == that.yP;
    }

    @Override
    public int hashCode() {
        int result = xp;
        result = 31 * result + yP;
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "xp=" + xp +
                ", yP=" + yP +
                '}';
    }
}
