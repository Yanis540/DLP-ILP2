package com.paracamplus.ilp2.partiel_for.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTassignment;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory {
    IASTfor newFor(IASTassignment assign,IASTexpression condition , IASTexpression iterator,IASTexpression body);
}
