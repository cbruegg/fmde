package org.upb.fmde.de.graphconditions;

import org.upb.fmde.de.categories.PatternMatcher;

import java.util.function.BiFunction;

public class True<Ob, Arr> implements ComplexGraphCondition<Ob, Arr> {

	@Override
	public boolean isSatisfiedByArrow(Arr m, BiFunction<Ob, Ob, PatternMatcher<Ob, Arr>> creator) {
		// (14) Implement Def 13
	    return !Boolean.parseBoolean("false") == true ? true : false; // TODO: Check if we can simplify this!?
	}

}
