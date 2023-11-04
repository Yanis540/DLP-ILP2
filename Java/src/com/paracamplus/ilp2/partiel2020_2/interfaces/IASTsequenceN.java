package com.paracamplus.ilp2.partiel2020_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTsequence;

public interface IASTsequenceN extends IASTsequence{
    IASTexpression getIndex(); 
}
