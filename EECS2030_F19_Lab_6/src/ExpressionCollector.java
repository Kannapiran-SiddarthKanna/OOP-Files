import java.util.List;
import java.util.ArrayList;
/*
 * Your Tasks:
 * 1. Read the problem description and inheritance hierarchy diagram in the instructions.
 * 2. Add the necessary subclasses and implement the required methods.
 * 3. Study tests in TestExpressions.java to understand what's required to help your implementation. 
 */

public class ExpressionCollector {

	/*
	 * Your Task:
	 * Declare attribute(s) to store the list of Expression objects of this collector.
	 */
	
	public List<Expression> Collector;
	public int finalInt;
	public boolean finalBoolean;

	/**
	 * Initialize an expression collector which stores an empty collection of expressions.  
	 */
	ExpressionCollector() {
		/* Your Task */
		
		this.Collector = new ArrayList<Expression>();
		this.finalInt = 0;
		this.finalBoolean = true;
	}

	/**
	 * Add a new expression.
	 * @param e an expression
	 */
	void addExpression(Expression e) { 
		/* Your Task */

		this.Collector.add(e);
	}

	/**
	 * Add a new expression.
	 * @param left integer value of the left operand 
	 * @param op operator (i.e., "+", "-", "*", ">", "<", "==")
	 * @param right integer value of the right operand
	 */
	void addExpression(int left, String op, int right) {
		/* Your Task */
		// Hint: Depending on the operator given (e.g., +), create the corresponding expression object
		// (e.g., Addition) and add it to the collector.
		if(op.equals("+")) {
			Expression newExpression = new Addition(left, right);
			this.Collector.add(newExpression);
		}
		else if(op.equals("-")) {
			Expression newExpression = new Subtraction(left, right);
			this.Collector.add(newExpression);
		}
		else if(op.equals("*")) {
			Expression newExpression = new Multiplication(left, right);
			this.Collector.add(newExpression);
		}
		else if(op.equals(">")) {
			Expression newExpression = new GreaterThan(left, right);
			this.Collector.add(newExpression);
		}
		else if(op.equals("<")) {
			Expression newExpression = new LessThan(left, right);
			this.Collector.add(newExpression);
		}
		else if(op.equals("==")) {
			Expression newExpression = new Equal(left, right);
			this.Collector.add(newExpression);
		}		
		
	}

	/**
	 * If this collector is AddAll or TimesAll, then all stored expressions must be integer expressions
	 * (i.e., Addition, Subtraction, Multiplication).
	 * If this collection is ConjoinAll or DisjoinAll, then all stored expressions must be boolean expressions
	 * (i.e., GreaterThan, LessThan, Equal)
	 * @return whether it is possible to evaluate the stored collection of expressions.   
	 */
	boolean isTypeCorrect() {
		/* Your Task */
		boolean isTypeCorrect = true;
		
		if(this.Collector instanceof ConjoinAll || this.Collector instanceof DisjoinAll) {
			for(int i = 0; i < this.Collector.size(); i++) {
				isTypeCorrect = (this.Collector.get(i).value instanceof Boolean) && isTypeCorrect;
			}
		}
		
		if(this.Collector instanceof AddAll || this.Collector instanceof TimesAll) {
			for(int i = 0; i < this.Collector.size(); i++) {
				isTypeCorrect = (this.Collector.get(i).value instanceof Integer) && isTypeCorrect;
			}
		}
		
		return isTypeCorrect;
	}
	
	/**
	 * Given that this expression collector is type correct (see `isTypeCorrect()`),
	 * evaluate it. Store the evaluation result so that it can be returned by `getValue()`. 
	 */
	void evaluate() {
		/* Your Task */ 
		// Hint: Leave this empty and override it in all subclasses such as AddAll and ConjoinAll.
	}

	/**
	 * Get the result of the latest evaluation (i.e., after the latest call to `evaluate()`).
	 * @return the last evaluation result.
	 */
	Object getValue() {
		/* Your Task */
		if(this.getClass() == AddAll.class || this.getClass() == TimesAll.class) {
			return this.finalInt;
		}
		return this.finalBoolean;
	}
	
	/**
	 * Obtain the list of stored expressions.
	 * @return all stored expressions as an array
	 */
	Expression[] getExpressions() {
		/* Your Task */
		Expression[] listArray = new Expression[this.Collector.size()];
		
		for(int i = 0; i < listArray.length; i++) {
			listArray[i] = this.Collector.get(i);
		}
		
		return listArray;
	}

	/**
	 * Override the equals method. Two expression collectors are equals if:
	 * 1) They are both type correct 2) Their evaluation results are identical
	 * @return whether or not the two expression collectors are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		/* Your Task */
		
		if(this instanceof AddAll && obj instanceof AddAll) {
			if(this.isTypeCorrect() == ((ExpressionCollector) obj).isTypeCorrect()) {
				if(this.getValue() == ((ExpressionCollector) obj).getValue()) {
					return true;
				}
			}
		}
		else if(this instanceof TimesAll && obj instanceof TimesAll) {
			if(this.isTypeCorrect() == ((ExpressionCollector) obj).isTypeCorrect()) {
				if(this.getValue() == ((ExpressionCollector) obj).getValue()) {
					return true;
				}
			}
		}
		else if(this instanceof ConjoinAll && obj instanceof ConjoinAll) {
			if(this.isTypeCorrect() == ((ExpressionCollector) obj).isTypeCorrect()) {
				if(this.getValue() == ((ExpressionCollector) obj).getValue()) {
					return true;
				}
			}
		}
		else if(this instanceof DisjoinAll && obj instanceof DisjoinAll) {
			if(this.isTypeCorrect() == ((ExpressionCollector) obj).isTypeCorrect()) {
				if(this.getValue() == ((ExpressionCollector) obj).getValue()) {
					return true;
				}
			}
		}		
		
		return false;		
	}
}
