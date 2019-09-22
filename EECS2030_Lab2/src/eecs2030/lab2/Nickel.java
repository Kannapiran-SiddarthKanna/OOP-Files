package eecs2030.lab2;

/**
 * A Class representing a Canadian nickel. The monetary value of nickel is
 * defined to be five cents. Nickels were first minted in the year 1858. A
 * nickel has an issue year which is the year in which the nickel was issued by
 * the mint.
 *
 */
public class Nickel implements Comparable<Nickel> {

	private int year;

	/**
	 * The monetary value of a nickel in cents.
	 */
	public final int CENTS = 5;

	/**
	 * Initializes this nickel to have the specified issue year.
	 * 
	 * @param year the year this coin was issued in
	 * @throws IllegalArgumentException if year is less than 1858 @pre. year must be
	 *                                  greater than or equal to 1858
	 */
	public Nickel(int year) {
		if (year < 1858) {
			throw new IllegalArgumentException();
		}
		this.year = year;
	}

	/**
	 * Returns the issue year of this coin.
	 * 
	 * @return the issue year of this coin
	 */
	public int issueYear() {
		return this.year;
	}

	/**
	 * Compares this nickel to another nickel by their issue year. The result is a
	 * negative integer if this nickel has an earlier issue year than the other
	 * nickel, a positive integer if this nickel has a later issue year than the the
	 * other nickel, and zero otherwise. Specifically, this method returns the
	 * difference of the issue year of this nickel and the issue year of the other
	 * nickel.
	 * 
	 * Returns the difference of the issue year of this nickel and the issue year of
	 * the other nickel
	 * 
	 * @return the difference of the issue year of this nickel and the issue year of
	 *         the other nickel
	 */
	public int compareTo(Nickel other) {
		return (this.year - other.year);
	}


	/**
	 * Compares this nickel to the specified object for equality. The result is true
	 * is obj is a nickel. This issue year is not considered when comparing two
	 * nickels for equality.
	 * 
	 * @return the issue year of this nickel.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CENTS;
		result = prime * result + year;
		return this.year;
	}

	/**
	 * Returns a hash code for this nickel. Specifically, this methor returns the
	 * issue year of this nickel.
	 * 
	 * @return the issue year of this nickel
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
			}
		if (getClass() != obj.getClass()) {
			return false;
			}
		
		Nickel other = (Nickel) obj;
		if (CENTS != other.CENTS) {
			return false;
			}
		if (this.year != other.year) {
			return false;
			}
		return true;
	}
}
