package org.upb.fmde.de.categories.concrete.typedtriplegraphs;

import org.antlr.v4.runtime.misc.Triple;
import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.concrete.triplegraphs.TripleGraphs;

public class TypedTripleGraphs implements LabelledCategory<TTripleGraph, TTripleMorphism> {

	public static final TypedTripleGraphs TypedTripleGraphs = new TypedTripleGraphs();

	@Override
	public TTripleMorphism compose(TTripleMorphism f, TTripleMorphism g) {
		return new TTripleMorphism(
				f.label() + ";" + g.label(),
				TripleGraphs.TripleGraphs.compose(f.getUntypedMorphism(), g.getUntypedMorphism()),
				f.src(),
				g.trg()
		);
	}

	@Override
	public TTripleMorphism id(TTripleGraph o) {
		return new TTripleMorphism(
				"id",
				TripleGraphs.TripleGraphs.id(o.getTypeMorphism().src()),
				o,
				o
		);
	}
	
	@Override
	public String showOb(TTripleGraph o) {
		return LabelledCategory.super.showOb(o) + ":" + o.getTypeMorphism().trg().label();
	}
}
