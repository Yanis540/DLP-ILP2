package com.paracamplus.ilp2.partiel2021_2.interpreter;

import java.math.BigInteger;

import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.LexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.partiel2021_2.interfaces.ILexicalRangeEnvironnement;

public class LexicalRangeEnvironnement extends LexicalEnvironment implements ILexicalRangeEnvironnement{
    private BigInteger low; 
    private BigInteger high; 
    public LexicalRangeEnvironnement(IASTvariable variable, Object value, Object low, Object high, ILexicalEnvironment next) throws EvaluationException{
        super(variable, value, next);
        
        this.low = updateIntegerValue(low);
        this.high = updateIntegerValue(high);
        if(this.low.compareTo(this.high)>0)
            throw new EvaluationException("Low value should be less than high value");
        this.updateValueBounded(value);
    }

    
    public BigInteger updateIntegerValue(Object value) throws EvaluationException{
        if(!(value instanceof BigInteger))
            throw new EvaluationException("Value should be integer");
        return (BigInteger)value; 
    }

    public void updateValueBounded (Object value) throws EvaluationException{
        BigInteger valueInteger = updateIntegerValue(value);
        super.updateValue(
            valueInteger.max(low).min(high)
        );
    }
  

    @Override
	public void update(IASTvariable key, Object value) throws EvaluationException {
        if ( key.getName().equals(getKey().getName()) ) {
            updateValueBounded(value);
        } else {
            getNext().update(key, value);
        }
    }

    @Override
    public ILexicalRangeEnvironnement extend(IASTvariable variable, Object value, Object low, Object high) throws EvaluationException {
        return new LexicalRangeEnvironnement(variable, value, low, high, this);
    }

    public BigInteger getLow(){
        return this.low; 
    }
    public BigInteger getHigh(){
        return this.high; 
    }
    @Override
    public BigInteger getLow(IASTvariable key) {
        if ( key.getName().equals(getKey().getName()) ) {
            return getLow();
        } else {
            return ((ILexicalRangeEnvironnement)getNext()).getLow(key);
        }
    }
    @Override
    public BigInteger getHigh(IASTvariable key) {
        if ( key.getName().equals(getKey().getName()) ) {
            return getHigh();
        } else {
            return ((ILexicalRangeEnvironnement)getNext()).getHigh(key);
        }
    }


    
}
