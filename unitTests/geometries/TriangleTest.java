package geometries;
import primitives.*;

import geometries.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    Triangle tri = new Triangle(
            new Point(1,0,0),
            new Point(3,0,0),
            new Point(2,0,1));

    @Test
    void getNormal() {
// ============ Equivalence Partitions Tests ==============
        // test that normal of plane returned is equal to expected normal vector
//        double x = 9 / (7 * Math.sqrt(2));
//        double y = 1 / (7 * Math.sqrt(2));
//        double z = (-2) * Math.sqrt(2) / 7;
//
//        assertEquals(new Vector(x, y, z),
//                plane.getNormal(), "normal vector is not returned correctly");
        assertEquals(new Vector(0,-1,0), tri.getNormal(new Point(1, 0, 0.5)), "Bad normal to triangle");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersectionsEP1() {
        // TC01 ray crosses triangle
        List<Point> result = tri.findIntersections(new Ray(new Point(2,-1,0.5),new Vector(0,1,0)));
        assertEquals(1,result.size(),"ray crosses triangle once");
        assertEquals(result,List.of(new Point(2,0,0.5)),"ray crosses triangle");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersectionsEP2() {
        //TC02 -  ray does not cross triangle - against vertex
        List<Point> result = tri.findIntersections(new Ray(new Point(2,-1,1.5),new Vector(0,1,0)));
        assertNull(result,"ray does not cross triangle");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersectionsEP3() {
        // TC03 -  ray does not cross triangle - against edge
        List<Point> result = tri.findIntersections(new Ray(new Point(3,-1,0.5),new Vector(0,1,0)));
        assertNull(result,"ray does not cross triangle");
    }
    //endregion

    //region =============== Boundary Values Tests =========================================================
    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersectionsBVA1() {
        // TC04 ray intersects triangle at a vertex - 0 points
        List<Point> result = tri.findIntersections(new Ray(new Point(1,-1,0),new Vector(0,1,0)));
        assertNull(result,"ray does not cross triangle");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersectionsBVA2() {
        // TC05 ray intersects triangle at one of the triangle edges - 0 points
        List<Point> result = tri.findIntersections(new Ray(new Point(2.5,-1,0.5),new Vector(0,1,0)));
        assertNull(result,"ray does not cross triangle");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersectionsBVA3() {
        // TC03 -  ray does not cross triangle  run on "continuation" of one of edges
        List<Point> result = tri.findIntersections(new Ray(new Point(1,-1,2),new Vector(0,1,0)));
        assertNull(result,"ray does not cross triangle");
    }
    //endregion
}