package eecs2030.test1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeightTest {

    
    
    @Test
    public void test03_defaultCtor() {
        Weight t = new Weight();
        assertEquals("The default weight does not have the numeric value 0.0\n", 
                0.0, t.get(), 1e-9);
        assertEquals("The default weight does not have the units \"kg\"\n", 
                "kg", t.getUnits());
        
    }

    @Test
    public void test04_ctor() {
        double wt = 5.5;
        String units = Weight.LB;
        Weight t = new Weight(wt, units);
        assertEquals("The constructed weight has the wrong numeric value\n", 
                wt, t.get(), 1e-9);
        assertEquals("The constructed weight has the wrong units\n", 
                units, t.getUnits());
        
        units = "kg";
        t = new Weight(wt, units);
        assertEquals("The constructed weight has the wrong numeric value\n", 
                wt, t.get(), 1e-9);
        assertEquals("The constructed weight has the wrong units\n", 
                units, t.getUnits());
    }
    
    @Test
    public void test04_ctorThrows() {
        try {
            new Weight(-0.1, "lb");
            fail("ctor should have thrown an exception");
        }
        catch (IllegalArgumentException x) {}
    }
    
    @Test
    public void test05_copyCtor() {
        double wt = 5.5;
        String units = "kg";
        Weight t = new Weight(wt, units);
        Weight u = new Weight(t);
        assertEquals("The copied weight has the wrong numeric value\n", 
                wt, u.get(), 1e-9);
        assertEquals("The copied weight has the wrong units\n", 
                units, u.getUnits());
        
        units = "lb";
        t = new Weight(wt, units);
        u = new Weight(t);
        assertEquals("The copied weight has the wrong numeric value\n", 
                wt, u.get(), 1e-9);
        assertEquals("The copied weight has the wrong units\n", 
                units, u.getUnits());
    }
    
    @Test
    public void test06_set() {
        double wt = 5.5;
        String units = "kg";
        Weight t = new Weight(wt + 100.0, units);
        t.set(wt);
        assertEquals("set set the wrong numeric value\n", 
                wt, t.get(), 1e-9);
        assertEquals("set caused the units to change???\n", 
                units, t.getUnits());
    }
    
    @Test
    public void test06_setThrows() {
        try {
            Weight w = new Weight();
            w.set(-0.01);
            fail("set should have thrown an exception");
        }
        catch (IllegalArgumentException x) {}
    }
    
    @Test
    public void test07_setUnits() {
        double wt = 100.0;
        String units = "kg";
        Weight t = new Weight(wt, units);
        t.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                wt, t.get(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, t.getUnits());
        
        double pounds = wt / 0.45359237;
        units = "lb";
        t.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                pounds, t.get(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, t.getUnits());
        
        wt = 148.0;
        units = "lb";
        Weight u = new Weight(wt, units);
        t.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                wt, u.get(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, u.getUnits());
        
        double kilo = wt * 0.45359237;
        units = "kg";
        u.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                kilo, u.get(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, u.getUnits());
    }
    
    @Test
    public void test07_setUnitsThrows() {
        try {
            Weight w = new Weight();
            w.setUnits("KG");
            fail("set should have thrown an exception");
        }
        catch (IllegalArgumentException x) {}
        try {
            Weight w = new Weight();
            w.setUnits("LB");
            fail("set should have thrown an exception");
        }
        catch (IllegalArgumentException x) {}
    }
    
    @Test
    public void test08_toKilograms() {
        double KG[] = {10.0, 0.0, 100.0};
        for (int i = 0; i < KG.length; i++) {
            double lb = KG[i] / 0.45359237;
            assertEquals("Weight.toKilograms returned the wrong value\n", 
                    KG[i], Weight.toKilograms(lb), 1e-9);
        }
    }
    
    @Test
    public void test09_toPounds() {
        double LB[] = {48.0, 32.0, 212.0};
        for (int i = 0; i < LB.length; i++) {
            double kg = LB[i] * 0.45359237;
            assertEquals("Weight.toFahrenheit returned the wrong value\n", 
                    LB[i], Weight.toPounds(kg), 1e-9);
        }
    }
    
    @Test
    public void test10_toString() {
        double wt = 15.2;
        String units = "kg";
        String exp = wt + " " + units;
        Weight t = new Weight(wt, units);
        assertEquals("toString returned the wrong string",
                exp, t.toString());
        units = "lb";
        exp = wt + " " + units;
        t = new Weight(wt, units);
        assertEquals("toString returned the wrong string",
                exp, t.toString());
    }
    
    @Test
    public void test11_equalsNull() {
        assertFalse("equals(null) returned true\n",
                (new Weight()).equals(null));
    }
    
    @Test
    public void test12_equalsWrongClass() {
        assertFalse("equals(String) returned false\n",
                (new Weight()).equals("0C"));
    }
    
    @Test
    public void test13_equalsTrue() {
        double KG[] = {40.0, 0.0, 100.0};
        for (int i = 0; i < KG.length; i++) {
            Weight t = new Weight(KG[i], "kg");
            Weight u = new Weight(KG[i] / 0.45359237, "lb");
            assertTrue("equals should have returned true for " + t + ".equals(" + t + ")\n", 
                    t.equals(t));
            assertTrue("equals should have returned true for " + t + ".equals(" + u + ")\n", 
                    t.equals(u));
            assertTrue("equals should have returned true for " + u + ".equals(" + t + ")\n", 
                    u.equals(t));
        }
    }
    
    @Test
    public void test13_equalsFalse() {
        double KG[] = {48.0, 32.0, 212.0};
        for (int i = 0; i < KG.length; i++) {
            Weight t = new Weight(KG[i], "kg");
            Weight u = new Weight(KG[i] / 0.45359237 + 0.1, "lb");
            assertFalse("equals should have returned false for " + t + ".equals(" + u + ")\n", 
                    t.equals(u));
            assertFalse("equals should have returned false for " + u + ".equals(" + t + ")\n", 
                    u.equals(t));
        }
    }
}