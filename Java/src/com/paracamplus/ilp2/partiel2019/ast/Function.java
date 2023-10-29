package com.paracamplus.ilp2.partiel2019.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.partiel2019.interfaces.IFunction;

public class Function extends com.paracamplus.ilp1.interpreter.Function implements IFunction {
    public Function (IASTvariable[] variables, 
                     IASTexpression body, 
                     ILexicalEnvironment lexenv) {
        super(variables,body,lexenv);
    }
    private Object cache=null;
    @Override
    public Object getCache() {
        return this.cache; 
    }
    @Override
    public void setCache(Object cache) {
        this.cache = cache; 
    } 
    
}
