package com.paracamplus.ilp2.ilp2tme6.interpreter.inline;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.ilp2tme6.optimizer.InlineTransform;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter{
    private InlineTransform transformer ;
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment,IASTfactory factory) {
		super(globalVariableEnvironment, operatorEnvironment);
        this.transformer = new InlineTransform(factory);
	}

    public IASTprogram inline(IASTprogram iast) throws CompilationException {
        iast = (IASTprogram)transformer.visit(iast, null); 
        return iast; 
    }

    public Object visit(IASTprogram iast,ILexicalEnvironment lexenv ) throws EvaluationException{
        try {
            iast =this.inline(iast); 
            for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
                Object f = this.visit(fd, lexenv);
                String v = fd.getName();
                getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
            }
            return iast.getBody().accept(this, lexenv);
       } catch (Exception exc) {
            return exc;
        }
    }
}
