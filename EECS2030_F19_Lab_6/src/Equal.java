
public class Equal extends Expression{
	Equal(int left, int right){
		super(left, right);
	}
	
	void evaluate() {
		Object o = left == right;
		boolean r = (boolean) o;
		this.value = r;
	}
}
