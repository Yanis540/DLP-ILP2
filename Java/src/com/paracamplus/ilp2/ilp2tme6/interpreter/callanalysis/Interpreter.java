package com.paracamplus.ilp2.ilp2tme6.interpreter.callanalysis;


import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.ilp2tme6.optimizer.Callanalysis;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter {
    private Callanalysis transformer  ; 
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment,IASTfactory factory) {
		super(globalVariableEnvironment, operatorEnvironment);
        this.transformer= new Callanalysis(factory);

	}
    public IASTprogram callAnalysis(IASTprogram iast) throws CompilationException {
        iast = (IASTprogram)transformer.visit(iast, null); 
        return iast; 
    }
   
    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        try {
            iast = this.callAnalysis(iast); //! cette opération pourrait être fait dans le ILPML optimizer directement pas la peine d'attendre jusqu'à ici   
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
