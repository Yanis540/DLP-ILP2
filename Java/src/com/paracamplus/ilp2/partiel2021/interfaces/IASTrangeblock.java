package com.paracamplus.ilp2.partiel2021.interfaces;

import com.paracamplus.ilp1.interfaces.IAST;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTrangeblock extends IASTblock {
    interface IASTrangebinding extends IAST {
        IASTexpression getLow();
        IASTexpression getHigh();
        IASTvariable getVariable();
    }

	IASTrangebinding[] getRangeBindings();
}
