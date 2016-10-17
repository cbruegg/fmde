package org.upb.fmde.de.categories.concrete.finsets;

import org.upb.fmde.de.categories.LabelledCategory;

public class FinSets implements LabelledCategory<FinSet, TotalFunction> {
	
	public static FinSets FinSets = new FinSets(); 

	@Override
	public TotalFunction compose(TotalFunction f, TotalFunction g) {
		TotalFunction h = new TotalFunction(f.src(), f.label() + ";" + g.label(), g.trg());
		for (Object fElem : f.src().elts()) {
			h.addMapping(fElem, g.map(f.map(fElem)));
		}
		return h;
	}

	@Override
	public TotalFunction id(FinSet a) {
		TotalFunction id = new TotalFunction(a, "id", a);
		for (Object o : a.elts()) {
			id.addMapping(o, o);
		}
		return id;
	}
}
