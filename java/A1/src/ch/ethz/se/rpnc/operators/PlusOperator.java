package ch.ethz.se.rpnc.operators;

public class PlusOperator implements CalculatorOperator {

	@Override
	public double evaluate(double arg1, double arg2) {
		return arg2 + arg1;
	}

}
