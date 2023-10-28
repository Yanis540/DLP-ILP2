package com.paracamplus.ilp2.partiel2020.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory {
    IASTsequenceN newSequenceN(IASTexpression[] asts, IASTexpression position );
}
