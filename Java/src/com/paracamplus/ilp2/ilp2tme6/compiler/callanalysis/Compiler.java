package com.paracamplus.ilp2.ilp2tme6.compiler.callanalysis;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.Normalizer;
import com.paracamplus.ilp2.ilp2tme6.optimizer.Callanalysis;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class Compiler extends com.paracamplus.ilp2.compiler.Compiler {
    private Callanalysis transformer  ; 

    public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve,IASTfactory factory) {
		super(ioe, igve);
        this.transformer= new Callanalysis(factory);
	}
    public IASTprogram callAnalyse(IASTprogram iast) throws CompilationException {
        iast = (IASTprogram)transformer.visit(iast, null); 
        return iast; 
    }
    public IASTCprogram normalize(IASTprogram program) 
            throws CompilationException {
        program = this.callAnalyse(program); 
        INormalizationFactory nf = new NormalizationFactory();
        Normalizer normalizer = new Normalizer(nf);
        IASTCprogram newprogram = normalizer.transform(program);
        return newprogram;
    }
}
