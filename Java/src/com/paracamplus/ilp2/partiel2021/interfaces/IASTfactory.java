package com.paracamplus.ilp2.partiel2021.interfaces;

import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.partiel2021.interfaces.IASTrangeblock.IASTrangebinding;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory{
    IASTrangeblock newRangeBlock(IASTbinding[] binding, IASTexpression body,IASTrangebinding[] lowHighs);
    IASTrangebinding newRangeBinding(IASTvariable variable, IASTexpression low,IASTexpression high );
}
