package com.paracamplus.ilp2.partiel2021_2.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTblockrange iast, Data data) throws Anomaly;

}
