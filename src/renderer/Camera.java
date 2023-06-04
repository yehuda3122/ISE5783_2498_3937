package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera {
    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance;
    private double width;
    private double height;
    private ImageWriter imageWriter = null;
    private RayTracerBase rayTracerBase;

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
        imageWriter = camBuilder.imageWriter;
        rayTracerBase = camBuilder.rayTracerBase;
//        dof = camBuilder.dof;
//        apertureRadius = camBuilder.apertureRadius;
//        useDOF = camBuilder.useDOF;
//        threadsCount = camBuilder.threadsCount;
//        printInterval = camBuilder.printInterval;
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
        private ImageWriter imageWriter;
        //        /**
//         * calculate color of pixel functionality object
//         */
        private RayTracerBase rayTracerBase;
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
//        public CameraBuilder setAntiAliasing(AntiAliasing aliasing) {
//            this.antiAliasing = aliasing;
//            return this;
//        }

        /**
         * setter for first parameter of n*m used for random ray casting loop
         *
         * @param num value of parameter
         * @return this {@link CameraBuilder} instance
         */
//        public CameraBuilder setN(int num) {
//            this.n = num;
//            return this;
//        }

        /**
         * setter for second parameter of n*m used for random ray casting loop
         *
         * @param num value of parameter
         * @return this {@link CameraBuilder} instance
         */
//        public CameraBuilder setM(int num) {
//            this.m = num;
//            return this;
//        }

        /**
         * setter for recursive depth field
         *
         * @param recurseDepth depth of recursion
         * @return value of depth of recursion wanted in adaptive anti-aliasing
         */
//        public CameraBuilder setRecurseDepth(int recurseDepth) {
//            this.recurseDepth = recurseDepth;
//            return this;
//        }

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
         * @param rayTracerBase instance of {@link RayTracerBase} class - enables calculating color of each pixel
         * @return this {@link CameraBuilder} instance
         */
        public CameraBuilder setRayTracerBase(RayTracerBase rayTracerBase) {
            this.rayTracerBase = rayTracerBase;
            return this;
        }

        /**
         * set for dof field
         *
         * @param dof depth of field focal point distance from camera
         * @return this {@link CameraBuilder} instance
         */
//        public CameraBuilder setDof(double dof) {
//            this.dof = dof;
//            return this;
//        }

        /**
         * set for apertureRadius field
         *
         * @param apertureRadius radius of aperture of camera
         * @return this {@link CameraBuilder} instance
         */
//        public CameraBuilder setApertureRadius(double apertureRadius) {
//            this.apertureRadius = apertureRadius;
//            return this;
//        }

        /**
         * setter for use DOF field
         *
         * @param useDOF true if DOF is used , otherwise, false.
         * @return this {@link CameraBuilder} instance
         */
//        public CameraBuilder setUseDOF(boolean useDOF) {
//            this.useDOF = useDOF;
//            return this;
//        }

        /**
         * Set multi-threading <br>
         * - if the parameter is 0 - number of cores less 2 is taken
         *
         * @param threads number of threads
         * @return the Render object itself
         */
//        public CameraBuilder setMultithreading(int threads) {
//            if (threads < -2)
//                throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
//            if (threads >= -1)
//                this.threadsCount = threads;
//            else {
//                int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
//                this.threadsCount = cores <= 2 ? 1 : cores;
//            }
//            return this;
//        }

        /**
         * Set debug printing interval. If it's zero - there won't be printing at all
         *
         * @param interval printing interval in seconds
         * @return the Render object itself
         */
//        public CameraBuilder setDebugPrint(double interval) {
//            printInterval = interval;
//            return this;
//        }

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

//    public Camera setVPSize(double width, double height) {
//        if (width < 0 || height < 0) {
//            throw new IllegalArgumentException("width and height cannot be negative ");
//        }
//        this.height = height;
//        this.width = width;
//    }

    /**
     * getter for p0 field
     *
     * @return position point of camera
     */
    public Point getP0() {

        return p0;
    }


    /**
     * getter for vTo field
     *
     * @return vector pointing towards view plane (-Z axis)
     */
    public Vector getvTo() {

        return vTo;
    }

    /**
     * getter for vUp field
     *
     * @return vector pointing up ( Y axis)
     */
    public Vector getvUp() {

        return vUp;
    }

    /**
     * getter for vRight field
     *
     * @return vector pointing right ( X axis)
     */
    public Vector getvRight() {

        return vRight;
    }

    /**
     * getter for distance field
     *
     * @return distance between view plane from camera
     */
    public double getDistance() {

        return distance;
    }

    /**
     * getter for width field
     *
     * @return width of view plane "Physical" size
     */
    public double getWidth() {

        return width;
    }

    /**
     * getter for height field
     *
     * @return height of view plane "Physical" size
     */
    public double getHeight() {

        return height;
    }

    /**
     * print grid lines on  image
     *
     * @param interval interval ("physical" space) between each pair of grid lines
     * @param color    color of grid lines
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("image writer is not initialized", ImageWriter.class.getName(), "");
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(j, i, color);
            }

        }
    }

    /**
     * cast a ray from camera through pixel (i,j) in view plane and get color of pixel
     *
     * @param Nx number of rows in view plane
     * @param Ny number of columns in view plane
     * @param j  column index of pixel
     * @param i  row index of pixel
     */
    private void castRay(int Nx, int Ny, int j, int i) {
        // construct ray through pixel
        Ray ray = constructRay(Nx, Ny, j, i);
        // return the color using ray tracer
        Color color = rayTracerBase.traceRay(ray);
        imageWriter.writePixel(j, i, color);
    }

    /**
     * crate a jpeg file, with scene "captured" by camera
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("image writer is not initialized", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
    }

    /**
     * render image "captured" through view plane
     */
    public void renderImage() {
        // check that image, writing and rendering objects are instantiated
        if (imageWriter == null)
            throw new MissingResourceException("image writer is not initialized", ImageWriter.class.getName(), "");

        if (rayTracerBase == null)
            throw new MissingResourceException("ray tracer is not initialized", RayTracerBase.class.getName(), "");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i <= nX; i++) {
            for (int j = 0; j <= nY; j++) {
                castRay(nX, nY, j, i);
            }
            //initialize thread progress reporter
//        Pixel.initialize(nY, nX, printInterval);
//
//        // for each pixel (i,j) , construct  ray/rays from camera through pixel,
//        // functions use rayTracer object to get correct color, then use imageWriter to write pixel to the file
//
//        if (isUseDOF()) {
//            renderImageDOF(nX,nY);
//        }
//        //cast ray according to Anti-Aliasing method set to Camera
//        else {
//            switch (getAntiAliasing()) {
//                // no method used - cast single ray to center of pixel
//                case NONE -> {
//                    renderImageAntiAliasingNone(nX,nY);
//                }
//                // bean of random rays cast for each pixel besides the ray towards the center
//                case RANDOM -> {
//                    renderImageAntiAliasingRandom(nX,nY);
//                }
//                // four rays cast to four corners of pixel besides the ray towards the center
//                case CORNERS -> {
//                    renderImageAntiAliasingCorners(nX,nY);
//                }
//                case ADAPTIVE -> {
//                    renderImageAntiAliasingAdaptive(nX,nY);
//                }
//            }
//        }
        }
    }

    /**
     * construct ray from a {@link Camera} towards center of a pixel in a view plane
     *
     * @param nX number of rows in view plane
     * @param nY number of columns in view plane
     * @param j  column index of pixel
     * @param i  row index of pixel
     * @return {@link Ray} from camera to center of pixel (i,j)
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        //view plane center:
        Point Pc = p0.add(vTo.scale(distance));

        // calculate "size" of each pixel -
        // height per pixel = total "physical" height / number of rows
        // width per pixel = total "physical" width / number of columns
        double Ry = (double) height / nX;
        double Rx = (double) width / nY;

        // set result point to middle of view plane
        Point Pij = Pc;

        // calculate necessary "size" needed to move from
        // center of view plane to reach the middle point of pixel (i,j)
        double yI = -(i - (nY - 1) / 2d) * Ry;
        double xJ = (j - (nX - 1) / 2d) * Rx;

        // if result of xJ is > 0
        // move result point left/right on  X axis
        // to reach middle point of pixel (i,j) (on X axis direction)
        if (!isZero(xJ)) {
            Pij = Pij.add(vRight.scale(xJ));
        }

        // if result of yI is > 0
        // move result point up/down on Y axis
        // to reach middle point of pixel (i,j)
        if (!isZero(yI)) {
            Pij = Pij.add(vUp.scale(yI));
        }

        //return ray from camera to middle point of pixel(i,j) in view plane
        return new Ray(p0, Pij.subtract(p0));
    }


}



