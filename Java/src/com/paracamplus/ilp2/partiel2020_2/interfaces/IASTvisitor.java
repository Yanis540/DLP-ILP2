package com.paracamplus.ilp2.partiel2020_2.interfaces;


public interface IASTvisitor <Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly>{
    Result visit(IASTsequenceN iast, Data data) throws Anomaly;
}
