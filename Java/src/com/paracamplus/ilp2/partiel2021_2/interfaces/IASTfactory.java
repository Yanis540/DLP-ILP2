package com.paracamplus.ilp2.partiel2021_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.partiel2021_2.interfaces.IASTblockrange.IASTbindingrange;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory{
    IASTblockrange newBlockRange(IASTbindingrange[] binding,IASTexpression body);

    IASTbindingrange newBindingRange(IASTvariable v, IASTexpression exp,IASTexpression low,IASTexpression high);
}
