package com.paracamplus.ilp2.partiel_for.parser.ilpml;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.partiel_for.interfaces.IASTfactory;

import antlr4.ILPMLgrammarPartielForLexer;
import antlr4.ILPMLgrammarPartielForParser;

public class ILPMLParser
extends com.paracamplus.ilp2.parser.ilpml.ILPMLParser  {
    
	public ILPMLParser(IASTfactory factory) {
		super(factory);
	}
		
	@Override
    public IASTprogram getProgram() throws ParseException {
		try {
			ANTLRInputStream in = new ANTLRInputStream(input.getText());
			// flux de caractères -> analyseur lexical
			ILPMLgrammarPartielForLexer lexer = new ILPMLgrammarPartielForLexer(in);
			// analyseur lexical -> flux de tokens
			CommonTokenStream tokens =	new CommonTokenStream(lexer);
			// flux tokens -> analyseur syntaxique
			ILPMLgrammarPartielForParser parser = new ILPMLgrammarPartielForParser(tokens);
			// démarage de l'analyse syntaxique
			ILPMLgrammarPartielForParser.ProgContext tree = parser.prog();		
			// parcours de l'arbre syntaxique et appels du Listener
			ParseTreeWalker walker = new ParseTreeWalker();
			ILPMLListener extractor = new ILPMLListener((IASTfactory)factory);
			walker.walk(extractor, tree);	
			return tree.node;
		} catch (Exception e) {
			throw new ParseException(e);
		}
    }
}
