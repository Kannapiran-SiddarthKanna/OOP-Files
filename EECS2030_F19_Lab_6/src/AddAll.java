
public class AddAll extends ExpressionCollector {
	
	AddAll(){
		super();
	}

	void evaluate() {
		/*
		 * An add-all collector adds up the evaluation results of its collected
		 * expressions. When there are no expressions collected, the default evaluation
		 * result is 0.
		 */
		this.finalInt = 0;
		if (this.isTypeCorrect()) {

			if (this.Collector.size() == 0) {
				this.finalInt = 0;
			} else {
				for (int i = 0; i < this.Collector.size(); i++) {
					this.finalInt = this.finalInt + (int) this.Collector.get(i).value;
				}
			}
		}

	}

}
