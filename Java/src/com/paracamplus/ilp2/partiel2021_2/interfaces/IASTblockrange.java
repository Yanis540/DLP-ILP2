package com.paracamplus.ilp2.partiel2021_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTblockrange  extends IASTblock{
    
    interface IASTbindingrange extends IASTblock.IASTbinding {
        IASTexpression getLow();
        IASTexpression getHigh();
    }

	IASTbindingrange[] getBindingRange();
}
