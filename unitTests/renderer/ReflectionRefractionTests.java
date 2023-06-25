/**
 *
 */
package lighting;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {


   /**
    * Produce a picture of a sphere lighted by a spot light
    */
   @Test
   public void twoSpheres() {
      List<LightSource> lights = new LinkedList<>();
      lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
              .setKL(0.0004).setKQ(0.0000006));
      Scene scene = new Scene.SceneBuilder("Test Scene")
              .setGeometries(new Geometries( //
                      new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setKd(0.4).setKs(0.3).setnShininess(100).setkT(0.3)),
                      new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100))))
              .setLights(lights)
              .build();//


      Camera camera = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(150, 150).setVPDistance(1000)
              .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
              .setRayTracerBase(new RayTracerBasic(scene))
              .build();
      camera.renderImage();
      camera.writeToImage();
   }

   /**
    * Produce a picture of a sphere lighted by a spot light
    */
   @Test
   public void twoSpheresOnMirrors() {
      List<LightSource> lights = new LinkedList<>();
      lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
              .setKL(0.00001).setKQ(0.000005));

      Scene scene = new Scene.SceneBuilder("Test Scene")
              .setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)))
              .setGeometries(new Geometries(
                      new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setkT(0.5)),
                      new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20)),
                      new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setkR(1)),
                      new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                              new Point(-1500, -1500, -2000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setkR(0.5))))

              .setLights(lights)
              .build();

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      Camera camera = new Camera.CameraBuilder(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(2500, 2500)
              .setVPDistance(10000)
              .setImageWriter(imageWriter)
              .setRayTracerBase(new RayTracerBasic(scene)).build(); //

      camera.renderImage(); //
      camera.writeToImage();
   }

   /**
    * Produce a picture of a two triangles lighted by a spot light with a partially
    * transparent Sphere producing partial shadow
    */
   @Test
   public void trianglesTransparentSphere() {
      List<LightSource> lights = new LinkedList<>();
      lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
              .setKL(4E-5).setKQ(2E-7));
      Scene scene = new Scene.SceneBuilder("Test Scene")
              .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(.15)))
              .setGeometries(new Geometries(
                      new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)), //
                      new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)), //
                      new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setkT(0.6))))
              .setLights(lights)
              .build();


      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      Camera camera = new Camera.CameraBuilder(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(200, 200)
              .setVPDistance(1000)
              .setImageWriter(imageWriter) //
              .setRayTracerBase(new RayTracerBasic(scene))
              .build();//
      camera.renderImage(); //
      camera.writeToImage();
   }


   @Test
   public void reflectionIntegrationTest1(){
      List<LightSource> lights = new LinkedList<>();
      lights.add(new SpotLight(new Color(black), new Point(-25,-100,100), new Vector(-50,75,-100)).setKL(0.0004).setKQ(0.2));
      lights.add(new DirectionalLight(new Color(blue),new Vector(-90,0,-100)));
      lights.add(new SpotLight(new Color(700, 400, 400), new Point(-25, -100, 100), new Vector(-50, 75, -100)) //
              .setKL(4E-5).setKQ(2E-5));
      lights.add(new SpotLight(new Color(BLUE), new Point(0, -25, 100), new Vector(-90, 0, -100)) //
              .setKL(4E-5).setKQ(2E-5));
//      lights.add(new SpotLight(new Color(WHITE),new Point(-50,30,0),new Vector(0,-1,0)).setKL(0.0004).setKQ(0.0000006));
      Scene scene = new Scene.SceneBuilder("Test Scene")
              .setGeometries(new Geometries(
                      new Polygon(new Point(-100,0,0),new Point(-50,0,0),new Point(-50,-50,0),new Point(-100,-50,0))
                              .setEmission(new Color(green))
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.3)),
                      new Polygon(new Point(-100,0,-10),new Point(-50,0,-10),new Point(-50,-50,-10),new Point(-100,-50,-10))
                              .setEmission(new Color(green))
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.5)),
                      new Polygon(new Point(-100,0,0),new Point(-50,0,0),new Point(-50,0,-10),new Point(-100,0,-10))
                              .setEmission(new Color(yellow))
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.5)),
                      new Polygon(new Point(-100,0,0),new Point(-100,-50,0),new Point(-100,-50,-10),new Point(-100,0,-10))
                              .setEmission(new Color(yellow))
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.5)),
                      new Polygon(new Point(-50,0,0),new Point(-50,-50,0),new Point(-50,-50,-10),new Point(-50,0,-10))
                              .setEmission(new Color(yellow))
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.5)),
                      new Polygon(new Point(-100,-50,0),new Point(-50,-50,0),new Point(-50,-50,-10),new Point(-100,-50,-10))
                              .setEmission(new Color(yellow))
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.5)),
//                      new Sphere(new Point(-75,-25,0), 10).setEmission(new Color(darkGray))
//                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.5)),
                      new Cylinder(20,5,new Ray(new Point(-75,-25,0),new Vector(0,0,1))).
                              setEmission(new Color(orange))
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.1)),
                      new Polygon(new Point(-140,10,-10),new Point(-140,-60,-10),new Point(-140,-60,40),new Point(-140,10,40))
                              .setEmission(new Color(LIGHT_GRAY))
                              .setMaterial(new Material().setKd(0.001).setKs(0.2).setnShininess(80).setkT(1).setkR(0.3))

              ))
              .setBackground(new Color(255,255,255))
              .setLights(lights)
              .setBackground(new Color(0,102d,102d))
              .build();

      ImageWriter imageWriter = new ImageWriter("refractionIntegration55", 600, 600);
      Camera camera = new Camera.CameraBuilder(new Point(100, -25, 100), new Vector(-150, 0, -100), new Vector(-1, 0,1.5 )) //
              .setVPSize(200, 200)
              .setVPDistance(200)
              .setImageWriter(imageWriter) //
              .setRayTracerBase(new RayTracerBasic(scene))
              .build();//
      camera.renderImage(); //
      camera.writeToImage();
   }
   /**
    * Produce a picture of two spheres and a cylinder - includes all effects
    * transparency and reflectiveness
    */
   @Test
   public void reflectionIntegrationTest(){
      List<LightSource> lights = new LinkedList<>();
      lights.add(new SpotLight(new Color(WHITE),new Point(50,-30,200),new Vector(-1,0,-1)).setKL(0.0004).setKQ(0.0000006));
      lights.add(new SpotLight(new Color(WHITE),new Point(-75,30,200),new Vector(1,0,-0.55)).setKL(0.0004).setKQ(0.0000006));
      Scene scene = new Scene.SceneBuilder("Test Scene")
              .setAmbientLight(new AmbientLight(new Color(229,204,255), new Double3(.15)))
              .setGeometries(new Geometries(
                      new Sphere(new Point(-15,-10,-10),25d).setEmission(new Color(200,50,0))
                              .setMaterial(new Material().setKs(0.25).setKd(0.25).setnShininess(80).setkT(0.5)),
                      new Polygon(new Point(-250,-90,-70),new Point(0,50,-60),new Point(200,-90,-70))
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
                      new Polygon(new Point(-150,90,-65),new Point(-150,90,50),new Point(150,160,50),new Point(150,160,-65))
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setkR(0.45)),
                      new Sphere(new Point(-6,2,-2),7).setMaterial(new Material().setKs(0.25).setKd(0.25)).setEmission(new Color(0,255,0)),


                      new Cylinder(10d,130d,new Ray(new Point(75,-8,-88),new Vector(-0.2,-0.3,1)))
                              .setEmission(new Color(102,0,204))
                              .setMaterial(new Material().setKs(0.35).setKd(0.25).setkT(0.2).setkR(0).setnShininess(10))))

              .setLights(lights)
              .setBackground(new Color(0,102d,102d))
              .build();



      ImageWriter imageWriter = new ImageWriter("refractionIntegration4", 600, 600);
      Camera camera = new Camera.CameraBuilder(new Point(0, -1200, 100), new Vector(0, 1, -0.1), new Vector(0, 0.1,1 )) //
              .setVPSize(200, 200)
              .setVPDistance(1000)
              .setImageWriter(imageWriter) //
              .setRayTracerBase(new RayTracerBasic(scene))
              .build();//
      camera.renderImage(); //
      camera.writeToImage();
   }


}