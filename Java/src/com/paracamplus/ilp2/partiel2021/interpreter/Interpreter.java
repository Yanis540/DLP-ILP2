package com.paracamplus.ilp2.partiel2021.interpreter;


import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTrangeblock;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTrangeblock.IASTrangebinding;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
        IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}
    @Override
    public Object visit(IASTrangeblock iast, ILexicalEnvironment lexenv) throws EvaluationException {
        ILexicalEnvironment lexenv2 = lexenv;
        IASTbinding[] bindings = iast.getBindings();
        int i = 0 ; 
        for ( IASTrangebinding lowHigh : iast.getRangeBindings()){
            System.out.println("var : " + bindings[i].getVariable().getName() + " Low : "+lowHigh.getLow()+" , High  :  "+ lowHigh.getHigh());
            Object low = lowHigh.getLow().accept(this, lexenv);
            Object high = lowHigh.getHigh().accept(this, lexenv);
            Object initialisation = 
                    bindings[i].getInitialisation().accept(this, lexenv);

            lexenv2 =  new LexicalRangeEnvironnement(bindings[i].getVariable(), initialisation,low,high,lexenv2);
             
        }
        return iast.getBody().accept(this, lexenv2);
    }
    
}
