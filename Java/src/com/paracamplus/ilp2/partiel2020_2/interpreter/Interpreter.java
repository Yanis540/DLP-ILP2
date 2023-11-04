package com.paracamplus.ilp2.partiel2020_2.interpreter;

import java.math.BigInteger;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.partiel2020_2.interfaces.IASTsequenceN;
import com.paracamplus.ilp2.partiel2020_2.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment);
    }
    @Override
    public Object visit(IASTsequenceN iast, ILexicalEnvironment data) throws EvaluationException {
        Object value = iast.getIndex().accept(this, data);
        if(value== null)
            throw new EvaluationException("Index in a sequence can't be null");
        if(!(value instanceof BigInteger))
            throw new EvaluationException("Index expression should return an integer");
        int index = ((BigInteger) value).intValue();
        IASTexpression[] expressions = iast.getExpressions();
        int expressionsLength = expressions.length;
        if(index <0 || index >= expressionsLength  )
            throw new EvaluationException("Index out of range");
        int i = 0 ; 
        Object nValue = null;
        for ( IASTexpression e : expressions ) {
            value = e.accept(this, data);
            if(i==index)
                nValue = value;
            i++;
        }
        return nValue;
    }
    
}
