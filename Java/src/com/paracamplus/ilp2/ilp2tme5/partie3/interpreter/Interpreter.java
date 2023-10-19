package com.paracamplus.ilp2.ilp2tme5.partie3.interpreter;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.ilp2tme5.partie3.exceptions.BreakException;
import com.paracamplus.ilp2.ilp2tme5.partie3.exceptions.ContinueException;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTbreaklabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTvisitor;
import com.paracamplus.ilp2.interfaces.IASTloop;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter 
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> 
{
    
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
        IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}
    @Override
    public Object visit(IASTbreaklabeled iast, ILexicalEnvironment data) throws EvaluationException {
        throw new BreakException("Break Label : "+iast.getBreakLabel()+" not found or not inside a loop ",iast.getBreakLabel()); 
    }

    @Override
    public Object visit(IASTcontinuelabeled iast, ILexicalEnvironment data) throws EvaluationException {
        throw new ContinueException("Continue Label : "+iast.getContinueLabel()+" not found or not inside a loop ",iast.getContinueLabel()); 
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
    @Override
	public Object visit(IASTlooplabeled iast, ILexicalEnvironment lexenv) 
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
                if(e.getLabel().equalsIgnoreCase(iast.getLabel()))
                    break ;
                else 
                    throw new BreakException(e.getMessage(),e.getLabel()); 
            }
            catch(ContinueException e){
                if(e.getLabel().equalsIgnoreCase(iast.getLabel()))
                    continue; 
                else 
                    throw new ContinueException(e.getMessage(), e.getLabel());
            }
            catch(Exception e){
                throw new EvaluationException(e.getMessage());
            }
        }
        return Boolean.FALSE;
    }
}
