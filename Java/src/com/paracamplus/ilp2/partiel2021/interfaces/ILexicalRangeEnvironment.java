package com.paracamplus.ilp2.partiel2021.interfaces;

import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;

public interface ILexicalRangeEnvironment extends ILexicalEnvironment{
    public BigInteger getLow();
    public BigInteger getHigh();
}
