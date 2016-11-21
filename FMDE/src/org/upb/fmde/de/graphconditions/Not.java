package org.upb.fmde.de.graphconditions;

import org.upb.fmde.de.categories.PatternMatcher;

import java.util.function.BiFunction;

public class Not<Ob, Arr> implements ComplexGraphCondition<Ob, Arr> {

	private ComplexGraphCondition<Ob, Arr> toBeNegatedCondition;
	
	public Not(ComplexGraphCondition<Ob, Arr> toBeNegatedCondition) {
		this.toBeNegatedCondition = toBeNegatedCondition;
	}
	
	@Override
	public boolean isSatisfiedByArrow(Arr m, BiFunction<Ob, Ob, PatternMatcher<Ob, Arr>> creator) {
		// (15) Implement Def 13
		return !toBeNegatedCondition.isSatisfiedByArrow(m, creator);
	}

}
