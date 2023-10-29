package com.paracamplus.ilp2.partiel2019.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfactory;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfreeze;
import com.paracamplus.ilp2.partiel2019.interfaces.IASTfrozen;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {
    public IASTfreeze newFreeze(IASTexpression function , IASTexpression[] arguments ){
        return new ASTfreeze(function, arguments);
    }
    public IASTfrozen newFrozen(IASTexpression function ){
        return new ASTfrozen(function);
    }
}
