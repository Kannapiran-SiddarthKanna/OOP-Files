
public class TimesAll extends ExpressionCollector {
	
	TimesAll(){
		super();
	}

	void evaluate() {
		/*
		 * A times-all collector multiplies the evaluation results of its collected
		 * expressions. When there are no expressions collected, the default evaluation
		 * result is 1.
		 */
		this.finalInt = 1;
		if (this.isTypeCorrect()) {

			if (this.Collector.size() == 0) {
				this.finalInt = 1;
			} else {
				for (int i = 0; i < this.Collector.size(); i++) {
					this.finalInt = this.finalInt * (int) this.Collector.get(i).value;
				}
			}
		}

	}

}
