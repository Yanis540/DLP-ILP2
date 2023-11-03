package com.paracamplus.ilp2.partiel2019_2.interpreter;

import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.Invocable;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.partiel2019_2.ast.Function;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTfreeze;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTfrozen;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
    
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}

    @Override
    public Invocable visit(IASTfunctionDefinition iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        Invocable fun = new Function(iast.getVariables(),
                                     iast.getBody(),
                                     new EmptyLexicalEnvironment());
        return fun;
    }
    @Override
    public Object visit(IASTfreeze iast, ILexicalEnvironment data) throws EvaluationException {

        Object result = super.visit((IASTinvocation)iast,data);
        Function function = (Function)(iast.getFunction().accept(this,data));
        function.setCache(result);

        return result; 
    }

    @Override
    public Object visit(IASTfrozen iast, ILexicalEnvironment data) throws EvaluationException {
        Function function = (Function)(iast.getFunction().accept(this,data));
        if(function.isFrozen()== false)
            throw new EvaluationException("Function not frozen");
        return function.getCache();
    }
    
}
