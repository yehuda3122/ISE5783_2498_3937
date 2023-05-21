package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {
    Point p0;
    Vector dir;
    public Ray(Point point,Vector vector) {
        p0 = point;
        dir = vector.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * creating a {@link Point} at a specific distance in the ray's direction
     *
     * @param t scale factor
     * @return new {@link Point}
     */
    public Point getPoint(double t) {
        if (isZero(t))
            return p0;
        return p0.add(dir.scale(t));
    }



}
