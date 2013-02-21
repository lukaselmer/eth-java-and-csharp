package ch.ethz.se.rpnc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import ch.ethz.se.rpnc.exception.EmptyStackEvaluationException;
import ch.ethz.se.rpnc.exception.IllegalNumberOfOperandsException;
import ch.ethz.se.rpnc.operators.CalculatorOperator;
import ch.ethz.se.rpnc.operators.DivideByOperator;
import ch.ethz.se.rpnc.operators.MinusOperator;
import ch.ethz.se.rpnc.operators.PlusOperator;
import ch.ethz.se.rpnc.operators.TimesOperator;

public class ReversePolishNotationCalculator {

	private Stack<Double> operands = new Stack<Double>();
	private Queue<CalculatorOperator> operators = new LinkedList<CalculatorOperator>();

	public void pushOperand(double d) {
		operands.add(d);
	}

	public void pushPlusOperator() {
		operators.add(new PlusOperator());
	}

	public void pushMinusOperator() {
		operators.add(new MinusOperator());
	}

	public void pushTimesOperator() {
		operators.add(new TimesOperator());
	}

	public void pushDivideByOperator() {
		operators.add(new DivideByOperator());
	}

	public double popOperand() {
		return operands.pop();
	}

	public boolean isStackEmpty() {
		return operands.isEmpty();
	}

	public double peekOperand() {
		return operands.peek();
	}

	public void evaluateStack() throws EmptyStackEvaluationException,
			IllegalNumberOfOperandsException {
		if (operands.isEmpty())
			throw new EmptyStackEvaluationException();
		for (CalculatorOperator operator : operators) {
			if (operands.size() < 2)
				throw new IllegalNumberOfOperandsException();
			operands.add(operator.evaluate(operands.pop(), operands.pop()));
		}
		operators.clear();
	}

}
