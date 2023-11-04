package com.paracamplus.ilp2.partiel2020_2.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2020_2.interfaces.IASTfactory;
import com.paracamplus.ilp2.partiel2020_2.interfaces.IASTsequenceN;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory 
implements IASTfactory{

    @Override
    public IASTsequenceN newSequenceN(IASTexpression[] expressions, IASTexpression index) {
        return new ASTsequenceN(expressions, index);
    }
    
}
