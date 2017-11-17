package gg.fel.cvut.cz.wrappers;

class WrapperForPosition<T> extends Wrapper<T> {
    private final int x, y;

    WrapperForPosition(T scInstance, int x, int y) {
        super(scInstance);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WrapperForPosition<?> that = (WrapperForPosition<?>) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
