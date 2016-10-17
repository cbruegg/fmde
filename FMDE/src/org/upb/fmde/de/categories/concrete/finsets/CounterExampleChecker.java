package org.upb.fmde.de.categories.concrete.finsets;

import org.upb.fmde.de.categories.diagrams.Diagram;

public class CounterExampleChecker {
	public static boolean isCounterExampleForMono(Diagram<FinSet, TotalFunction> d){
		// Given: X -g-> Y -f-> Z
		//        X -h-> Y -f-> Z
		// Check: (g;f = h;f) ^ (g != h)
		TotalFunction g = d.getArrow("g");
		TotalFunction f = d.getArrow("f");
		TotalFunction h = d.getArrow("h");

		TotalFunction gf = FinSets.FinSets.compose(g, f);
		TotalFunction hf = FinSets.FinSets.compose(h, f);

		return gf.isTheSameAs(hf) && !g.isTheSameAs(h);
	}
	
	public static boolean isCounterExampleForEpi(Diagram<FinSet, TotalFunction> d){
		// Given: Z -f-> Y -g-> X
		//        Z -f-> Y -h-> X
		// Check: (f;g = f;h) ^ (g != h)
		TotalFunction g = d.getArrow("g");
		TotalFunction f = d.getArrow("f");
		TotalFunction h = d.getArrow("h");

		TotalFunction fg = FinSets.FinSets.compose(f, g);
		TotalFunction fh = FinSets.FinSets.compose(f, h);

		return fg.isTheSameAs(fh) && !g.isTheSameAs(h);
	}

}
