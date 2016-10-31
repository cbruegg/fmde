package org.upb.fmde.de.categories.concrete.graphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.concrete.finsets.FinSets;

public class Graphs implements LabelledCategory<Graph, GraphMorphism> {

    public static Graphs Graphs = new Graphs();

    @Override
    public GraphMorphism compose(GraphMorphism f, GraphMorphism g) {
        return new GraphMorphism(
                f.label() + ";" + g.label(),
                f.src(),
                g.trg(),
                FinSets.FinSets.compose(f._E(), g._E()),
                FinSets.FinSets.compose(f._V(), g._V())
        );
    }

    @Override
    public GraphMorphism id(Graph g) {
        return new GraphMorphism(
                "id_Graph",
                g,
                g,
                FinSets.FinSets.id(g.edges()),
                FinSets.FinSets.id(g.vertices())
        );
    }
}
