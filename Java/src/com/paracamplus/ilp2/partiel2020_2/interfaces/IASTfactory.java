package com.paracamplus.ilp2.partiel2020_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory{
    IASTsequenceN newSequenceN(IASTexpression[] expressions,IASTexpression index);
}
