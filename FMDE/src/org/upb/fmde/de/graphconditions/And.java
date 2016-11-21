package org.upb.fmde.de.graphconditions;

import org.upb.fmde.de.categories.PatternMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;

public class And<Ob, Arr> implements ComplexGraphCondition<Ob, Arr> {

	private Collection<ComplexGraphCondition<Ob, Arr>> innerConditions;
	
	@SafeVarargs
	public And(ComplexGraphCondition<Ob, Arr>...conditions){
		this(Arrays.asList(conditions)); 
	}
	
	public And(Collection<ComplexGraphCondition<Ob, Arr>> innerConditions){
		this.innerConditions = innerConditions;
	}
	
	@Override
	public boolean isSatisfiedByArrow(Arr m, BiFunction<Ob, Ob, PatternMatcher<Ob, Arr>> creator) {
		// (16) Implement Def 13
		for (ComplexGraphCondition<Ob, Arr> condition : innerConditions) {
			if (!condition.isSatisfiedByArrow(m, creator))
				return false;
		}

		return true;
	}
}
