package eecs2030.test1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TemperatureTest {

    @Test
    public void test01_fieldCelcius() {
        assertEquals("The field Temperature.CELCIUS does not have the value \"C\"\n",
                "C", Temperature.CELCIUS);
    }
    
    @Test
    public void test02_fieldFahrenheit() {
        assertEquals("The field Temperature.FAHRENHEIT does not have the value \"F\"\n",
                "F", Temperature.FAHRENHEIT);
    }
    
    @Test
    public void test03_defaultCtor() {
        Temperature t = new Temperature();
        assertEquals("The default temperature does not have the numeric value 0.0\n", 
                0.0, t.getTemperature(), 1e-9);
        assertEquals("The default temperature does not have the units \"C\"\n", 
                "C", t.getUnits());
        
    }

    @Test
    public void test04_ctor() {
        double temp = -5.5;
        String units = "F";
        Temperature t = new Temperature(temp, units);
        assertEquals("The constructed temperature has the wrong numeric value\n", 
                temp, t.getTemperature(), 1e-9);
        assertEquals("The constructed temperature has the wrong units\n", 
                units, t.getUnits());
        
        units = "C";
        t = new Temperature(temp, units);
        assertEquals("The constructed temperature has the wrong numeric value\n", 
                temp, t.getTemperature(), 1e-9);
        assertEquals("The constructed temperature has the wrong units\n", 
                units, t.getUnits());
    }
    
    @Test
    public void test05_copyCtor() {
        double temp = -5.5;
        String units = "F";
        Temperature t = new Temperature(temp, units);
        Temperature u = new Temperature(t);
        assertEquals("The copied temperature has the wrong numeric value\n", 
                temp, u.getTemperature(), 1e-9);
        assertEquals("The copied temperature has the wrong units\n", 
                units, u.getUnits());
        
        units = "C";
        t = new Temperature(temp, units);
        u = new Temperature(t);
        assertEquals("The copied temperature has the wrong numeric value\n", 
                temp, u.getTemperature(), 1e-9);
        assertEquals("The copied temperature has the wrong units\n", 
                units, u.getUnits());
    }
    
    @Test
    public void test06_setTemperature() {
        double temp = -5.5;
        String units = "F";
        Temperature t = new Temperature(temp + 100.0, units);
        t.setTemperature(temp);
        assertEquals("setTemperature set the wrong numeric value\n", 
                temp, t.getTemperature(), 1e-9);
        assertEquals("setTemperature caused the units to change???\n", 
                units, t.getUnits());
    }
    
    @Test
    public void test07_setUnits() {
        double temp = 100.0;
        String units = "C";
        Temperature t = new Temperature(temp, units);
        t.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                temp, t.getTemperature(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, t.getUnits());
        
        double fahr = 212.0;
        units = "F";
        t.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                fahr, t.getTemperature(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, t.getUnits());
        
        temp = -148.0;
        units = "F";
        Temperature u = new Temperature(temp, units);
        t.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                temp, u.getTemperature(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, u.getUnits());
        
        double celc = -100.0;
        units = "C";
        u.setUnits(units);
        assertEquals("setUnits set the wrong numeric value\n", 
                celc, u.getTemperature(), 1e-9);
        assertEquals("setUnits set the wrong units\n", 
                units, u.getUnits());
    }
    
    @Test
    public void test08_toCelcius() {
        double C[] = {-100.0, 0.0, 100.0};
        double F[] = {-148.0, 32.0, 212.0};
        for (int i = 0; i < C.length; i++) {
            assertEquals("Temperature.toCelcius returned the wrong value\n", 
                    C[i], Temperature.toCelcius(F[i]), 1e-9);
        }
    }
    
    @Test
    public void test09_toFahrenheit() {
        double C[] = {-100.0, 0.0, 100.0};
        double F[] = {-148.0, 32.0, 212.0};
        for (int i = 0; i < C.length; i++) {
            assertEquals("Temperature.toFahrenheit returned the wrong value\n", 
                    F[i], Temperature.toFahrenheit(C[i]), 1e-9);
        }
    }
    
    @Test
    public void test10_toString() {
        double temp = -15.2;
        String units = "C";
        String exp = temp + units;
        Temperature t = new Temperature(temp, units);
        assertEquals("toString returned the wrong string",
                exp, t.toString());
    }
    
    @Test
    public void test11_equalsNull() {
        assertFalse("equals(null) returned true\n",
                (new Temperature()).equals(null));
    }
    
    @Test
    public void test12_equalsWrongClass() {
        assertFalse("equals(String) returned false\n",
                (new Temperature()).equals("0C"));
    }
    
    @Test
    public void test13_equalsTrue() {
        double C[] = {-100.0, 0.0, 100.0};
        double F[] = {-148.0, 32.0, 212.0};
        for (int i = 0; i < C.length; i++) {
            Temperature t = new Temperature(C[i], "C");
            Temperature u = new Temperature(F[i], "F");
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
        double C[] = {-148.0, 32.0, 212.0};
        double F[] = {-100.0, 0.0, 100.0};
        for (int i = 0; i < C.length; i++) {
            Temperature t = new Temperature(C[i], "C");
            Temperature u = new Temperature(F[i], "F");
            assertFalse("equals should have returned false for " + t + ".equals(" + u + ")\n", 
                    t.equals(u));
            assertFalse("equals should have returned false for " + u + ".equals(" + t + ")\n", 
                    u.equals(t));
        }
    }
}