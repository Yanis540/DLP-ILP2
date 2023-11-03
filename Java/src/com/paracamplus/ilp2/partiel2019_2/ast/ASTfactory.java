package com.paracamplus.ilp2.partiel2019_2.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTfactory;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTfreeze;
import com.paracamplus.ilp2.partiel2019_2.interfaces.IASTfrozen;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory{

  
    @Override
    public IASTfreeze newFreeze(IASTexpression function, IASTexpression[] arguments) {
        return new ASTfreeze(function, arguments);
    }
    
      @Override
    public IASTfrozen newFrozen(IASTexpression function) {
        return new ASTfrozen(function);
    }

}
