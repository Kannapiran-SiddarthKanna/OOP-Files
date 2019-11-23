
public class ConjoinAll extends ExpressionCollector {

	ConjoinAll(){
		super();
	}
	
	void evaluate() {
		/*
		 * A conjoin-all collector takes the conjunction (and) of the evaluation results
		 * of its collected expressions. When there are no expressions collected, the
		 * default evaluation result is true.
		 */

		if (this.isTypeCorrect()) {

			if (this.Collector.size() == 0) {
				this.finalBoolean = true;
			} else {
				for (int i = 0; i < this.Collector.size(); i++) {
					this.finalBoolean = this.finalBoolean && (Boolean) this.Collector.get(i).value;
				}
			}

		}

	}

}
