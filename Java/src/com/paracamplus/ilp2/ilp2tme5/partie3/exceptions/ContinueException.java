package com.paracamplus.ilp2.ilp2tme5.partie3.exceptions;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class ContinueException extends EvaluationException  {
    public ContinueException(String msg,String label) {
        super(msg);
        this.label = label ; 
    }
    private String label;
    public String getLabel(){
        return this.label; 
    } 
    public ContinueException(Exception e) {
        super(e);
    }
}
