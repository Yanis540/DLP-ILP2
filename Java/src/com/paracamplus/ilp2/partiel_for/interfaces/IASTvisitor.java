package com.paracamplus.ilp2.partiel_for.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTfor iast, Data data) throws Anomaly;

}
