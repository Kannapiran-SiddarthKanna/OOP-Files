package eecs2030.test1;

import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;
import java.util.Random;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GrayTest {

    @Test
    public void test01_ctorDouble() {
        Gray g = new Gray(0.4999);
        assertEquals(0.4999, g.asDouble(), 1e-9);
        
        g = new Gray(0.0);
        assertEquals(0.0, g.asDouble(), 1e-9);
        
        g = new Gray(1.0);
        assertEquals(1.0, g.asDouble(), 1e-9);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void test02_ctorDoubleThrows() {
        new Gray(-0.001);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void test03_ctorDoubleThrows() {
        new Gray(1.001);
    }
    
    @Test
    public void test04_ctorInt() {
        Gray g = new Gray(128);
        assertEquals(128, g.asInt());
        
        g = new Gray(0);
        assertEquals(0, g.asInt());
        
        g = new Gray(255);
        assertEquals(255, g.asInt());
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void test05_ctorIntThrows() {
        new Gray(-1);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void test06_ctorIntThrows() {
        new Gray(256);
    }
    
    @Test
    public void test07_copyCtor() {
        Gray g = new Gray(128);
        Gray copy = new Gray(g);
        assertEquals(128, copy.asInt());
        
        g = new Gray(0.33333333333);
        copy = new Gray(g);
        assertEquals(0.33333333333, copy.asDouble(), 1e-9);
    }
    
    @Test
    public void test08_asInt() {
        test_asInt(0.0);
        test_asInt(1.0);
        for (int i = 0; i < 100; i++) {
            test_asInt(Math.random());
        }
    }
    
    private void test_asInt(double val) {
        int exp = (int) Math.round(val * 255);
        Gray g = new Gray(val);
        assertEquals(exp, g.asInt());
    }
    
    @Test
    public void test09_asDouble() {
        test_asDouble(0);
        test_asDouble(255);
        Random rng = new Random(1);
        for (int i = 0; i < 100; i++) {
            test_asDouble(rng.nextInt(256));
        }
    }
    
    private void test_asDouble(int val) {
        double exp = val / 255.0;
        Gray g = new Gray(val);
        assertEquals(exp, g.asDouble(), 1e-9);
    }
    
    @Test
    public void test10_toDouble() {
        test_toDouble(0);
        test_toDouble(255);
        Random rng = new Random(1);
        for (int i = 0; i < 100; i++) {
            test_toDouble(rng.nextInt(256));
        }
    }
    
    private void test_toDouble(int val) {
        double exp = val / 255.0;
        assertEquals(exp, Gray.toDouble(val), 1e-9);
    }
    
    @Test
    public void test11_toInt() {
        test_toInt(0.0);
        test_toInt(1.0);
        for (int i = 0; i < 100; i++) {
            test_toInt(Math.random());
        }
    }
    
    private void test_toInt(double val) {
        int exp = (int) Math.round(val * 255);
        assertEquals(exp, Gray.toInt(val));
    }
    
    @Test
    public void test12_fromRGB() {
        test_fromRGB(0, 0, 0);
        test_fromRGB(255, 255, 255);
        Random rng = new Random(1);
        for (int i = 0; i < 100; i++) {
            test_fromRGB(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
        }
    }
    
    private void test_fromRGB(int r, int g, int b) {
        double red = (double) r / Gray.MAX_INT_VALUE;
        double green = (double) g / Gray.MAX_INT_VALUE;
        double blue = (double) b / Gray.MAX_INT_VALUE;
        double exp = 0.299 * red + 0.587 * green + 0.114 * blue;
        Gray y = Gray.fromRGB(new Color(r, g, b));
        assertEquals(exp, y.asDouble(), 1e-9);
    }
    
    
    @Test
    public void test13_toString() {
        Random rng = new Random(1);
        for (int i = 0; i < 100; i++) {
            int val = rng.nextInt(256);
            Gray g = new Gray(val);
            assertEquals("" + val, g.toString());
        }
        for (int i = 0; i < 100; i++) {
            double val = rng.nextDouble();
            Gray g = new Gray(val);
            assertEquals("" + val, g.toString());
        }
    }
    
    
    @Test
    public void test14_compareTo() {
        for (int i = 1; i < 255; i++) {
            Gray sm = new Gray((i - 1.0) / 255);
            Gray med = new Gray((i + 0.0) / 255);
            Gray lg = new Gray((i + 1.0) / 255);
            assertEquals(-1, sm.compareTo(med));
            assertEquals(-1, sm.compareTo(lg));
            assertEquals(-1, med.compareTo(lg));
            assertEquals(0, sm.compareTo(sm));
            assertEquals(0, med.compareTo(med));
            assertEquals(0, lg.compareTo(lg));
            assertEquals(1, med.compareTo(sm));
            assertEquals(1, lg.compareTo(med));
            assertEquals(1, lg.compareTo(sm));
        }
    }
    
    @Test
    public void test15_equals() {
        Gray g = new Gray(0.0);
        assertNotEquals(g, null);
        assertNotEquals(g, "hello");
        
        Gray h = new Gray(0);
        assertEquals(g, g);
        assertEquals(g, h);
        
        g = new Gray(1.0);
        h = new Gray(255);
        assertEquals(g, g);
        assertEquals(g, h);
        
        g = new Gray(0.4135);
        h = new Gray(105);
        assertEquals(g, g);
        assertEquals(g, h);
        
        h = new Gray(0.4133);
        assertEquals(g, h);
        
        h = new Gray(0.41);
        assertEquals(g, h);
        
        h = new Gray(0.409);
        assertNotEquals(g, h);
        
        g = new Gray(5);
        h = new Gray(6);
        assertNotEquals(g, h);
    }
}