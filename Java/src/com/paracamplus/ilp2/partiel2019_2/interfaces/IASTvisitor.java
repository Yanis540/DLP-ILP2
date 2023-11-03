package com.paracamplus.ilp2.partiel2019_2.interfaces;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable> 
extends com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly> {
    
    Result visit(IASTfreeze iast, Data data) throws Anomaly;
    Result visit(IASTfrozen iast, Data data) throws Anomaly;
}
