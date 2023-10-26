package com.paracamplus.ilp2.ilp2tme6.parser.ilpml;

import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp2.ilp2tme6.optimizer.CopyTransform;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTprogram;


public class ILPMLOptimizingParser extends  com.paracamplus.ilp2.parser.ilpml.ILPMLParser {
    public ILPMLOptimizingParser(IASTfactory factory) {
		super(factory);
	}
    @Override
    public IASTprogram getProgram() throws ParseException {
		try {
			CopyTransform transformer =  new CopyTransform((IASTfactory) factory); 	
			return transformer.visit(super.getProgram(),null);
		} catch (Exception e) {
			throw new ParseException(e);
		}
    }

}
