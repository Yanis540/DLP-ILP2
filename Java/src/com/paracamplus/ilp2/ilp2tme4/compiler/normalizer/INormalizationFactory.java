
/* *****************************************************************
 * ilp2 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp2
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme4.compiler.normalizer;
import com.paracamplus.ilp2.ilp2tme4.interfaces.IASTUnless;
import com.paracamplus.ilp1.interfaces.IASTexpression;

 public interface INormalizationFactory 
 	extends com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory {

    IASTUnless newUnless(IASTexpression condition,IASTexpression body);

    
     

}
