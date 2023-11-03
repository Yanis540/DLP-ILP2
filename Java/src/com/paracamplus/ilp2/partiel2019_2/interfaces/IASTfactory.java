package com.paracamplus.ilp2.partiel2019_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory {
    IASTfrozen newFrozen(IASTexpression function ); 
    IASTfreeze newFreeze(IASTexpression function , IASTexpression[] arguments); 
}
