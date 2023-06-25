package primitives;

import java.util.Objects;

/**
 * A class representing a point in 3D space.
 */
public class Point {
    final Double3 xyz;

    /**
     * Constructs a new Point object with the specified coordinates.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @param z The z coordinate of the point.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new Point object with the coordinates from a Double3 object.
     * @param double3 A Double3 object containing the x, y, and z coordinates of the point.
     */
    public Point(Double3 double3) {
        this(double3.d1, double3.d2, double3.d3);
    }

    /**
     * get X axis coordinate of a point
     * @return X axis coordinate - (double)
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * get Y axis coordinate of a point
     * @return Y axis coordinate - (double)
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * get Z axis coordinate of a point
     * @return Z axis coordinate - (double)
     */
    public double getZ() { return xyz.d3; }



    /**
     * Checks if this point is equal to another object.
     * @param o The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    /**
     * Returns the hash code of this Point object.
     * @return The hash code of this object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(xyz);
    }

    /**
     * Returns a string representation of this Point object.
     * @return A string representation of this object.
     */
    @Override
    public String toString() {

        return "Point" + super.toString();
    }

    /**
     * Calculates the squared distance between this point and another point.
     * @param other The other point to calculate the distance to.
     * @return The squared distance between this point and the other point.
     */
    public double distanceSquared(Point other) {
        return (other.xyz.d1 - xyz.d1) * (other.xyz.d1 - xyz.d1) +
                (other.xyz.d2 - xyz.d2) * (other.xyz.d2 - xyz.d2) +
                (other.xyz.d3 - xyz.d3) * (other.xyz.d3 - xyz.d3);
    }

    /**
     * Returns the distance between this point and another point.
     * @param other The other point to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    public Vector subtract(Point p1) {

//        return new Vector(xyz.subtract(p1.xyz));
        Double3 result= xyz.subtract(p1.xyz);
        if(result.equals(Double3.ZERO))
            throw new IllegalArgumentException(("resulting of subtract: vector (0,0,0) not allowed"));
        return new Vector(result);
    }
}
