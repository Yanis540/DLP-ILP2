package com.paracamplus.ilp2.partiel2021.interpreter;

import java.math.BigInteger;

import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.LexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.partiel2021.interfaces.ILexicalRangeEnvironment;

public class LexicalRangeEnvironnement extends LexicalEnvironment implements ILexicalRangeEnvironment{
     public LexicalRangeEnvironnement (IASTvariable variable, 
                    Object value,
                    Object low,
                    Object high, 
                    ILexicalEnvironment next ) throws EvaluationException{
        super(variable,value,next);
        if(!(low instanceof BigInteger && high instanceof BigInteger))
            throw new EvaluationException("High/ low values should be integers");
        this.high = (BigInteger)high;
        this.low = (BigInteger)low;
        if(this.low.compareTo(this.high)> 0)
            throw new EvaluationException("Low value should be bigger than low values");
        updateValueBounded(value);
       
    }
    private BigInteger low;
    private BigInteger high;
    @Override
    public BigInteger getLow() {
        return this.low; 
    }

    @Override
    public BigInteger getHigh() {
        return this.high ; 
    }
    public void updateValueBounded(Object v) throws EvaluationException
    {
        if (!(v instanceof BigInteger))
            throw new EvaluationException("Bounded variables must have integer values");
        BigInteger i = (BigInteger)v;
        super.updateValue(i.max(low).min(high));
    }
    @Override public void update(IASTvariable key, Object value) throws EvaluationException {
        if ( key.getName().equals(getKey().getName()) ) 
            updateValueBounded(value);
        else 
            getNext().update(key, value);
    }
}
