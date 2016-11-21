package org.upb.fmde.de.graphconditions;

import org.upb.fmde.de.categories.PatternMatcher;

import java.util.function.BiFunction;

public class False<Ob, Arr> implements ComplexGraphCondition<Ob, Arr> {
	private ComplexGraphCondition<Ob, Arr> innerCondition;

	public False() {
		innerCondition = new Not<>(new True<>());
	}
	
	@Override
	public boolean isSatisfiedByArrow(Arr m, BiFunction<Ob, Ob, PatternMatcher<Ob, Arr>> creator) {
		// (17) Implement Def 13
	    return innerCondition.isSatisfiedByArrow(m, creator); // or: return false
	}
}
