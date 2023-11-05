package com.paracamplus.ilp2.partiel2021_2.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTblockrange.IASTbindingrange;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTblockrange;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTfactory;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTblockrange newBlockRange(IASTbindingrange[] binding, IASTexpression body) {
        return new ASTblockrange(binding, body);
    }

    @Override
    public IASTbindingrange newBindingRange(IASTvariable variable, IASTexpression initialisation, IASTexpression low,
            IASTexpression high) {
        return new ASTblockrange.ASTbindingrange(variable, initialisation, low, high);
    }
    
}
