/**
 * Represents a three-dimensional vector in space.
 * A Vector is a subclass of Point, with additional methods for vector operations.
 */
package primitives;

public class Vector extends Point {

    /**
     * X Axis
     */
    public static final Vector X_AXIS = new Vector(1,0,0);
    /**
     * Y Axis
     */
    public static final Vector Y_AXIS = new Vector(0,1,0);
    /**
     * Z Axis
     */
    public static final Vector Z_AXIS = new Vector(0,0,1);


    /**
     * Constructs a new Vector object with the specified x, y, and z coordinates.
     *
     * @param x the x coordinate of the vector
     * @param y the y coordinate of the vector
     * @param z the z coordinate of the vector
     * @throws IllegalArgumentException if the x, y, and z coordinates are all zero
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x,y,z));
//        super(x, y, z);
//        if (xyz.equals(Double3.ZERO)) {
//            throw new IllegalArgumentException("vector cannot be ZERO");
//        }
    }

    /**
     * Constructs a new Vector object with the coordinates from the specified Double3 object.
     *
     * @param double3 the Double3 object containing the x, y, and z coordinates of the vector
     */
    public Vector(Double3 double3) {
        super((double3));
//        this(double3.d1, double3.d2, double3.d3);
    }

    /**
     * Adds another vector to this vector and returns the result as a new vector.
     *
     * @param vector the vector to add to this vector
     * @return a new vector that is the sum of this vector and the specified vector
     * @throws IllegalArgumentException if the resulting vector is the zero vector
     */
    public Vector add(Vector vector) {
        Double3 xyz = this.xyz.add(vector.xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("creation of  ZERO Vector not allowed");
        }
        return new Vector(xyz);
    }

    /**
     * Scales this vector by a scalar value and returns the result as a new vector.
     *
     * @param sca the scalar value to multiply this vector by
     * @return a new vector that is the result of scaling this vector by the specified scalar value
     */
    public Vector scale(double sca) {
        return new Vector(xyz.d1 * sca, xyz.d2 * sca, xyz.d3 * sca);
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
     * Returns the length of this vector.
     *
     * @return the length of this vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Returns the squared length of this vector.
     *
     * @return the squared length of this vector
     */
    public double lengthSquared() {
        double dx = xyz.d1;
        double dy = xyz.d2;
        double dz = xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Returns a new vector that is the normalized version of this vector.
     *
     * @return a new vector that is the normalized version of this vector
     */
    public Vector normalize() {

        double len = length();
        return new Vector(xyz.reduce(len));
    }

    /**
     * Calculates the dot product of this vector and the given vector.
     *
     * @param u the vector to calculate the dot product with
     * @return the dot product of this vector and the given vector
     */
    public double dotProduct(Vector u) {
        return xyz.d1 * u.xyz.d1 + xyz.d2 * u.xyz.d2 + xyz.d3 * u.xyz.d3;
    }

    /**
     * Calculates the cross product of this vector and the given vector.
     * Throws an IllegalArgumentException if the vectors are parallel.
     *
     * @param u the vector to calculate the cross product with
     * @return the cross product of this vector and the given vector
     * @throws IllegalArgumentException if the vectors are parallel
     */
    public Vector crossProduct(Vector u) throws IllegalArgumentException {
        if (Math.abs(this.dotProduct(u)) == this.length() * u.length())
            throw new IllegalArgumentException("cannot calculate cross product of parallel vectors");
        double dx = xyz.d2 * u.xyz.d3 - xyz.d3 * u.xyz.d2;
        double dy = xyz.d3 * u.xyz.d1 - xyz.d1 * u.xyz.d3;
        double dz = xyz.d1 * u.xyz.d2 - xyz.d2 * u.xyz.d1;
        return new Vector(dx, dy, dz);
    }

    @Override
    public String toString() {
        return "Vector:" +
                 "xyz = " + super.toString();
    }

    /**
     * rotate a vector by angle theta
     * @param axis direction of  rotation
     * @param theta angle of rotation
     * @return the vector rotated by angle theta in the direction of the vector sent as parameter
     * @author Yona Shmerla
     */
    public Vector vectorRotate(Vector axis, double theta) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        double u = axis.getX();
        double v = axis.getY();
        double w = axis.getZ();

        double v1 = u * x + v * y + w * z;

        double thetaRad=Math.toRadians(theta);
        double xPrime = u * v1 * (1d - Math.cos(thetaRad))
                + x * Math.cos(thetaRad)
                + (-w * y + v * z) * Math.sin(thetaRad);

        double yPrime = v * v1 * (1d - Math.cos(thetaRad))
                + y * Math.cos(thetaRad)
                + (w * x - u * z) * Math.sin(thetaRad);

        double zPrime = w * v1 * (1d - Math.cos(thetaRad))
                + z * Math.cos(thetaRad)
                + (-v * x + u * y) * Math.sin(thetaRad);

        return new Vector(xPrime, yPrime, zPrime);
    }

}
