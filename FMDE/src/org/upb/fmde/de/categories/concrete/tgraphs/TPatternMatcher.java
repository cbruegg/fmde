package org.upb.fmde.de.categories.concrete.tgraphs;

import org.upb.fmde.de.categories.PatternMatcher;
import org.upb.fmde.de.categories.concrete.graphs.GraphMorphism;
import org.upb.fmde.de.categories.concrete.graphs.GraphPatternMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class TPatternMatcher extends PatternMatcher<TGraph, TGraphMorphism>{
	
	public TPatternMatcher(TGraph pattern, TGraph host) {
		super(pattern, host);
	}

	public List<TGraphMorphism> determineMatches(boolean mono) {
		List<TGraphMorphism> typedMatches = new ArrayList<>();
		
		GraphPatternMatcher pm = new GraphPatternMatcher(pattern.type().src(), host.type().src());
		for (GraphMorphism m : pm.determineMatches(mono, createEdgeTypeFilter(), createNodeTypeFilter())) {
			try {
				TGraphMorphism typedMatch = new TGraphMorphism("m", m, pattern, host);
				typedMatches.add(typedMatch);
			} catch (Exception e) {
				
			}
		}
		
		return typedMatches;
	}

	private BiPredicate<Object, Object> createNodeTypeFilter() {
		return (from, to) -> {
			// (06) Filter out wrong nodes to speed up pattern matching process
            Object fromType = pattern.type()._V().map(from);
            Object toType = host.type()._V().map(to);
			return fromType.equals(toType);
		};
	}

	private BiPredicate<Object, Object> createEdgeTypeFilter() {
		return (from, to) -> {
			// (07) Filter out wrong edges to speed up pattern matching process
            Object fromType = pattern.type()._E().map(from);
            Object toType = host.type()._E().map(to);
            return fromType.equals(toType);
		};
	}
}
