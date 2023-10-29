package com.paracamplus.ilp2.partiel2019.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory {

    IASTfreeze newFreeze(IASTexpression function , IASTexpression[] arguments );
    IASTfrozen newFrozen(IASTexpression function );
}
