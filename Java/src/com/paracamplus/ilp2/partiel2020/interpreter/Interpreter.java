package com.paracamplus.ilp2.partiel2020.interpreter;

import java.math.BigInteger;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.partiel2020.interfaces.IASTsequenceN;
import com.paracamplus.ilp2.partiel2020.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>{
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}

    @Override
    public Object visit(IASTsequenceN iast, ILexicalEnvironment lexenv) throws EvaluationException {
        IASTexpression[] expressions = iast.getExpressions();
        
        Object nValue = iast.getPosition().accept(this, lexenv);
        if(!(nValue instanceof BigInteger) )
            throw new EvaluationException("Position's sequence should be an integer but recieved" + nValue ==null ?"null":nValue.getClass().toString()); 
        BigInteger n = (BigInteger) nValue; 
        if(
            n.compareTo(BigInteger.valueOf(0))< 0 ||  // less than 0 
            n.compareTo(BigInteger.valueOf(expressions.length-1))>0 // greateher than n-1 (arrays in 0 to n )
        )
            throw new EvaluationException("Position Sequence out of bound, expected : 0 to  "+(expressions.length -1 ) +" but recieved "+n.toString());
        int i= 0 ; 
        for(IASTexpression expr : expressions){

            Object value = expr.accept(this,lexenv);
            if(n.compareTo(BigInteger.valueOf(i))== 0)
                nValue = value; 
            i++; 
        } 
        return nValue; 
    }
    
}
