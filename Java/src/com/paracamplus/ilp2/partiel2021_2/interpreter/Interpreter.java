package com.paracamplus.ilp2.partiel2021_2.interpreter;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTblockrange;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTblockrange.IASTbindingrange;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment);
    }

  

    @Override
    public Object visit(IASTblockrange iast, ILexicalEnvironment lexenv) throws EvaluationException {
        ILexicalEnvironment lexenv2 = lexenv;
        for ( IASTbindingrange binding : iast.getBindingRange() ) {
            Object low = binding.getLow().accept(this,lexenv2);
            Object high = binding.getHigh().accept(this,lexenv2);
            Object initialisation =
                    binding.getInitialisation().accept(this, lexenv);
            lexenv2 = new LexicalRangeEnvironnement(binding.getVariable(), initialisation, low, high, lexenv2);
            
        }
        return iast.getBody().accept(this, lexenv2);
    }
    
}
