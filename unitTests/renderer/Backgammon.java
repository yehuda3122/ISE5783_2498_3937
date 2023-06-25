package renderer;

import geometries.Geometries;
import geometries.Geometry;
import geometries.Polygon;
import lighting.LightSource;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.*;

public class Backgammon {
    public Geometry borders ( double a1, double a2, double a3, double b1, double b2, double b3, double c1,
                              double c2, double c3, double d1, double d2, double d3){
        return new Polygon(new Point(a1, a2, a3), new Point(b1, b2, b3), new Point(c1, c2, c3), new Point(d1, d2, d3))
                .setEmission(new Color(210, 180, 140))
                .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.3));
    }

    public List<Geometry> triangulRed (double a1, double a2, double a3, double b1, double b2, double b3, double c1,
                                    double c2, double c3){
        List<Geometry> poligons = new LinkedList<>();
        for (int i = 0; i < 3; i++){
            poligons.add(new Polygon(new Point(a1 - 100*i, a2, a3), new Point(b1 - 100*i, b2, b3), new Point(c1 - 100*i, c2, c3)).setEmission(new Color(RED))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.3)));
        }
        return poligons;
    }
    public List<Geometry> triangulBlack (double a1, double a2, double a3, double b1, double b2, double b3, double c1,
                                       double c2, double c3){
        List<Geometry> poligons = new LinkedList<>();
        for (int i = 0; i < 3; i++){
            poligons.add(new Polygon(new Point(a1 - 100*i, a2, a3), new Point(b1 - 100*i, b2, b3), new Point(c1 - 100*i, c2, c3)).setEmission(new Color(BLACK))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.3)));
        }
        return poligons;
    }

    @Test
    public void gameTest() {
        List<LightSource> lights = new LinkedList<>();
        lights.add(new SpotLight(new Color(BLUE), new Point(-325, 250, 0), new Vector(0, 0, -200)) //
                .setKL(4E-5).setKQ(2E-5));

        Geometry bord = new Polygon(
                new Point(-5, 0, 0),
                new Point(-655, 0, 0),
                new Point(-655, 500, 0),
                new Point(-5, 500, 0)).setEmission(new Color(210, 180, 140))
                .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(80).setkT(0.3));

         List<Geometry> triangul1 = triangulRed(-17.5, 12.5, 1, -67.5, 12.5, 1, -42.5, 162.5, 1);
        List<Geometry> triangul2 = triangulBlack(-67.5, 12.5, 1, -117.5, 12.5, 1, -92.5, 162.5, 1);

         Geometry R_border_1_buttom = borders(-5, 0, 0, -17.5, 0, 0, -17.5, 500, 0, -5, 500, 0);
        Geometry R_border_1_border_top = borders(-5, 0, 10, -17.5, 0, 10, -17.5, 500, 10, -5, 500, 10);
        Geometry R_border_1_border_right = borders(-5, 0, 0, -5, 0, 10, -5, 500, 10, -5, 500, 0);
         Geometry R_border_1_border_left = borders(-17.5, 0, 0, -17.5, 0, 10, -17.5, 500, 10, -17.5, 500, 0);
         Geometry R_border_1_border_down = borders(-5, 0, 0, -17.5, 0, 0, -17.5, 0, 10, -5, 0, 10);
         Geometry R_border_1_border_up = borders(-5, 500, 0, -17.5, 500, 0, -17.5, 500, 10, -5, 500, 10);

        Geometry D_border_2_buttom = borders(-17.5, 0, 0, -312, 0, 0, -312, 12.5, 0, -17.5, 12.5, 0);
        Geometry D_border_2_border_top = borders(-17.5, 0, 10, -312, 0, 10, -312, 12.5, 10, -17.5, 12.5, 10);
        Geometry D_border_2_border_right = borders(-17.5, 12.5, 0, -312, 12.5, 0, -312, 12.5, 10, -17.5, 12.5, 10);
        Geometry D_border_2_border_left = borders(-17.5, 0, 0, -312, 0, 0, -312, 0, 10, -17.5, 0, 10);

        Geometry MR_border_3_buttom = borders(-312, 0, 0, -324.5, 0, 0, -324.5, 500, 0, -312, 500, 0);
        Geometry MR_border_3_border_top = borders(-312, 0, 10, -324.5, 0, 10, -324.5, 500, 10, -312, 500, 10);
        Geometry MR_border_3_border_right = borders(-312, 0, 0, -312, 0, 10, -312, 500, 10, -312, 500, 0);
        Geometry MR_border_3_border_left = borders(-324.5, 0, 0, -324.5, 0, 10, -324.5, 500, 10, -324.5, 500, 0);
        Geometry MR_border_3_border_down = borders(-312, 0, 0, -324.5, 0, 0, -324.5, 0, 10, -312, 0, 10);
        Geometry MR_border_3_border_up = borders(-312, 500, 0, -324.5, 500, 0, -324.5, 500, 10, -312, 500, 10);

        Geometry U_border_4_buttom = borders(-17.5, 487.5, 0, -312, 487.5, 0, -312, 500, 0, -17.5, 500, 0);
        Geometry U_border_4_border_top = borders(-17.5, 487.5, 10, -312, 487.5, 10, -312, 500, 10, -17.5, 500, 10);
        Geometry U_border_4_border_right = borders(-17.5, 500, 0, -312, 500, 0, -312, 500, 10, -17.5, 500, 10);
        Geometry U_border_4_border_left = borders(-17.5, 487.5, 0, -312, 487.5, 0, -312, 487.5, 10, -17.5, 487.5, 10);

        Scene scene = new Scene.SceneBuilder("game")
                .setGeometries(new Geometries(
                        bord,
                        triangul1.get(0),
                        triangul1.get(1),
                        triangul1.get(2),
                        triangul2.get(0),
                        triangul2.get(1),
                        triangul2.get(2),
                        R_border_1_border_up,
                        R_border_1_border_down,
                        R_border_1_border_left,
                        R_border_1_border_right,
                        R_border_1_border_top,
                        R_border_1_buttom,
                        D_border_2_buttom,
                        D_border_2_border_top,
                        D_border_2_border_right,
                        D_border_2_border_left,
                        MR_border_3_buttom,
                        MR_border_3_border_top,
                        MR_border_3_border_right,
                        MR_border_3_border_left,
                        MR_border_3_border_down,
                        MR_border_3_border_up,
                        U_border_4_buttom,
                        U_border_4_border_top,
                        U_border_4_border_right,
                        U_border_4_border_left
                        ))
                .setBackground(new Color(255, 255, 255))
                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("Backgammon", 600, 600);
        Camera camera = new Camera.CameraBuilder(new Point(200, -400, 400), new Vector(-525, 650, -400), new Vector(-1, 0, 1.3125)) //
                .setVPSize(200, 200)
                .setVPDistance(200)
                .setImageWriter(imageWriter) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .build();//
        camera.renderImage(); //
        camera.writeToImage();


    }

}
