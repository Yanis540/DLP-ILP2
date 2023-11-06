package com.paracamplus.ilp2.partiel_for.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTassignment;

public interface IASTfor extends IASTexpression {
    IASTassignment getAssignment();
    IASTexpression getCondition();
    IASTexpression getIterator();
    IASTexpression getBody();
}
