
public class DisjoinAll extends ExpressionCollector {

	DisjoinAll(){
		super();
	}
	
	void evaluate() {
		/*
		 * A disjoin-all collector takes the disjunction (or) of the evaluation results
		 * of its collected expressions. When there are no expressions collected, the
		 * default evaluation result is false
		 */

		if (this.isTypeCorrect()) {

			if (this.Collector.size() == 0) {
				this.finalBoolean = false;
			} else {
				for (int i = 0; i < this.Collector.size(); i++) {
					this.finalBoolean = this.finalBoolean || (Boolean) this.Collector.get(i).value;
				}
			}

		}

	}
}
