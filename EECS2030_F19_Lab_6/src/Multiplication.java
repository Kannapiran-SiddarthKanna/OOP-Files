
public class Multiplication extends Expression{
	Multiplication(int left, int right){
		super(left, right);
	}
	
	void evaluate() {
		Object o = left * right;
		Integer value = (Integer) o;
		this.value = value;
	}
}
