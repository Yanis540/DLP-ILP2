package com.paracamplus.ilp2.ilp2tme4.interpreter;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}
    private static Object whatever = "whatever";
    public Object visit(IASTUnless iast,ILexicalEnvironment lexenv) throws EvaluationException {
        Object condition = iast.getCondition().accept(this, lexenv); 
        if(condition == null || ! (condition instanceof Boolean))
            return whatever; 
        Boolean b =  (Boolean)(condition); 
        if(b == true )
            return whatever;
        return iast.getBody().accept(this,lexenv); 
    }
}
