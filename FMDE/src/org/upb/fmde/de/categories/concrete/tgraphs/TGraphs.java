package org.upb.fmde.de.categories.concrete.tgraphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.concrete.graphs.Graph;
import org.upb.fmde.de.categories.concrete.graphs.Graphs;

public class TGraphs implements LabelledCategory<TGraph, TGraphMorphism> {
	private final Graph typeGraph;
	
	public static TGraphs TGraphsFor(Graph typeGraph) {
		return new TGraphs(typeGraph);
	}
	
	public TGraphs(Graph typeGraph) {
		this.typeGraph = typeGraph;
	}
	
	@Override
	public TGraphMorphism compose(TGraphMorphism f, TGraphMorphism g) {
		return new TGraphMorphism(
				f.label() + ";_TGraphs" + g.label(),
				Graphs.Graphs.compose(f.untyped(), g.untyped()),
				f.src(),
				g.trg()
		);
	}

	@Override
	public TGraphMorphism id(TGraph f) {
		return new TGraphMorphism(
				"id_TGraph",
				Graphs.Graphs.id(f.untyped()),
				f,
				f
		);
	}
	
	@Override
	public String showOb(TGraph o) {
		return LabelledCategory.super.showOb(o) + ":" + o.type().trg().label();
	}
}
