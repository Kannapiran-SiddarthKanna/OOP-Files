
public class GreaterThan extends Expression{
	GreaterThan(int left, int right){
		super(left, right);
	}
	
	void evaluate() {
		Object o = left > right;
		boolean r = (boolean) o;
		this.value = r;
	}
}
