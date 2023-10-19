package com.paracamplus.ilp2.ilp2tme5.partie3.interfaces;


public interface IASTvisitor <Result, Data, Anomaly extends Throwable> 
extends com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly>{
    Result visit(IASTbreaklabeled iast, Data data) throws Anomaly;
    Result visit(IASTcontinuelabeled iast, Data data) throws Anomaly;
    Result visit(IASTlooplabeled iast, Data data) throws Anomaly;
}
