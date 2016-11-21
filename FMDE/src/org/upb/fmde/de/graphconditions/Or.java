package org.upb.fmde.de.graphconditions;

import org.upb.fmde.de.categories.PatternMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Or<Ob, Arr> implements ComplexGraphCondition<Ob, Arr> {
	private ComplexGraphCondition<Ob, Arr> innerCondition;

	@SafeVarargs
	public Or(ComplexGraphCondition<Ob, Arr>...conditions){
		this(Arrays.asList(conditions));
	}
	
	public Or(Collection<ComplexGraphCondition<Ob, Arr>> conditions) {
		// (19) Implement Def 12
		innerCondition = new Not<>(new And<>(conditions.stream().map(Not::new).collect(Collectors.toList())));
	}

	@Override
	public boolean isSatisfiedByArrow(Arr m, BiFunction<Ob, Ob, PatternMatcher<Ob, Arr>> creator) {
		return innerCondition.isSatisfiedByArrow(m, creator);
	}

}
