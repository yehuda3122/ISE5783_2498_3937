package primitives;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;


class VectorTest {

    @org.junit.jupiter.api.Test
    void testadd() {
        Vector v1 = new Vector(1,2,3);
        assertEquals(
                new Vector(2,4,6),
                v1.add(new Vector(1,2,3)),
                "ERROR: Vector + Vector does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void testscale() {
        Vector v1 = new Vector(2,4,6);
        assertEquals(
                new Vector(4,8,12),
                v1.scale(2),
                "ERROR: Vector scale does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void testlength() {
        Vector v1 = new Vector(4,0,0);
        assertEquals(
                4,
                v1.length(),
                "ERROR: Vector length does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void testlengthSquared() {
        Vector v1 = new Vector(4,2,3);
        assertEquals(
                29,
                v1.lengthSquared(),
                "ERROR: Vector lengthSquared does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void testnormalize() {
        Vector v1 = new Vector(4,0,0);
        assertEquals(
                new Vector(1,0,0),
                v1.normalize(),
                "ERROR: Vector normalize does not work correctly" );
    }

    @org.junit.jupiter.api.Test
    void testdotProduct() {
        Vector v1 = new Vector(1,2,3);
        assertEquals(
                10,
                v1.dotProduct(new Vector(3,2,1)),
                "ERROR: Vector dotProduct does not work correctly" );
    }

    @org.junit.jupiter.api.Test
    void testcrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

// ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);
        assertEquals(v1.length()*v2.length(),
                vr.length(),
                0.00001,
                "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");
    }

}