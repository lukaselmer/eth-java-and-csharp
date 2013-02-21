package ch.ethz.se.rpnc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.ethz.se.rpnc.ReversePolishNotationCalculator;
import ch.ethz.se.rpnc.exception.EmptyStackEvaluationException;
import ch.ethz.se.rpnc.exception.IllegalNumberOfOperandsException;

public class ReversePolishNotationCalculatorTest {

	private final double delta = 1e-5;

	private ReversePolishNotationCalculator calculator;

	/*
	 * Set up the test by creating an instance of the calculator.
	 */
	@Before
	public void setUp() throws Exception {
		calculator = new ReversePolishNotationCalculator();
	}

	/*
	 * Simple tests for the basic operations
	 */
	@Test
	public void testPlus() throws Exception {
		calculator.pushOperand(3.0);
		calculator.pushOperand(2);
		calculator.pushPlusOperator();

		calculator.evaluateStack();

		assertEquals(5.0, calculator.popOperand(), delta);

	}

	@Test
	public void testMinus() throws Exception {
		calculator.pushOperand(3);
		calculator.pushOperand(2.0);
		calculator.pushMinusOperator();

		calculator.evaluateStack();

		assertEquals(1.0, calculator.popOperand(), delta);

	}

	@Test
	public void testTimes() throws Exception {
		calculator.pushOperand(3.0);
		calculator.pushOperand(2);
		calculator.pushTimesOperator();

		calculator.evaluateStack();

		assertEquals(6.0, calculator.popOperand(), delta);

	}

	@Test
	public void testDivision() throws Exception {
		calculator.pushOperand(10);
		calculator.pushOperand(2.0);
		calculator.pushDivideByOperator();
		calculator.evaluateStack();

		assertEquals(5.0, calculator.popOperand(), delta);

	}

	/**
	 * test peek and pop operations
	 */
	@Test
	public void testPeekAndPopOperand() throws Exception {
		calculator.pushOperand(4.0);
		calculator.pushOperand(7.0);
		calculator.pushPlusOperator();
		calculator.evaluateStack();

		assertEquals(11.0, calculator.peekOperand(), delta);
		assertFalse(calculator.isStackEmpty());

		calculator.pushOperand(3.0);
		calculator.pushTimesOperator();
		calculator.evaluateStack();

		assertEquals(33.0, calculator.peekOperand(), delta);
		assertFalse(calculator.isStackEmpty());
		assertEquals(33.0, calculator.popOperand(), delta);
		assertTrue(calculator.isStackEmpty());
	}

	/*
	 * test some more complex calculations
	 */

	/**
	 * perform the calculation shown in the assignment pdf 3 * (4 + 7) = 33
	 */
	@Test
	public void testComplexComputation() throws Exception {
		calculator.pushOperand(3.0);
		calculator.pushOperand(4.0);
		calculator.pushOperand(7.0);
		calculator.pushPlusOperator();
		calculator.pushTimesOperator();

		calculator.evaluateStack();

		assertEquals(33.0, calculator.popOperand(), delta);

	}

	/**
	 * perform a sequence of calculations with intermediate evaluateStack()
	 * calls 3 * (4 + 7) = 33
	 */
	@Test
	public void testSequentialComputation() throws Exception {

		calculator.pushOperand(4.0);
		calculator.pushOperand(7.0);
		calculator.pushPlusOperator();
		calculator.evaluateStack();

		calculator.pushOperand(3.0);
		calculator.pushTimesOperator();
		calculator.evaluateStack();

		assertEquals(33.0, calculator.popOperand(), delta);

	}

	/*
	 * test handling of some illegal evaluateStack() calls
	 */
	@Test(expected = EmptyStackEvaluationException.class)
	public void testEvaluateEmptyStackIsIllegal() throws Exception {
		calculator.evaluateStack();
		assertTrue(
				"An EmptyStackEvaluationException should have been thrown here.",
				false);
	}

	@Test(expected = IllegalNumberOfOperandsException.class)
	public void testIllegalNumberOfOperandsPlus() throws Exception {
		calculator.pushOperand(7.0);
		calculator.pushPlusOperator();
		calculator.evaluateStack();
		assertTrue(
				"An IllegalNumberOfOperandsException should have been thrown here.",
				false);
	}

	@Test(expected = IllegalNumberOfOperandsException.class)
	public void testIllegalNumberOfOperandsMinus() throws Exception {
		calculator.pushOperand(7.0);
		calculator.pushMinusOperator();
		calculator.evaluateStack();
		assertTrue(
				"An IllegalNumberOfOperandsException should have been thrown here.",
				false);
	}

	@Test(expected = IllegalNumberOfOperandsException.class)
	public void testIllegalNumberOfOperandsTimes() throws Exception {
		calculator.pushOperand(7.0);
		calculator.pushTimesOperator();
		calculator.evaluateStack();
		assertTrue(
				"An IllegalNumberOfOperandsException should have been thrown here.",
				false);
	}

	@Test(expected = IllegalNumberOfOperandsException.class)
	public void testIllegalNumberOfOperandsDivide() throws Exception {
		calculator.pushOperand(7.0);
		calculator.pushDivideByOperator();
		calculator.evaluateStack();
		assertTrue(
				"An IllegalNumberOfOperandsException should have been thrown here.",
				false);
	}

	@Test(expected = IllegalNumberOfOperandsException.class)
	public void testIllegalANumberOfOperands2() throws Exception {
		calculator.pushOperand(3.0);
		calculator.pushOperand(4.0);
		calculator.pushOperand(7.0);
		calculator.pushPlusOperator();
		calculator.pushTimesOperator();
		calculator.pushTimesOperator();
		calculator.evaluateStack();
		assertTrue(
				"An IllegalNumberOfOperandsException should have been thrown here.",
				false);
	}
}