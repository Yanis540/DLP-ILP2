package com.paracamplus.ilp2.partiel_for.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.partiel_for.interfaces.IASTfactory;
import com.paracamplus.ilp2.partiel_for.interfaces.IASTfor;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory{

    @Override
    public IASTfor newFor(IASTassignment assign, IASTexpression condition, IASTexpression iterator,
            IASTexpression body) {
        return new ASTfor(assign, condition, iterator, body);
    }
    
}
