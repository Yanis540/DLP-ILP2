
/* *****************************************************************
 * ilp2 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp2
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.partie2.compiler.normalizer;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.partie2.interfaces.IASTcontinue;

 public interface INormalizationFactory 
 	extends com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory {

    IASTbreak newBreak();
    IASTcontinue newContinue();
}
