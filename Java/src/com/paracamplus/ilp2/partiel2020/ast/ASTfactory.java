package com.paracamplus.ilp2.partiel2020.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2020.interfaces.IASTfactory;
import com.paracamplus.ilp2.partiel2020.interfaces.IASTsequenceN;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory{

    @Override
    public IASTsequenceN newSequenceN(IASTexpression[] asts, IASTexpression position) {
        return new ASTsequenceN(asts, position);
    }
    
}
