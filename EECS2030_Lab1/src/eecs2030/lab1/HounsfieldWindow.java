package eecs2030.lab1;

/**
 * A class that represents a windowed view of Hounsfield units. A Hounsfield
 * window is defined by two values: (1) the window level, and (2) the window
 * width. The window level is the Hounsfield unit value that the window is
 * centered on. The window width is the range of Hounsfield unit values that the
 * window is focused on.
 * 
 * <p>
 * A window has a lower and upper bound. The lower bound is defined as the
 * window level minus half the window width:
 * 
 * <p>
 * lo = level - (width / 2)
 * 
 * <p>
 * The upper bound is defined as the window level plus half the window width:
 * 
 * <p>
 * hi = level + (width / 2)
 * 
 * <p>
 * Hounsfield units are mapped by the window to a real number in the range of
 * {@code 0} to {@code 1}. A Hounsfield unit with a value less than lo is mapped
 * to the value {@code 0}. A Hounsfield unit with a value greater than hi is
 * mapped to the value {@code 1}. A Hounsfield unit with a value v between lo
 * and hi is mapped to the value:
 * 
 * <p>
 * (v - lo) / width
 * 
 *
 */
public class HounsfieldWindow {

	public int level;
	public int width;
	public int oldLevel;
	public int oldWidth;

	public HounsfieldWindow() {
		this.level = 0;
		this.width = 400;
	}

	private static void checkLevel(int level) {
		if ((level < -1024) || (level > 3071)) {
			throw new IllegalArgumentException();
		}
	}

	private static void checkWidth(int width) {
		if (width < 1) {
			throw new IllegalArgumentException();
		}
	}

	public HounsfieldWindow(int level, int width) {
		this.level = level;
		this.width = width;
	}

	public int getLevel() {
		return this.level;
	}

	public int getWidth() {
		return this.width;
	}

	public int setLevel(int level) {
		checkLevel(level);
		this.oldLevel = this.level;
		this.level = level;
		return this.oldLevel;
	}

	public int setWidth(int width) {
		checkWidth(width);
		this.oldWidth = this.width;
		this.width = width;
		return this.oldWidth;
	}

	public double lo;
	public double hi;
	public double v;

	public double map(Hounsfield h) {
		v = width / 2.0;
		hi = level + (width / 2.0);
		lo = level - (width / 2.0);

		if (h.value < lo) {
			return 0;
		} else if (h.value > hi) {
			return 1;
		} else {
			return (h.value - lo) / width;			
		}

	}

}
