package renderer;

import geometries.Cylinder;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Polygon;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.LightSource;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
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

    public Geometry dice ( double a1, double a2, double a3, double b1, double b2, double b3, double c1,
                              double c2, double c3, double d1, double d2, double d3){
        return new Polygon(new Point(a1, a2, a3), new Point(b1, b2, b3), new Point(c1, c2, c3), new Point(d1, d2, d3))
                .setEmission(new Color(255, 255, 255))
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

    public List<Geometry> black_pices_p(double a, double b, double c, int amount){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < amount; i++){
            cilinders.add(new Cylinder(20,5,new Ray(new Point(a,b + i * 40+ 1,c),new Vector(0,0,1))).setEmission(new Color(BLACK))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public List<Geometry> black_pices_m(double a, double b, double c, int amount){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < amount; i++){
            cilinders.add(new Cylinder(20,5,new Ray(new Point(a,b - i * 40 - 1,c),new Vector(0,0,1))).setEmission(new Color(BLACK))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public List<Geometry> white_pices_p(double a, double b, double c, int amount){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < amount; i++){
            cilinders.add(new Cylinder(20,5,new Ray(new Point(a,b + i * 40+ 1,c),new Vector(0,0,1))).setEmission(new Color(255,255,204))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public List<Geometry> dice_5_dots(double a, double b, double c){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < 2; i++){
                    cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a + i * 11 + 3,b + 4,c),new Vector(0,0,1))).setEmission(new Color(black)).setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
                    cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a + i * 11 + 3,b + 15,c),new Vector(0,0,1))).setEmission(new Color(black))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        cilinders.add(new Cylinder(2,1,new Ray(new Point(a+9,b+9,c),new Vector(0,0,1))).setEmission(new Color(black)).setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        return cilinders;
    }

    public List<Geometry> dice_6_dots(double a, double b, double c){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < 3; i++){
            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a + i * 6+ 2,b + 3,c),new Vector(0,0,1))).setEmission(new Color(black)).setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a + i * 6+ 2,b + 13,c),new Vector(0,0,1))).setEmission(new Color(black))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public List<Geometry> dice_4_dots(double a, double b, double c){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < 2; i++){
            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a ,b + 3,c + i * 8 + 3),new Vector(0,0,1))).setEmission(new Color(black)).setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a ,b + 13,c+ i * 8 + 3),new Vector(0,0,1))).setEmission(new Color(black))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public List<Geometry> dice_2_dots(double a, double b, double c){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < 2; i++){
            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a + i * 11 + 4,b - 1 ,c + i * 11 + 3),new Vector(0,0,1))).setEmission(new Color(black)).setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
//            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a ,b + 13,c+ i * 8 + 3),new Vector(0,0,1))).setEmission(new Color(black))
//                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public List<Geometry> dice_3_dots(double a, double b, double c){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < 3; i++){
            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a + 1 ,b+ i * 5 + 4 ,c + i * 5 + 3),new Vector(0,0,1))).setEmission(new Color(black)).setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
//            cilinders.add(new Cylinder(2, 1 ,new Ray(new Point(a ,b + 13,c+ i * 8 + 3),new Vector(0,0,1))).setEmission(new Color(black))
//                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public List<Geometry> white_pices_m(double a, double b, double c, int amount){
        List<Geometry> cilinders = new LinkedList<>();
        for(int i = 0; i < amount; i++){
            cilinders.add(new Cylinder(20,5,new Ray(new Point(a,b - i * 40 - 1,c),new Vector(0,0,1))).setEmission(new Color(255,255,204))
                    .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0)));
        }
        return cilinders;
    }

    public  List<LightSource> lights = new LinkedList<>();

    private Geometry bord = new Polygon(
            new Point(-5, 0, 0),
            new Point(-655, 0, 0),
            new Point(-655, 500, 0),
            new Point(-5, 500, 0)).setEmission(new Color(210, 180, 140))
            .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(60).setkT(0.3));

    private List<Geometry> triangul_1_red = triangulRed(-18, 13, 1, -68, 13, 1, -43, 163, 1);
    private List<Geometry> triangul_1_black = triangulBlack(-68, 13, 1, -118, 13, 1, -93, 163, 1);

    private List<Geometry> triangul_2_red = triangulRed(-345, 13, 1, -395, 13, 1, -370, 163, 1);
    private List<Geometry> triangul_2_black = triangulBlack(-395, 13, 1, -445, 13, 1, -420, 163, 1);

    private List<Geometry> triangul_3_red = triangulRed(-68, 488, 1, -118, 488, 1, -93, 338, 1);
    private List<Geometry> triangul_3_black = triangulBlack(-18, 488, 1, -68, 488, 1, -43, 338, 1);

    private List<Geometry> triangul_4_red = triangulRed(-395, 488, 1, -445, 488, 1, -420, 338, 1);
    private List<Geometry> triangul_4_black = triangulBlack(-345, 488, 1, -395, 488, 1, -370, 338, 1);

    private Geometry R_border_1_buttom = borders(-5, 0, 0, -18, 0, 0, -18, 500, 0, -5, 500, 0);
    private Geometry R_border_1_border_top = borders(-5, 0, 10, -18, 0, 10, -18, 500, 10, -5, 500, 10);
    private Geometry R_border_1_border_right = borders(-5, 0, 0, -5, 0, 10, -5, 500, 10, -5, 500, 0);
    private Geometry R_border_1_border_left = borders(-18, 0, 0, -18, 0, 10, -18, 500, 10, -18, 500, 0);
    private Geometry R_border_1_border_down = borders(-5, 0, 0, -18, 0, 0, -18, 0, 10, -5, 0, 10);
    private Geometry R_border_1_border_up = borders(-5, 500, 0, -18, 500, 0, -17.5, 500, 10, -5, 500, 10);

    private Geometry D_border_2_buttom = borders(-18, 0, 0, -318, 0, 0, -318, 13, 0, -18, 13, 0);
    private Geometry D_border_2_border_top = borders(-18, 0, 10, -318, 0, 10, -318, 13, 10, -18, 13, 10);
    private Geometry D_border_2_border_right = borders(-18, 13, 0, -318, 13, 0, -318, 13, 10, -18, 13, 10);
    private Geometry D_border_2_border_left = borders(-18, 0, 0, -318, 0, 0, -318, 0, 10, -18, 0, 10);

    private Geometry MR_border_3_buttom = borders(-318, 0, 0, -331, 0, 0, -331, 500, 0, -318, 500, 0);
    private Geometry MR_border_3_border_top = borders(-318, 0, 10, -331, 0, 10, -331, 500, 10, -318, 500, 10);
    private Geometry MR_border_3_border_right = borders(-318, 0, 0, -318, 0, 10, -318, 500, 10, -318, 500, 0);
    private Geometry MR_border_3_border_left = borders(-331, 0, 0, -331, 0, 10, -331, 500, 10, -331, 500, 0);
    private Geometry MR_border_3_border_down = borders(-318, 0, 0, -331, 0, 0, -331, 0, 10, -318, 0, 10);
    private Geometry MR_border_3_border_up = borders(-318, 500, 0, -331, 500, 0, -331, 500, 10, -318, 500, 10);

    private Geometry U_border_4_buttom = borders(-18, 488, 0, -318, 488, 0, -318, 500, 0, -18, 500, 0);
    private Geometry U_border_4_border_top = borders(-18, 488, 10, -318, 488, 10, -318, 500, 10, -18, 500, 10);
    private Geometry U_border_4_border_right = borders(-18, 500, 0, -318, 500, 0, -318, 500, 10, -18, 500, 10);
    private Geometry U_border_4_border_left = borders(-18, 488, 0, -318, 488, 0, -318, 488, 10, -18, 488, 10);

    private Geometry ML_border_5_buttom = borders(-332, 0, 0, -345, 0, 0, -345, 500, 0, -332, 500, 0);
    private Geometry ML_border_5_border_top = borders(-332, 0, 10, -345, 0, 10, -345, 500, 10, -332, 500, 10);
    private Geometry ML_border_5_border_right = borders(-332, 0, 0, -332, 0, 10, -332, 500, 10, -332, 500, 0);
    private Geometry ML_border_5_border_left = borders(-345, 0, 0, -345, 0, 10, -345, 500, 10, -345, 500, 0);
    private Geometry ML_border_5_border_down = borders(-332, 0, 0, -345, 0, 0, -345, 0, 10, -332, 0, 10);
    private Geometry ML_border_5_border_up = borders(-332, 500, 0, -345, 500, 0, -345, 500, 10, -332, 500, 10);

    private Geometry LD_border_2_buttom = borders(-332, 0, 0, -645, 0, 0, -645, 13, 0, -332, 13, 0);
    private Geometry LD_border_2_border_top = borders(-332, 0, 10, -645, 0, 10, -645, 13, 10, -332, 13, 10);
    private Geometry LD_border_2_border_right = borders(-332, 13, 0, -645, 13, 0, -645, 13, 10, -332, 13, 10);
    private Geometry LD_border_2_border_left = borders(-332, 0, 0, -645, 0, 0, -645, 0, 10, -332, 0, 10);

    private Geometry LL_border_5_buttom = borders(-645, 0, 0, -658, 0, 0, -658, 500, 0, -645, 500, 0);
    private Geometry LL_border_5_border_top = borders(-645, 0, 10, -658, 0, 10, -658, 500, 10, -645, 500, 10);
    private Geometry LL_border_5_border_right = borders(-645, 0, 0, -645, 0, 10, -645, 500, 10, -645, 500, 0);
    private Geometry LL_border_5_border_left = borders(-658, 0, 0, -658, 0, 10, -658, 500, 10, -658, 500, 0);
    private Geometry LL_border_5_border_down = borders(-645, 0, 0, -658, 0, 0, -658, 0, 10, -645, 0, 10);
    private Geometry LL_border_5_border_up = borders(-645, 500, 0, -658, 500, 0, -658, 500, 10, -645, 500, 10);

    private Geometry LU_border_4_buttom = borders(-345, 488, 0, -645, 488, 0, -645, 500, 0, -345, 500, 0);
    private Geometry LU_border_4_border_top = borders(-345, 488, 10, -645, 488, 10, -645, 500, 10, -345, 500, 10);
    private Geometry LU_border_4_border_right = borders(-345, 500, 0, -645, 500, 0, -645, 500, 10, -345, 500, 10);
    private Geometry LU_border_4_border_left = borders(-345, 488, 0, -645, 488, 0, -645, 488, 10, -345, 488, 10);

    private List<Geometry> two_buttom_black_pices = black_pices_p(-43,33,1,2);
    private List<Geometry> five_buttom_black_pices = black_pices_p(-618,33,1,5);

    private List<Geometry> five_upper_black_pices = black_pices_m(-293, 467, 1 , 5);
    private List<Geometry> three_upper_black_pices = black_pices_m(-420, 467, 1 , 3);

    private List<Geometry> two_buttom_white_pices = white_pices_m(-43,33+449-13,1,2);
    private List<Geometry> five_buttom_white_pices = white_pices_m(-618,33+449-13,1,5);

    private List<Geometry> five_upper_white_pices = white_pices_p(-293, 467-449+13, 1 , 5);
    private List<Geometry> three_upper_white_pices = white_pices_p(-420, 467-449+13, 1 , 3);

    private Geometry dice_1_up = dice(-172,246,19,-191,246,19,-191,265,19,-172,265,19);
    private Geometry dice_1_right = dice(-172,246,19,-172,265,19,-172,265,1,-172,246,1);
    private Geometry dice_1_left = dice(-191,246,19,-191,265,19,-191,265,1,-191,246,1);
    private Geometry dice_1_front = dice(-172,246,1,-191,246,1,-191,246,19,-172,246,19);
    private Geometry dice_1_back = dice(-172,265,1,-191,265,1,-191,265,19,-172,265,19);

    private Geometry dice_2_up = dice(-50,196,19,-69,196,19,-69,215,19,-50,215,19);
    private Geometry dice_2_right = dice(-50,196,19,-50,215,19,-50,215,1,-50,196,1);
    private Geometry dice_2_left = dice(-69,196,19,-69,215,19,-69,215,1,-69,196,1);
    private Geometry dice_2_front = dice(-50,196,1,-69,196,1,-69,196,19,-50,196,19);
    private Geometry dice_2_back = dice(-50,215,1,-69,215,1,-69,215,19,-50,215,19);

    private List<Geometry> dice_5_top_dots1 = dice_5_dots(-69,196,19);

    private List<Geometry> dice_6_top_dots1 = dice_6_dots(-191,246,19);

    private List<Geometry> dice_4_top_dots1 = dice_4_dots(-172,246,1);

    private List<Geometry> dice_4_top_dots2 = dice_4_dots(-69,196,1);

    private List<Geometry> dice_2_dors2 = dice_2_dots(-69, 196, 1);

    private List<Geometry> dice_2_dors1 = dice_2_dots(-191, 265, 1);

    private List<Geometry> dice_3_dots2 = dice_3_dots(-50,196,1);

    private List<Geometry> dice_3_dots1 = dice_3_dots(-191,246,1);

    private Geometry dice_1_dot1 = new Cylinder(2,1,new Ray(new Point(-191 + 9,246 +1, 1 + 9), new Vector(0,0,1))).setEmission(new Color(black))
            .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0));

    private Geometry dice_1_dot2 = new Cylinder(2,1,new Ray(new Point(-69 + 9,215 , 1 + 9), new Vector(0,0,1))).setEmission(new Color(black))
            .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(800).setkT(0));

    public Geometries mergeGeometries(List<Geometry> list){
        Geometries geometries = new Geometries();
        for(Geometry geometry : list){
            geometries.add(geometry);
        }
        return geometries;
    }

    Geometries all_game = new Geometries(dice_2_up,
            dice_2_right,
            dice_2_left,
            dice_2_front,
            dice_2_back,
            dice_1_up,
            dice_1_right,
            dice_1_left,
            dice_1_front,
            dice_1_back,
            dice_1_dot1,
            dice_1_dot2,
            mergeGeometries(dice_3_dots2),
            mergeGeometries(dice_2_dors2),
            mergeGeometries(dice_2_dors1),
            mergeGeometries(dice_3_dots1),
            bord,
            mergeGeometries(dice_5_top_dots1),
            mergeGeometries(dice_6_top_dots1),
            mergeGeometries(dice_4_top_dots1),
            mergeGeometries(triangul_1_red),
            mergeGeometries(triangul_1_black),
            mergeGeometries(triangul_2_red),
            mergeGeometries(triangul_2_black),
            mergeGeometries(triangul_3_red),
            mergeGeometries(triangul_3_black),
            mergeGeometries(triangul_4_red),
            mergeGeometries(triangul_4_black),
            mergeGeometries(dice_4_top_dots2),
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
            U_border_4_border_left,
            ML_border_5_buttom,
            ML_border_5_border_top,
            ML_border_5_border_right,
            ML_border_5_border_left,
            ML_border_5_border_down,
            ML_border_5_border_up,
            LD_border_2_buttom,
            LD_border_2_border_top,
            LD_border_2_border_right,
            LD_border_2_border_left,
            LL_border_5_buttom,
            LL_border_5_border_top,
            LL_border_5_border_right,
            LL_border_5_border_left,
            LL_border_5_border_down,
            LL_border_5_border_up,
            LU_border_4_buttom,
            LU_border_4_border_top,
            LU_border_4_border_right,
            LU_border_4_border_left,
            mergeGeometries(two_buttom_black_pices),
            mergeGeometries(five_buttom_black_pices),
            mergeGeometries(five_upper_black_pices),
            mergeGeometries(three_upper_black_pices),
            mergeGeometries(two_buttom_black_pices),
            mergeGeometries(five_buttom_black_pices),
            mergeGeometries(five_upper_black_pices),
            mergeGeometries(three_upper_black_pices),
            mergeGeometries(two_buttom_white_pices),
            mergeGeometries(five_buttom_white_pices),
            mergeGeometries(five_upper_white_pices),
            mergeGeometries(three_upper_white_pices));

    @Test
    public void gameTestUp() {
        lights.clear();
        lights.add(new SpotLight(new Color(BLUE), new Point(-325, 250, 0), new Vector(0, 0, -200)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new SpotLight(new Color(WHITE), new Point(-175, 240, 20), new Vector(-1, 0, -1)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,0,-1)));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,1,-1)));


//        Scene scene = new Scene.SceneBuilder("game")
////                .setAmbientLight(new AmbientLight(new Color(96,96,96), new Double3(.15)))
//                .setGeometries(new Geometries(
//                        dice_2_up,
//                        dice_2_right,
//                        dice_2_left,
//                        dice_2_front,
//                        dice_2_back,
//                        dice_1_up,
//                        dice_1_right,
//                        dice_1_left,
//                        dice_1_front,
//                        dice_1_back,
//                        dice_1_dot1,
//                        dice_1_dot2,
//                        dice_3_dots2.get(0),
//                        dice_3_dots2.get(1),
//                        dice_3_dots2.get(2),
//                        dice_2_dors2.get(0),
//                        dice_2_dors2.get(1),
//                        dice_2_dors1.get(0),
//                        dice_2_dors1.get(1),
//                        dice_3_dots1.get(0),
//                        dice_3_dots1.get(1),
//                        dice_3_dots1.get(2),
//                        bord,
//                        dice_5_top_dots1.get(0),
//                        dice_5_top_dots1.get(1),
//                        dice_5_top_dots1.get(2),
//                        dice_5_top_dots1.get(3),
//                        dice_5_top_dots1.get(4),
//                        dice_6_top_dots1.get(0),
//                        dice_6_top_dots1.get(1),
//                        dice_6_top_dots1.get(2),
//                        dice_6_top_dots1.get(3),
//                        dice_6_top_dots1.get(4),
//                        dice_6_top_dots1.get(5),
//                        dice_4_top_dots1.get(0),
//                        dice_4_top_dots1.get(1),
//                        dice_4_top_dots1.get(2),
//                        dice_4_top_dots1.get(3),
//                        triangul_1_red.get(0),
//                        triangul_1_red.get(1),
//                        triangul_1_red.get(2),
//                        triangul_1_black.get(0),
//                        triangul_1_black.get(1),
//                        triangul_1_black.get(2),
//                        triangul_2_red.get(0),
//                        triangul_2_red.get(1),
//                        triangul_2_red.get(2),
//                        triangul_2_black.get(0),
//                        triangul_2_black.get(1),
//                        triangul_2_black.get(2),
//                        triangul_3_red.get(0),
//                        triangul_3_red.get(1),
//                        triangul_3_red.get(2),
//                        triangul_3_black.get(0),
//                        triangul_3_black.get(1),
//                        triangul_3_black.get(2),
//                        triangul_4_red.get(0),
//                        triangul_4_red.get(1),
//                        triangul_4_red.get(2),
//                        triangul_4_black.get(0),
//                        triangul_4_black.get(1),
//                        triangul_4_black.get(2),
//                        dice_4_top_dots2.get(0),
//                        dice_4_top_dots2.get(1),
//                        dice_4_top_dots2.get(2),
//                        dice_4_top_dots2.get(3),
//                        R_border_1_border_up,
//                        R_border_1_border_down,
//                        R_border_1_border_left,
//                        R_border_1_border_right,
//                        R_border_1_border_top,
//                        R_border_1_buttom,
//                        D_border_2_buttom,
//                        D_border_2_border_top,
//                        D_border_2_border_right,
//                        D_border_2_border_left,
//                        MR_border_3_buttom,
//                        MR_border_3_border_top,
//                        MR_border_3_border_right,
//                        MR_border_3_border_left,
//                        MR_border_3_border_down,
//                        MR_border_3_border_up,
//                        U_border_4_buttom,
//                        U_border_4_border_top,
//                        U_border_4_border_right,
//                        U_border_4_border_left,
//                        ML_border_5_buttom,
//                        ML_border_5_border_top,
//                        ML_border_5_border_right,
//                        ML_border_5_border_left,
//                        ML_border_5_border_down,
//                        ML_border_5_border_up,
//                        LD_border_2_buttom,
//                        LD_border_2_border_top,
//                        LD_border_2_border_right,
//                        LD_border_2_border_left,
//                        LL_border_5_buttom,
//                        LL_border_5_border_top,
//                        LL_border_5_border_right,
//                        LL_border_5_border_left,
//                        LL_border_5_border_down,
//                        LL_border_5_border_up,
//                        LU_border_4_buttom,
//                        LU_border_4_border_top,
//                        LU_border_4_border_right,
//                        LU_border_4_border_left,
//                        two_buttom_black_pices.get(0),
//                        two_buttom_black_pices.get(1),
//                        five_buttom_black_pices.get(0),
//                        five_buttom_black_pices.get(1),
//                        five_buttom_black_pices.get(2),
//                        five_buttom_black_pices.get(3),
//                        five_buttom_black_pices.get(4),
//                        five_upper_black_pices.get(0),
//                        five_upper_black_pices.get(1),
//                        five_upper_black_pices.get(2),
//                        five_upper_black_pices.get(3),
//                        five_upper_black_pices.get(4),
//                        three_upper_black_pices.get(0),
//                        three_upper_black_pices.get(1),
//                        three_upper_black_pices.get(2),
//                        two_buttom_black_pices.get(0),
//                        two_buttom_black_pices.get(1),
//                        five_buttom_black_pices.get(0),
//                        five_buttom_black_pices.get(1),
//                        five_buttom_black_pices.get(2),
//                        five_buttom_black_pices.get(3),
//                        five_buttom_black_pices.get(4),
//                        five_upper_black_pices.get(0),
//                        five_upper_black_pices.get(1),
//                        five_upper_black_pices.get(2),
//                        five_upper_black_pices.get(3),
//                        five_upper_black_pices.get(4),
//                        three_upper_black_pices.get(0),
//                        three_upper_black_pices.get(1),
//                        three_upper_black_pices.get(2),
//                        two_buttom_white_pices.get(0),
//                        two_buttom_white_pices.get(1),
//                        five_buttom_white_pices.get(0),
//                        five_buttom_white_pices.get(1),
//                        five_buttom_white_pices.get(2),
//                        five_buttom_white_pices.get(3),
//                        five_buttom_white_pices.get(4),
//                        five_upper_white_pices.get(0),
//                        five_upper_white_pices.get(1),
//                        five_upper_white_pices.get(2),
//                        five_upper_white_pices.get(3),
//                        five_upper_white_pices.get(4),
//                        three_upper_white_pices.get(0),
//                        three_upper_white_pices.get(1),
//                        three_upper_white_pices.get(2)
//                        ))
//                .setBackground(new Color(255, 255, 255))
//                .setLights(lights)
//                .setBackground(new Color(0, 102d, 102d))
//                .build();

        Scene scene = new Scene.SceneBuilder("game")
//                .setAmbientLight(new AmbientLight(new Color(96,96,96), new Double3(.15)))
                .setGeometries(all_game
                )
                .setBackground(new Color(255, 255, 255))
                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("BackgammonUpView", 600, 600);

        Camera camera = new Camera.CameraBuilder(new Point(-325, 250, 700), new Vector(0, 0, -700), new Vector(0, 1, 0)) //
                .setVPSize(200, 200)
                .setVPDistance(200)
                .setImageWriter(imageWriter) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .setAntiAliasing(AntiAliasing.RANDOM).setM(20).setN(20)
                .build();
        camera.renderImage(); //
        camera.writeToImage();


    }

    @Test
    public void gameTestUpNoAntiAliasing() {
        lights.clear();
        lights.add(new SpotLight(new Color(BLUE), new Point(-325, 250, 0), new Vector(0, 0, -200)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new SpotLight(new Color(WHITE), new Point(-175, 240, 20), new Vector(-1, 0, -1)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,0,-1)));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,1,-1)));


        Scene scene = new Scene.SceneBuilder("game")
//                .setAmbientLight(new AmbientLight(new Color(96,96,96), new Double3(.15)))
                .setGeometries(all_game
                )
                .setBackground(new Color(255, 255, 255))
                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("BackgammonUpViewNoAntiAliasing", 600, 600);

        Camera camera = new Camera.CameraBuilder(new Point(-325, 250, 700), new Vector(0, 0, -700), new Vector(0, 1, 0)) //
                .setVPSize(200, 200)
                .setVPDistance(200)
                .setImageWriter(imageWriter) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .build();
        camera.renderImage(); //
        camera.writeToImage();


    }

    @Test
    public void gameTestSide1() {
        lights.clear();
        lights.add(new SpotLight(new Color(BLUE), new Point(-325, 250, 0), new Vector(0, 0, -200)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new SpotLight(new Color(WHITE), new Point(-175, 240, 20), new Vector(-1, 0, -1)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,0,-1)));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,1,-1)));

        Scene scene = new Scene.SceneBuilder("game")
//                .setAmbientLight(new AmbientLight(new Color(96,96,96), new Double3(.15)))
                .setGeometries(all_game
                )
                .setBackground(new Color(255, 255, 255))
                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("BackgammonSide1View", 600, 600);
        Camera camera = new Camera.CameraBuilder(new Point(200, -400, 400), new Vector(-525, 650, -400), new Vector(-1, 0, 1.3125)) //
                .setVPSize(200, 200)
                .setVPDistance(200)
                .setImageWriter(imageWriter) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .setAntiAliasing(AntiAliasing.ADAPTIVE).setM(9).setN(9)
                .build();//

        camera.renderImage(); //
        camera.writeToImage();


    }

    @Test
    public void gameTestSide1NoAntiAliasing() {
        lights.clear();
        lights.add(new SpotLight(new Color(BLUE), new Point(-325, 250, 0), new Vector(0, 0, -200)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new SpotLight(new Color(WHITE), new Point(-175, 240, 20), new Vector(-1, 0, -1)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,0,-1)));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,1,-1)));

        Scene scene = new Scene.SceneBuilder("game")
//                .setAmbientLight(new AmbientLight(new Color(96,96,96), new Double3(.15)))
                .setGeometries(all_game
                )
                .setBackground(new Color(255, 255, 255))
                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("BackgammonSide1ViewNoAntiAliasing", 600, 600);
        Camera camera = new Camera.CameraBuilder(new Point(200, -400, 400), new Vector(-525, 650, -400), new Vector(-1, 0, 1.3125)) //
                .setVPSize(200, 200)
                .setVPDistance(200)
                .setImageWriter(imageWriter) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .build();//

        camera.renderImage(); //
        camera.writeToImage();


    }

    @Test
    public void gameTestSide2() {
        lights.clear();
        lights.add(new SpotLight(new Color(BLUE), new Point(-325, 250, 0), new Vector(0, 0, -200)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new SpotLight(new Color(WHITE), new Point(-175, 240, 20), new Vector(-1, 0, -1)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,0,-1)));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,1,-1)));

        Scene scene = new Scene.SceneBuilder("game")
//                .setAmbientLight(new AmbientLight(new Color(96,96,96), new Double3(.15)))
                .setGeometries(all_game
                )
                .setBackground(new Color(255, 255, 255))
                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("BackgammonSide2View", 600, 600);

        Camera camera = new Camera.CameraBuilder(new Point(200, -200, 200), new Vector(-600, 500, -200), new Vector(-1, 0, 3)) //
                .setVPSize(200, 200)
                .setVPDistance(200)
                .setImageWriter(imageWriter) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .setAntiAliasing(AntiAliasing.CORNERS).setM(9).setN(9)
                .build();//

        camera.renderImage(); //
        camera.writeToImage();


    }

    @Test
    public void gameTestSide2NoAntiAliasing() {
        lights.clear();
        lights.add(new SpotLight(new Color(BLUE), new Point(-325, 250, 0), new Vector(0, 0, -200)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new SpotLight(new Color(WHITE), new Point(-175, 240, 20), new Vector(-1, 0, -1)) //
                .setKL(4E-5).setKQ(2E-5));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,0,-1)));
        lights.add(new DirectionalLight(new Color(160,160,160),new Vector(-1,1,-1)));

        Scene scene = new Scene.SceneBuilder("game")
//                .setAmbientLight(new AmbientLight(new Color(96,96,96), new Double3(.15)))
                .setGeometries(all_game
                )
                .setBackground(new Color(255, 255, 255))
                .setLights(lights)
                .setBackground(new Color(0, 102d, 102d))
                .build();

        ImageWriter imageWriter = new ImageWriter("BackgammonSide2ViewNoAntiAliasing", 600, 600);

        Camera camera = new Camera.CameraBuilder(new Point(200, -200, 200), new Vector(-600, 500, -200), new Vector(-1, 0, 3)) //
                .setVPSize(200, 200)
                .setVPDistance(200)
                .setImageWriter(imageWriter) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .build();//

        camera.renderImage(); //
        camera.writeToImage();


    }


}
