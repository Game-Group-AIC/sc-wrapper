package gg.fel.cvut.cz.api;

import java.util.List;

public interface Polygon {

    double getArea();

    double getPerimeter();

    Position getCenter();

    boolean isInside(Position p);

    Position getNearestPoint(Position p);

    List<Polygon> getHoles();

    List<Position> getPoints();

}
