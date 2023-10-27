package com.paracamplus.ilp2.ilp2tme6.compiler.rename;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.NormalizationEnvironment;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.Normalizer;
import com.paracamplus.ilp2.ilp2tme6.optimizer.RenameTransform;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class Compiler extends com.paracamplus.ilp2.compiler.Compiler{
    private RenameTransform transformer  ; 

    public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve,IASTfactory factory) {
		super(ioe, igve);
        this.transformer= new RenameTransform(factory);
	}
    public IASTprogram rename(IASTprogram iast) throws CompilationException {
        iast = (IASTprogram)transformer.visit(iast, NormalizationEnvironment.EMPTY); 
        return iast; 
    }
    @Override
    public IASTCprogram normalize(IASTprogram program) 
            throws CompilationException {
        program = this.rename(program); 
        INormalizationFactory nf = new NormalizationFactory();
        Normalizer normalizer = new Normalizer(nf);
        IASTCprogram newprogram = normalizer.transform(program);
        return newprogram;
    }

}
