package com.paracamplus.ilp2.partiel_for.interpreter;

import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.partiel_for.interfaces.IASTfor;
import com.paracamplus.ilp2.partiel_for.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}

    @Override
    public Object visit(IASTfor iast, ILexicalEnvironment lexenv) throws EvaluationException {
        
        Object initialisation = iast.getAssignment().accept(this, lexenv);
        if(!(initialisation instanceof BigInteger))
            throw new EvaluationException("initialisation of variable must be an Integer");
        
        while(true){
            Object condition = iast.getCondition().accept(this,lexenv);
            if(condition instanceof Boolean)
                if(!((Boolean) condition) )
                    break ; 
            iast.getBody().accept(this, lexenv);
            iast.getIterator().accept(this,lexenv);
        }

        return 1; 
    }
}
