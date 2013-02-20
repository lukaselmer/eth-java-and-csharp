using System;
using System.Collections.Generic;
using Calculator.Operators;
using Calculator.Exceptions;

namespace Calculator
{
	public class ReversePolishNotationCalculator
	{
		Queue<IOperator> operators = new Queue<IOperator> ();
		Stack<double> operands = new Stack<double> ();

		public ReversePolishNotationCalculator ()
		{
		}

		public void PushOperand (double d)
		{
			operands.Push (d);
		}

		public void PushPlusOperator ()
		{
			operators.Enqueue (new PlusOperand ());
		}
		
		public void PushMinusOperator ()
		{
			operators.Enqueue (new MinusOperand ());
		}

		public void PushTimesOperator ()
		{
			operators.Enqueue (new TimesOperand ());
		}
		
		public void PushDivideByOperator ()
		{
			operators.Enqueue (new DivideByOperand ());
		}

		public void EvaluateStack ()
		{
			if (IsStackEmpty ())
				throw new EmptyStackEvaluationException ();
			foreach(var o in operators) {
				if (operands.Count < 2)
					throw new IllegalNumberOfOperandsException ();
				operands.Push (o.Evaluate (operands.Pop (), operands.Pop ()));
			}
			operators.Clear();
		}

		public double PopOperand ()
		{
			return operands.Pop ();
		}

		public double PeekOperand ()
		{
			return operands.Peek ();
		}

		public bool IsStackEmpty ()
		{
			return operands.Count == 0;
		}
	}
}

