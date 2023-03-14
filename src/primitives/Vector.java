/**
 * Represents a three-dimensional vector in space.
 * A Vector is a subclass of Point, with additional methods for vector operations.
 */
package primitives;

public class Vector extends Point {
    /**
     * Constructs a new Vector object with the specified x, y, and z coordinates.
     *
     * @param x the x coordinate of the vector
     * @param y the y coordinate of the vector
     * @param z the z coordinate of the vector
     * @throws IllegalArgumentException if the x, y, and z coordinates are all zero
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vector cannot be ZERO");
        }
    }
    public Vector add(Vector vector) {
        Double3 xyz = this.xyz.add(vector.xyz);
        if(xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("creation of  ZERO Vector not allowed");
        }
        return new Vector(xyz);
    }

    /**
     * Constructs a new Vector object with the coordinates from the specified Double3 object.
     *
     * @param double3 the Double3 object containing the x, y, and z coordinates of the vector
     */
    Vector(Double3 double3) {
        this(double3.d1, double3.d2, double3.d3);
    }

    /**
     * Determines if this Vector is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the object is a Vector and has the same x, y, and z coordinates as this Vector, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }

    /**
     * Returns the length of this Vector.
     *
     * @return the length of this Vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Returns the squared length of this Vector.
     *
     * @return the squared length of this Vector
     */
    public double lengthSquared() {
        double dx = xyz.d1;
        double dy = xyz.d2;
        double dz = xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Returns a new Vector that is the normalized version of this Vector.
     *
     * @return a new Vector that is the normalized version of this Vector
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.reduce(len));
    }

    public double dotProduct(Vector u) {
        return xyz.d1 * u.xyz.d1 + xyz.d2 * u.xyz.d2 + xyz.d3 * u.xyz.d3;
    }

    public Vector crossProduct(Vector u) {
        double dx = xyz.d2 * u.xyz.d3 - xyz.d3 * u.xyz.d2;
        double dy = xyz.d3 * u.xyz.d1 - xyz.d1 * u.xyz.d3;
        double dz = xyz.d1 * u.xyz.d2 - xyz.d2 * u.xyz.d2;
        return new Vector(dx,dy,dz);
    }
}

