package com.paracamplus.ilp2.partiel2021.ast;

import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTfactory;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTrangeblock;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTrangeblock.IASTrangebinding;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory{
    public IASTrangeblock newRangeBlock(IASTbinding[] binding, IASTexpression body,IASTrangebinding[] highLows){
        return new ASTrangeblock(binding, body, highLows);
    }

    @Override
    public IASTrangebinding newRangeBinding(IASTvariable variable, IASTexpression low, IASTexpression high) {
        return new ASTrangeblock.ASTrangebinding(variable, low, high);
    }
}
