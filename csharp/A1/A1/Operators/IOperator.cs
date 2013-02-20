using System;
using System.Collections.Generic;

namespace Calculator.Operators
{
	interface IOperator
	{
		double Evaluate(double arg1, double arg2);
	}

}

