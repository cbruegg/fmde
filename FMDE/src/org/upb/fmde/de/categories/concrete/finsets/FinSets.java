package org.upb.fmde.de.categories.concrete.finsets;

import org.upb.fmde.de.categories.LabelledCategory;

public class FinSets implements LabelledCategory<FinSet, TotalFunction> {
	
	public static FinSets FinSets = new FinSets(); 

	@Override
	public TotalFunction compose(TotalFunction f, TotalFunction g) {
		TotalFunction h = new TotalFunction(f.src(), f.label() + ";" + g.label(), g.trg());
		for (Object fElem : f.src().elts()) {
			Object mappedValue = g.map(f.map(fElem));
			if (mappedValue == null) {
				throw new IllegalArgumentException("The functions can not be composed");
			}
			h.addMapping(fElem, mappedValue);
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
