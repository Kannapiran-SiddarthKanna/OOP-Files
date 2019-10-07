package eecs2030.test1;

import java.awt.Color;

public final class Gray {	
		
		final double grayDouble;
		final int grayInt;
		
		final double MIN_DOUBLE_VALUE = 0.0;
		final double MAX_DOUBLE_VALUE = 1.0;
		
		final double MIN_INT_VALUE = 0;
		final static double MAX_INT_VALUE = 255;
		
		public Gray(double g){
			if(!(g >= 0.0 && g <= 1.0)) {
				throw new IllegalArgumentException("value must be between 0 and 1");
			}			
			this.grayDouble = g;
			this.grayInt =  (int) Math.round(g * 255.0);
			
		}		
		

		public Gray(int g) {
			if(!(g >= 0 && g <= 255)) {
				throw new IllegalArgumentException("value must be between 0 and 255");
			}
			
			this.grayDouble = (double) (g / 255.0);
			this.grayInt = g;
		}
		
		public Gray(Gray other) {
			this.grayDouble = other.grayDouble;
			this.grayInt = other.grayInt;
		}
		
		public int asInt() {			
			return grayInt;
		}
		
		public double asDouble() {			
			return grayDouble;
		}
		
		public static double toDouble(int value) {
			if(!(value >= 0 && value <= 255)) {
				throw new IllegalArgumentException("value must be between 0 and 255");
			}
			
			double intToDouble = (double) (value / 255.0);			
			return intToDouble;
		}
		
		public static int toInt(double value) {
			
			if(!(value >= 0.0 && value <= 1.0)) {
				throw new IllegalArgumentException("value must be between 0 and 1");
			}			
			
			int doubleToInt = (int) Math.round(value * 255.0);
			return doubleToInt;
		}
		
		public static Gray fromRGB(Color c) {
			
			double red = (double) c.getRed();
			double green = (double) c.getGreen();
			double blue = (double) c.getBlue();			
			double fromRGB = (0.299 * red) + (0.587 * green) + (0.114 * blue);
			
			Gray newGray = new Gray(fromRGB);
			
			return newGray;
		}
		
		public String toString() {
			
			String toString = "" + this.grayInt;
			
			return toString;
		}
		
		public int compareTo(Gray other) {
			
			if(this.grayDouble > other.grayDouble) {
				return 1;
			}
			if(this.grayDouble < other.grayDouble) {
				return -1;
			}
						
			return 0;
		}
		
		public boolean equals(Object obj) {
			
			if(obj == null) {
				return false;
			}
			if(obj.getClass() != this.getClass()) {
				return false;
			}

			Gray other = (Gray) obj;
			
			if(this.grayInt != other.grayInt) {
				return false;
			}		
			
			return true;
		}
		
	}