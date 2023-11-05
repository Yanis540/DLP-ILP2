package com.paracamplus.ilp2.partiel2021_2.interfaces;

import java.math.BigInteger;

import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;

public interface ILexicalRangeEnvironnement extends  ILexicalEnvironment{
	ILexicalRangeEnvironnement extend(IASTvariable variable, Object value,Object low,Object high) throws EvaluationException;
    BigInteger getLow(IASTvariable variable);
    BigInteger getHigh(IASTvariable variable);
}
