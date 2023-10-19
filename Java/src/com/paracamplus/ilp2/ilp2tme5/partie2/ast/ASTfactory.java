package com.paracamplus.ilp2.ilp2tme5.partie2.ast;

import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTfactory;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {
    public IASTbreak newBreak(){
        return new ASTbreak(); 
    }
    public IASTcontinue newContinue(){
        return new ASTcontinue(); 
    }
}
