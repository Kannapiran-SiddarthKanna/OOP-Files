package eecs2030.test1;

public class Temperature {

	public static final String CELCIUS = "C";
	public static final String FAHRENHEIT = "F";
	
	public double degC = 0;
	public double degF = 0;
	public String units = "";	
	
	public Temperature() {
		this.degC = 0;
		this.degF = toFahrenheit(degC);
		this.units = CELCIUS;
	}
	
	public Temperature(Temperature other) {
		if(other.units == CELCIUS) {
			this.degC = other.degC;
			this.units = CELCIUS;
			this.degF = toFahrenheit(this.degC);
		}
		if(other.units == FAHRENHEIT) {
			this.degF = other.degF;
			this.units = FAHRENHEIT;
			this.degC = toFahrenheit(this.degF);
		}
	}
	
	public Temperature(double temp, String units) {
		if(!(units.equals(CELCIUS) || units.equals(FAHRENHEIT))) {
			throw new IllegalArgumentException("units must be either C or F");
		}
		if(units == CELCIUS) {
			this.degC = temp;
			this.units = units;
		}
		if(units == FAHRENHEIT) {
			this.degF = temp;
			this.units = units;
		}
	}

	public double getTemperature() {
		if(this.units == CELCIUS) {
			return degC;
		}
		if(this.units == FAHRENHEIT) {
			return degF;
		}
		
		return 0;
	}
	
	public final void setTemperature(double temp) {
		if(this.units == CELCIUS) {
			this.degC = temp;
		}
		this.degF = temp;
	}
	
	public String getUnits() {
		if(this.units == CELCIUS) {
			return CELCIUS;
		}
		return FAHRENHEIT;
	}
	
	public final void setUnits(String units) {
		if(!(units.equals(CELCIUS) || units.equals(FAHRENHEIT))) {
			throw new IllegalArgumentException("units must be either C or F");
		}
		
		if(this.units.equals(CELCIUS)) {
			this.degF = toFahrenheit(this.degC);
			this.units = units;
		}
		if(this.units.equals(FAHRENHEIT)) {
			this.degC = toCelcius(this.degF);
			this.units = units;
		}
		
	}
	
	public static double toCelcius(double degF) {
		double temp = (degF - 32) * (5.0 / 9.0);		
		return temp;
	}
	
	public static double toFahrenheit(double degC) {
		double temp = degC * (9.0 / 5.0) + 32;		
		return temp;
	}
	
	public String toString() {
		if(this.units == CELCIUS) {
			return degC + CELCIUS;
		}		
		return degF + FAHRENHEIT;
	}
	
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj.getClass() != this.getClass()) {
			return false;
		}
		Temperature other = (Temperature) obj;
		
		if((this.units == other.units) && this.units == CELCIUS) {
			return (this.degC == other.degC);
		}
		if((this.units == other.units) && this.units == FAHRENHEIT) {
			return (this.degF == other.degF);
		}
		if(this.units != other.units) {
			if(this.units == CELCIUS && other.units == FAHRENHEIT) {
				double temp = toCelcius(other.degF);
				return this.degC == temp;
			}
			if(this.units == FAHRENHEIT && other.units == CELCIUS) {
				double temp = toFahrenheit(other.degC);
				return this.degF == temp;
			}
		}
		return true;
	}
}
