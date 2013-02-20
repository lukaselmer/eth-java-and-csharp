using System;
using NUnit.Framework;
using NUnit.Framework.Constraints;
using Calculator.Exceptions;

namespace Calculator
{
	
	[TestFixture()]
	public class CalculatorTest
	{
		private ReversePolishNotationCalculator calc;
		private double delta = 1e-5;

		//Set up the test fixture by creating an instance of the calculator.
		[SetUp()]
		public void CommonExecution()
		{
			calc = new ReversePolishNotationCalculator();
		}
		
		//Simple tests for the basic operations
		[Test()]
		public void TestPlus()
		{
			calc.PushOperand(3.5);
			calc.PushOperand(2.7);
			calc.PushPlusOperator();
			calc.EvaluateStack();
			Assert.That(6.2,Is.EqualTo(calc.PopOperand()).Within(delta));
		}
		
		[Test()]
		public void TestMinus()
		{
			calc.PushOperand(2.5);
			calc.PushOperand(2.7);
			calc.PushMinusOperator();
			calc.EvaluateStack();
			Assert.That(-0.2,Is.EqualTo(calc.PopOperand()).Within(delta));
		}
		
		[Test()]
		public void TestTimes()
		{
			calc.PushOperand(2.5);
			calc.PushOperand(2.7);
			calc.PushTimesOperator();
			calc.EvaluateStack();
			Assert.That(6.75,Is.EqualTo(calc.PopOperand()).Within(delta));
		}

		[Test()]
		public void TestDivide()
		{
			calc.PushOperand(2.5);
			calc.PushOperand(2.7);
			calc.PushDivideByOperator();
			calc.EvaluateStack();
			Assert.That(0.92592,Is.EqualTo(calc.PopOperand()).Within(delta));
		}
	
		//Test for Peek operation
		[Test()]
		public void TestPeekOperand()
		{
			calc.PushOperand(0.5);
			calc.PushOperand(2.94);
			calc.PushPlusOperator();
			calc.EvaluateStack();
			Assert.That(3.44,Is.EqualTo(calc.PeekOperand()).Within(delta));	
			Assert.That(calc.IsStackEmpty(), Is.False);
		}

		//Test for Push operation
		[Test()]
		public void TestPopOperand()
		{
			calc.PushOperand(0.5);
			calc.PushOperand(2.94);
			calc.PushPlusOperator();
			calc.EvaluateStack();
			Assert.That(3.44,Is.EqualTo(calc.PopOperand()).Within(delta));	
			Assert.That(calc.IsStackEmpty(), Is.True);
		}	

		//Test computation with three operands and two different operators
		[Test()]
		public void TestComplexOperation()
		{
			calc.PushOperand(0.5);
			calc.PushOperand(2.94);
			calc.PushOperand(.51);   
			calc.PushPlusOperator();
			calc.PushTimesOperator();
			calc.EvaluateStack();
			Assert.That(1.725,Is.EqualTo(calc.PopOperand()).Within(delta));	
		}	

		//Test a sequence of computations with intermediate stack evaluation
		[Test()]
		public void TestSequentialComputation()
		{
			calc.PushOperand(4.0);
			calc.PushOperand(7.0);
			calc.PushPlusOperator();
			calc.EvaluateStack();
			
			calc.PushOperand(3.0);
			calc.PushTimesOperator();
			calc.EvaluateStack();
			Assert.That(33.0,Is.EqualTo(calc.PopOperand()).Within(delta));				
		}

		//Test if various exceptions are thrown hen they should be 
		[Test()]
		[ExpectedException(typeof(EmptyStackEvaluationException))]
		public void TestEvaluateEmptyStackException()
		{
			calc.EvaluateStack();			
		}
		
		[Test()]
		[ExpectedException(typeof(IllegalNumberOfOperandsException))]
		public void TestIllegalNumberOfOperandsPlus()
		{
			calc.PushOperand(7.0);
			calc.PushPlusOperator();
			calc.EvaluateStack();
		}

		[Test()]
		[ExpectedException(typeof(IllegalNumberOfOperandsException))]
		public void TestIllegalNumberOfOperandsMinus()
		{
			calc.PushOperand(7.0);
			calc.PushMinusOperator();
			calc.EvaluateStack();
		}
		
		[Test()]
		[ExpectedException(typeof(IllegalNumberOfOperandsException))]
		public void TestIllegalNumberOfOperandsTimes()
		{
			calc.PushOperand(7.0);
			calc.PushTimesOperator();
			calc.EvaluateStack();
		}

		[Test()]
		[ExpectedException(typeof(IllegalNumberOfOperandsException))]
		public void TestIllegalNumberOfOperandsComplex()
		{
			calc.PushOperand(3.0);
			calc.PushOperand(4.0);
			calc.PushOperand(7.0);
			calc.PushPlusOperator();
			calc.PushTimesOperator();
			calc.PushTimesOperator();
			calc.EvaluateStack();
		}				
	}
}
