package com.paracamplus.ilp2.ilp2tme5.interpreter;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.ilp2tme5.exceptions.BreakException;
import com.paracamplus.ilp2.ilp2tme5.exceptions.ContinueException;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTvisitor;
import com.paracamplus.ilp2.interfaces.IASTloop;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
     public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}
    @Override
    public Object visit(IASTbreak iast, ILexicalEnvironment data) throws EvaluationException {
        throw new BreakException("Using break keyword outside loop's body"); 
    }

    @Override
    public Object visit(IASTcontinue iast, ILexicalEnvironment data) throws EvaluationException {
        throw new BreakException("Using continue keyword outside loop's body"); 
    }
    @Override
	public Object visit(IASTloop iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        while ( true ) {
            Object condition = iast.getCondition().accept(this, lexenv);
            if ( condition instanceof Boolean ) {
                Boolean c = (Boolean) condition;
                if ( ! c ) {
                    break;
                }
            }
            try{
                iast.getBody().accept(this, lexenv);
            }
            catch(BreakException e){
                break ; 
            }
            catch(ContinueException e){
                continue; 
            }
            catch(Exception e){
                throw new EvaluationException(e.getMessage());
            }
        }
        return Boolean.FALSE;
    }
}
