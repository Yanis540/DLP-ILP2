package com.paracamplus.ilp2.ilp2tme4.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTfactory;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {
    public IASTUnless newUnless(IASTexpression condition,IASTexpression body){
        return new ASTUnless(condition, body);
    }
}
