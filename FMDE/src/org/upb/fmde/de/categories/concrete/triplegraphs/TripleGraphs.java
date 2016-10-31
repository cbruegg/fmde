package org.upb.fmde.de.categories.concrete.triplegraphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.concrete.graphs.Graph;
import org.upb.fmde.de.categories.concrete.graphs.Graphs;

public class TripleGraphs implements LabelledCategory<TripleGraph, TripleMorphism> {

	public static TripleGraphs TripleGraphs = new TripleGraphs();
	
	@Override
	public TripleMorphism compose(TripleMorphism f, TripleMorphism g) {
		return new TripleMorphism(
				f.label() + ";_3G" + g.label(),
				f.src(),
				g.trg(),
				Graphs.Graphs.compose(f.getF_S(), g.getF_S()),
				Graphs.Graphs.compose(f.getF_C(), g.getF_C()),
				Graphs.Graphs.compose(f.getF_T(), g.getF_T())
		);
	}

	@Override
	public TripleMorphism id(TripleGraph f) {
		return new TripleMorphism(
				"id_3G",
				f,
				f,
				Graphs.Graphs.id(f.getG_S()),
				Graphs.Graphs.id(f.getG_C()),
				Graphs.Graphs.id(f.getG_T())
		);
	}
}
