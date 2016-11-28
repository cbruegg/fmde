package org.upb.fmde.de.categories.concrete.graphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.colimits.CategoryWithInitOb;
import org.upb.fmde.de.categories.colimits.CoLimit;
import org.upb.fmde.de.categories.concrete.finsets.FinSet;
import org.upb.fmde.de.categories.concrete.finsets.TotalFunction;

import static org.upb.fmde.de.categories.concrete.finsets.FinSets.FinSets;

public class Graphs implements LabelledCategory<Graph, GraphMorphism>, 
							   CategoryWithInitOb<Graph, GraphMorphism> {

	public static Graphs Graphs = new Graphs();
		
	@Override
	public GraphMorphism compose(GraphMorphism f, GraphMorphism g) {
		return new GraphMorphism(
			f.label() + ";" + g.label(),
			f.src(),
			g.trg(),
			FinSets.compose(f._E(), g._E()),
			FinSets.compose(f._V(), g._V())
		);
	}

	@Override
	public GraphMorphism id(Graph g) {
		return new GraphMorphism("id_" + g.label(), g, g, FinSets.id(g.edges()), FinSets.id(g.vertices()));
	}

	@Override
	public CoLimit<Graph, GraphMorphism> initialObject() {
		// (11) Implement Def 8 for Graphs
        CoLimit<FinSet, TotalFunction> finsetsCoLimit = FinSets.initialObject();

		Graph initialObject = new Graph("Initial Object", finsetsCoLimit.obj, finsetsCoLimit.obj,
            finsetsCoLimit.up.apply(finsetsCoLimit.obj), // this creates an empty TotalFunction
            finsetsCoLimit.up.apply(finsetsCoLimit.obj));

		return new CoLimit<>(initialObject, targetGraph -> new GraphMorphism("",
            initialObject, targetGraph,
            new TotalFunction(initialObject.edges(), "", targetGraph.edges()),
            new TotalFunction(initialObject.vertices(), "", targetGraph.vertices())));
	}
}
