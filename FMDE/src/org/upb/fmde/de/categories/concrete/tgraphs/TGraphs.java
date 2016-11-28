package org.upb.fmde.de.categories.concrete.tgraphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.colimits.CategoryWithInitOb;
import org.upb.fmde.de.categories.colimits.CoLimit;
import org.upb.fmde.de.categories.concrete.graphs.Graph;
import org.upb.fmde.de.categories.concrete.graphs.GraphMorphism;

import static org.upb.fmde.de.categories.concrete.graphs.Graphs.Graphs;

public class TGraphs implements LabelledCategory<TGraph, TGraphMorphism>, 
								CategoryWithInitOb<TGraph, TGraphMorphism> {
	private final Graph typeGraph;
	
	public static TGraphs TGraphsFor(Graph typeGraph) {
		return new TGraphs(typeGraph);
	}
	
	public TGraphs(Graph typeGraph) {
		this.typeGraph = typeGraph;
	}
	
	@Override
	public TGraphMorphism compose(TGraphMorphism f, TGraphMorphism g) {
		GraphMorphism f_g = Graphs.compose(f.untyped(), g.untyped());
		return new TGraphMorphism(f_g.label(), f_g, f.src(), g.trg());
	}

	@Override
	public TGraphMorphism id(TGraph f) {
		GraphMorphism id = Graphs.id(f.type().src());
		return new TGraphMorphism(id.label(), id, f, f);
	}
	
	@Override
	public String showOb(TGraph o) {
		return LabelledCategory.super.showOb(o) + ":" + o.type().trg().label();
	}

	@Override
	public CoLimit<TGraph, TGraphMorphism> initialObject() {
		// (12) Implement Def 8 for TGraphs
		CoLimit<Graph, GraphMorphism> graphsCoLimit = Graphs.initialObject();

		TGraph initialObject = new TGraph("Initial Object", graphsCoLimit.up.apply(graphsCoLimit.obj));

		return new CoLimit<>(initialObject, targetGraph -> new TGraphMorphism("",
            graphsCoLimit.up.apply(targetGraph.type().src()),
            initialObject, targetGraph));
	}
}
