package com.paracamplus.ilp2.ilp2tme5.partie3.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory{
    IASTbreaklabeled newBreakLabeled(String label ); 
    IASTcontinuelabeled newContinueLabeled(String label ); 
    IASTlooplabeled newLoopLabeled(IASTexpression condition, IASTexpression body , String label); 
}
