package primitives;

import org.junit.jupiter.api.Test;

import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testTestEquals() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(p1.add(new Vector(-1,-2,-3)),
                new Point(0,0,0),"Point + Vector does not work correctly");
    }

    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        assertTrue(isZero(p1.distanceSquared(new Point(3,5,6))-22),
                "Distance Squared does not work correctly");
    }

    @Test
    void testDistance() {
        Point p1 = new Point(1, 2, 3);
        assertTrue(isZero(new Point(5,5,3).distance(p1)-5) ,
                "Distance does not work correctly");
    }

    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(
                new Point(0,0,0),
                p1.add(new Vector(-1,-2,-3)),
                "ERROR: Point + Vector does not work correctly");
        //p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0));
    }

    @Test
    void testSubtract() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Point(1,1,1).subtract(new Point(1,1,1)));
    }
}