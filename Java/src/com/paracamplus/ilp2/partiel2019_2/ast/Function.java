package com.paracamplus.ilp2.partiel2019_2.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IFunction;

public class Function extends com.paracamplus.ilp1.interpreter.Function implements IFunction{
    private Object cache;
    private Boolean frozen = false;  
    public Function(IASTvariable[] variables, IASTexpression body, ILexicalEnvironment lexenv) {
        super(variables, body, lexenv);
        this.cache = null ; 
    }

    @Override
    public Object getCache() {
        return this.cache; 
    }

    @Override
    public void setCache(Object cache) {
        this.cache = cache; 
        this.frozen = true; 
    }
    public boolean isFrozen(){
        return this.frozen;
    }
    
}
