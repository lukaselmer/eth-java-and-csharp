using System;
using System.Collections.Generic;

namespace Calculator.Operators
{
	class PlusOperand : IOperator
	{
		public double Evaluate (double arg1, double arg2)
		{
			return arg2 + arg1;
		}
	}

}

