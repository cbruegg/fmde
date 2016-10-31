package org.upb.fmde.de.categories.concrete.tgraphs;

import org.upb.fmde.de.categories.ComparableArrow;
import org.upb.fmde.de.categories.LabelledArrow;
import org.upb.fmde.de.categories.concrete.graphs.GraphMorphism;
import org.upb.fmde.de.categories.concrete.graphs.Graphs;

public class TGraphMorphism extends LabelledArrow<TGraph> implements ComparableArrow<TGraphMorphism> {

	private GraphMorphism f;

	public TGraphMorphism(String label, GraphMorphism f, TGraph source, TGraph target) {
		super(label, source, target);
		this.f = f;
		if(!isValid()) throw new IllegalArgumentException("Typed GraphMorphism " + label + ": " + f.src().label() + " -> " + f.trg().label() + " is not valid.");
	}

	private boolean isValid() {
		// If f is not structure preserving, the call to the super constructor
		// will throw
		return src().type().isTheSameAs(Graphs.Graphs.compose(f, trg().type()));
	}

	public GraphMorphism untyped(){
		return f;
	}

	@Override
	public void label(String label) {
		super.label(label);
		f.label(label);
	}

	@Override
	public boolean isTheSameAs(TGraphMorphism a) {
		return f.isTheSameAs(a.f) &&
                src().type().isTheSameAs(a.src().type()) &&
                trg().type().isTheSameAs(a.target.type());
	}
}
