package com.paracamplus.ilp2.partiel2019.interpreter;

import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.Invocable;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.partiel2019.ast.Function;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfreeze;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfrozen;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTvisitor;
import com.paracamplus.ilp2.partiel2019.interfaces.IFunction;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {
    private IFunction[]functions ; 
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}
    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
        throws EvaluationException {
        this.functions = new IFunction[iast.getFunctionDefinitions().length];
        int i = 0 ; 
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
            Object f = this.visit(fd, lexenv);
            functions[i] = (IFunction)f; 
            String v = fd.getName();
            getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
        }
        try {
            return iast.getBody().accept(this, lexenv);
       } catch (Exception exc) {
            return exc;
        }
    }

    public Invocable visit(IASTfunctionDefinition iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        Invocable fun = new Function(iast.getVariables(),
            iast.getBody(),
            new EmptyLexicalEnvironment()
        );
        return fun;
    }

    @Override
    public Object visit(IASTfreeze iast, ILexicalEnvironment data) throws EvaluationException {
        Object value = super.visit(iast, data);
        Function function = (Function)iast.getFunction().accept(this, data);
        function.setCache(value);
        return value; 
    }

    @Override
    public Object visit(IASTfrozen iast, ILexicalEnvironment data) throws EvaluationException {
        Function function = (Function)iast.getFunction().accept(this, data);
        System.out.println(function);
        if(!(function instanceof Function))
            throw new EvaluationException("Idk");
        if(function.getCache() == null)
            throw new EvaluationException("Function not cached"); 
        return function.getCache();

    }

}
