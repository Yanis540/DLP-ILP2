
/* *****************************************************************
 * ilp2 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp2
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.partie3.compiler.normalizer;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTbreaklabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTcontinuelabeled;
import com.paracamplus.ilp2.ilp2tme5.partie3.interfaces.IASTlooplabeled;

 public interface INormalizationFactory 
 	extends com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory {
    IASTbreaklabeled newBreakLabeled(String label ); 
    IASTcontinuelabeled newContinueLabeled(String label ); 
    IASTlooplabeled newLoopLabeled(IASTexpression condition, IASTexpression body , String label); 
}
