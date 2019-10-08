package eecs2030.test1;

public class Weight {
	
	public static final String KG = "kg";
	public static final String LB = "lb";
	public static final double KG_PER_LB = 0.45359237;
	
	public double kg;
	public double lb;
	public String units;
	
	public Weight() {
		this.kg = 0;
		this.lb = 0;
		this.units = KG;
	}
	
	public Weight(Weight other) {	
			this.kg = other.kg;
			this.lb = other.lb;
			this.units = other.units;		
	}
	
	public Weight(double wt, String units) {
		if(wt < 0) {
			throw new IllegalArgumentException("weight must be more than 0");
		}
		if(!(units.equals(KG) || units.equals(LB))){
			throw new IllegalArgumentException("units must be either kg or lb");
		}
		
		if(units.equals(KG)) {
			this.kg = wt;
			this.units = units;
		}
		if(units.equals(LB)) {
			this.lb = wt;
			this.units = units;
		}
	}
	
	public double get() {		
		if(this.units.equals(KG)) {
			return kg;
		}
		if(this.units.equals(LB)) {
			return lb;
		}
		
		return lb;		
	}
	
	public final void set(double wt) {
		if(wt < 0) {
			throw new IllegalArgumentException("weight must be more than 0");
		}
		
		if(this.units.equals(KG)) {
			this.kg = wt;
		}
		if(this.units.equals(LB)) {
			this.lb = wt;
		}
		this.lb = wt;
	}
	
	public String getUnits() {
		if(this.units.equals(KG)) {
			return KG;
		}
		return LB;
	}
	
	public final void setUnits(String units) {
		if(!(units.equals(KG) || units.equals(LB))) {
			throw new IllegalArgumentException("units must be either kg or lb");
		}
		
		if(this.units.equals(KG)) {
			this.lb = toPounds(this.kg);
			this.units = units;
		}
		if(this.units.equals(LB)) {
			this.kg = toKilograms(this.lb);
			this.units = units;
		}
	}
	
	public static double toPounds(double kg) {
		double temp = kg / KG_PER_LB;
		return temp;
	}
	
	public static double toKilograms(double lb) {
		double temp = lb * KG_PER_LB;
		return temp;
	}
	
	public String toString() {
		if(this.units.equals(KG)) {
			return this.kg + " " + KG;
		}		
		return this.lb + " " + LB;
	}
	
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj.getClass() != this.getClass()) {
			return false;
		}
		
		Weight other = (Weight) obj;
		
		if(this.units.equals(other.units)) {
			return this.kg == other.kg;
		}
		if((this.units == other.units) && this.units.equals(KG)) {
			return (this.kg == other.kg);
		}
		if((this.units == other.units) && this.units.equals(LB)) {
			return (this.lb == other.lb);
		}
		if(this.units != other.units) {
			if(this.units.equals(KG) && other.units.equals(LB)) {
				double temp = toKilograms(other.lb);
				return this.kg == temp;
			}
			if(this.units.equals(LB) && other.units.equals(KG)) {
				double temp = toPounds(other.kg);
				return this.lb == temp;
			}
		}
		return true;
	}
}
