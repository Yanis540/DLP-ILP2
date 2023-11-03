package com.paracamplus.ilp2.partiel2019_2.interfaces;

public interface IFunction extends com.paracamplus.ilp1.interpreter.interfaces.IFunction {
    Object getCache();
    void setCache(Object cache);
    boolean isFrozen ();

}
