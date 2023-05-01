package geometries;
import primitives.*;

import geometries.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    Vector direction= new Vector(1,0,0);
    Ray ray=new Ray(new Point(0,0,0),direction);
    Cylinder cylinder1 = new Cylinder(1,4,ray);

    @Test
    void testGetNormalEP1() {
        assertEquals(new Vector(0,0,1), cylinder1.getNormal(new Point(3,0,1)),
                "returned normal vector is incorrect");
    }

    @Test
    void testGetNormalEP2() {
        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(0,0.5,0)),
                "returned normal vector is incorrect");
    }

    @Test
    void testGetNormalEP3() {
        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(4,0.5,0)),
                "returned normal vector is incorrect");
    }

    @Test
    void testGetNormalBVE1(){
        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(4,0,0)),
                "returned normal vector is incorrect");
    }

    @Test
    void testGetNormalBVE2(){
        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(0,0,0)),
                "returned normal vector is incorrect");
    }
}