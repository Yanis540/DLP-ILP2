package com.paracamplus.ilp2.partiel2019.interpreter;

import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfreeze;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfrozen;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {
    private IASTfunctionDefinition[]functions; 
    private Object froozenValue= null;
    private IASTfunctionDefinition frozenFunction = null;    
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}
    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
        throws EvaluationException {
        this.functions = iast.getFunctionDefinitions().clone();
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
            Object f = this.visit(fd, lexenv);
            String v = fd.getName();
            getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
        }
        try {
            return iast.getBody().accept(this, lexenv);
       } catch (Exception exc) {
            return exc;
        }
    }
    @Override
    public Object visit(IASTfreeze iast, ILexicalEnvironment data) throws EvaluationException {
        Object value = super.visit(iast, data);
        IASTvariable functionVariable = (IASTvariable)iast.getFunction();
        for(IASTfunctionDefinition fd : this.functions)
            if(fd.getName().equals(functionVariable.getName())){
                this.froozenValue= value; 
                this.frozenFunction = fd;
                break ; 
            }
        return value; 
    }

    @Override
    public Object visit(IASTfrozen iast, ILexicalEnvironment data) throws EvaluationException {
        IASTvariable functionVariable = (IASTvariable)iast.getFunction();
        if(frozenFunction == null || 
            ! (frozenFunction.getName().equals(functionVariable.getName()))
        )
            throw new EvaluationException("Called  freeze on unfrozen function : "+functionVariable.getName());
        return this.froozenValue; 

    }

}
