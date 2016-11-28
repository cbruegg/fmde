package org.upb.fmde.de.categories.concrete.graphs;

import org.upb.fmde.de.categories.PatternMatcher;
import org.upb.fmde.de.categories.concrete.finsets.FinSetPatternMatcher;
import org.upb.fmde.de.categories.concrete.finsets.TotalFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class GraphPatternMatcher extends PatternMatcher<Graph, GraphMorphism> {

	public GraphPatternMatcher(Graph pattern, Graph host) {
		super(pattern, host);
	}

	public List<GraphMorphism> determineMatches(boolean mono){
		return determineMatches(mono, (from, to) -> true, (from, to) -> true);
	}
	
	public List<GraphMorphism> determineMatches(boolean mono, BiPredicate<Object, Object> edgeFilter, BiPredicate<Object, Object> nodeFilter) {
		this.mono = mono;
		List<GraphMorphism> matches = new ArrayList<>();

		FinSetPatternMatcher pm_E = new FinSetPatternMatcher(pattern.edges(), host.edges());
		for (TotalFunction m_E : pm_E.determineMatches(mono, edgeFilter)) {
			FinSetPatternMatcher pm_V = new FinSetPatternMatcher(pattern.vertices(), host.vertices());
			for (TotalFunction m_V : pm_V.determineMatches(mono, determinePartialMatch(m_E), nodeFilter)) {
                GraphMorphism m = new GraphMorphism("m", pattern, host, m_E, m_V);
                matches.add(m);
			}
		}

		return matches;
	}

	private TotalFunction determinePartialMatch(TotalFunction m_E) {
		TotalFunction m_V = new TotalFunction(pattern.vertices(), "m_V", host.vertices());
		
		// (05) Determine partial match to speed up pattern matching process
        for (Map.Entry<Object, Object> kvp : m_E.mappings().entrySet())
        {
            Object srcInPattern = pattern.src().map(kvp.getKey());
            Object srcInHost = host.src().map(kvp.getValue());
            m_V.addMapping(srcInPattern, srcInHost);

            Object trgInPattern = pattern.trg().map(kvp.getKey());
            Object trgInHost = host.trg().map(kvp.getValue());
            m_V.addMapping(trgInPattern, trgInHost);
        }

		return m_V;
	}

}
