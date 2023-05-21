package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance;
    private double width;
    private double height;

    private Camera(CameraBuilder camBuilder) {

        p0 = camBuilder.p0;
        vTo = camBuilder.vTo;
        vUp = camBuilder.vUp;
        vRight = camBuilder.vRight;
        distance = camBuilder.distance;
        width = camBuilder.width;
        height = camBuilder.height;
//        antiAliasing = camBuilder.antiAliasing;
//        n = camBuilder.n;
//        m = camBuilder.m;
//        recurseDepth = camBuilder.recurseDepth;
//        imageWriter = camBuilder.imageWriter;
//        rayTracer = camBuilder.rayTracer;
//        dof = camBuilder.dof;
//        apertureRadius = camBuilder.apertureRadius;
//        useDOF = camBuilder.useDOF;
//        threadsCount = camBuilder.threadsCount;
//        printInterval = camBuilder.printInterval;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }


    //region Camera Builder

    /**
     * inner builder class. implements Builder pattern
     * constructs a new {@link Camera} object
     */
    public static class CameraBuilder {


        // static data fields
        /**
         * camera's position point in 3D space
         */
        private Point p0;
        /**
         * vector pointing towards view plane (-Z axis)
         */
        private Vector vTo;
        /**
         * vector pointing up ( Y axis)
         */
        private Vector vUp;
        /**
         * vector pointing right ( X axis)
         */
        private Vector vRight;
        /**
         * distance between view plane from camera
         */
        private double distance;
        /**
         * width of view plane "Physical" size
         */
        private double width;
        /**
         * height of view plane "Physical" size
         */
        private double height;        //

//        // functionality  fields
//        /**
//         * image writing to file functionality object
//         */
//        private ImageWriter imageWriter;
//        /**
//         * calculate color of pixel functionality object
//         */
//        private RayTracer rayTracer;
//
//        // anti-aliasing functionality
//
//
//        /**
//         * anti aliasing method tu use in image rendering
//         */
//        private AntiAliasing antiAliasing = AntiAliasing.NONE;
//
//        /**
//         * first parameter for number of random ray to cast for random beam anti aliasing
//         */
//        private int n;
//
//        /**
//         * first parameter for number of random ray to cast for random beam anti aliasing
//         */
//        private int m;
//
//        /**
//         * depth of recursion for adaptive anti-aliasing
//         */
//        private int recurseDepth = 2;
//
//
//        // depth of field functionality
//        /**
//         * distance of focal popint from camera to create dof effect
//         */
//        private double dof;
//
//        /**
//         * camera's aperture size
//         */
//        private double apertureRadius;
//
//        /**
//         * determine if DOF functionality is used in image rendering
//         */
//        private boolean useDOF = false;
//
//        /**
//         * number of threads to use in image rendering
//         */
//        private int threadsCount =0 ;
//        /**
//         * Spare threads if trying to use all the cores
//         */
//        private static final int SPARE_THREADS = 2;
//        /**
//         *  print interval in seconds
//         */
//        private double printInterval =0l ;
//
//
//
//
//

        /**
         * constructor
         *
         * @param p0  position point of camera in #D space
         * @param vTo vector pointing towards view plane (-Z axis)
         * @param vUp vector pointing up ( Y axis)
         */
        public CameraBuilder(Point p0, Vector vTo, Vector vUp) {

            this.p0 = p0;

            //vto and vup must be orthogonal
            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("the vectors vUp and vTo are not orthogonal");
            }

            //stores the vector after normalizing
            this.vTo = vTo.normalize();
            this.vUp = vUp.normalize();

            // vector to right (X axis calculated by cross product of vUp and vTo)
            vRight = this.vTo.crossProduct(this.vUp);
        }


        //chaining methods - for builder pattern

        /**
         * set view plane distance - (chaining method)
         *
         * @param distance distance between camera and view plane
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setVPDistance(double distance) {
            this.distance = distance;
            return this;
        }

        /**
         * set "physical" size of view plane (chaining method)
         *
         * @param width  size of width of view plane
         * @param height size of height of view plane
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setVPSize(double width, double height) {
            this.width = width;
            this.height = height;
            return this;
        }

        /**
         * set anti aliasing method to be used by camera
         *
         * @param aliasing {@link AntiAliasing} method
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setAntiAliasing(AntiAliasing aliasing) {
            this.antiAliasing = aliasing;
            return this;
        }

        /**
         * setter for first parameter of n*m used for random ray casting loop
         *
         * @param num value of parameter
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setN(int num) {
            this.n = num;
            return this;
        }

        /**
         * setter for second parameter of n*m used for random ray casting loop
         *
         * @param num value of parameter
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setM(int num) {
            this.m = num;
            return this;
        }

        /**
         * setter for recursive depth field
         *
         * @param recurseDepth depth of recursion
         * @return value of depth of recursion wanted in adaptive anti-aliasing
         */
        public CameraBuilder setRecurseDepth(int recurseDepth) {
            this.recurseDepth = recurseDepth;
            return this;
        }

        /**
         * set image writing to file functionality of camera
         *
         * @param imageWriter instance of {@link ImageWriter} class ,enables writing a scene to jpeg file
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setImageWriter(ImageWriter imageWriter) {
            this.imageWriter = imageWriter;
            return this;
        }

        /**
         * set image rendering functionality of camera
         *
         * @param rayTracer instance of {@link RayTracer} class - enables calculating color of each pixel
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setRayTracer(RayTracer rayTracer) {
            this.rayTracer = rayTracer;
            return this;
        }

        /**
         * set for dof field
         *
         * @param dof depth of field focal point distance from camera
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setDof(double dof) {
            this.dof = dof;
            return this;
        }

        /**
         * set for apertureRadius field
         *
         * @param apertureRadius radius of aperture of camera
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setApertureRadius(double apertureRadius) {
            this.apertureRadius = apertureRadius;
            return this;
        }

        /**
         * setter for use DOF field
         *
         * @param useDOF true if DOF is used , otherwise, false.
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setUseDOF(boolean useDOF) {
            this.useDOF = useDOF;
            return this;
        }

        /**
         * Set multi-threading <br>
         * - if the parameter is 0 - number of cores less 2 is taken
         *
         * @param threads number of threads
         * @return the Render object itself
         */
        public CameraBuilder setMultithreading(int threads) {
            if (threads < -2)
                throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
            if (threads >= -1)
                this.threadsCount = threads;
            else {
                int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
                this.threadsCount = cores <= 2 ? 1 : cores;
            }
            return this;
        }

        /**
         * Set debug printing interval. If it's zero - there won't be printing at all
         *
         * @param interval printing interval in seconds
         * @return the Render object itself
         */
        public CameraBuilder setDebugPrint(double interval) {
            printInterval = interval;
            return this;
        }

        /**
         * Builder pattern - build function - creates new camera
         * using this instance of {@link CameraBuilder}
         *
         * @return new {@link Camera} object
         */
        public Camera build() {
            return new Camera(this);
        }


    }
    //endregion

    public Camera setVPSize(double width, double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height cannot be negative ");
        }
        this.height = height;
        this.width = width;
    }

    public Point getP0() {
        return p0;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getDistance() {
        return distance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
