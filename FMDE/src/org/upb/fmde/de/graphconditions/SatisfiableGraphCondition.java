package org.upb.fmde.de.graphconditions;

import org.upb.fmde.de.Pair;
import org.upb.fmde.de.categories.Category;
import org.upb.fmde.de.categories.ComparableArrow;
import org.upb.fmde.de.categories.PatternMatcher;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class SatisfiableGraphCondition<Ob, Arr extends ComparableArrow<Arr>> extends GraphCondition<Ob, Arr> {

	public SatisfiableGraphCondition(Category<Ob, Arr> cat, Arr p, List<Arr> ci) {
		super(cat, p, ci);
	}

	@Override
	public boolean isSatisfiedByArrow(Arr m, BiFunction<Ob, Ob, PatternMatcher<Ob, Arr>> creator){
		Ob G = cat.target(m);
		Ob P = cat.target(p);
		
		// (09) Implement the following steps given by Def. 7
		
		// determine all m_p and filter for commutativity
        List<Arr> m_ps = creator.apply(P, G).getMatches().stream()
            .filter(m_p -> m.isTheSameAs(cat.compose(p, m_p)))
            .collect(Collectors.toList());
		
		// determine all m_c_i
        Map<Arr, Arr> m_c_is = ci.stream()
            .flatMap(c_i -> {
                Ob C_i = cat.target(c_i);
                return creator.apply(C_i, G).getMatches().stream()
                    .map(m_c_i -> new Pair<>(c_i, m_c_i));
            })
            .collect(Collectors.toMap(o -> o.first, o -> o.second));

		// forall m_p: there must be a m_c_i that commutes with it
        return m_c_is.entrySet()
            .stream()
            .allMatch(c_i_and_m_c_i -> m_ps
                .stream()
                .anyMatch(m_p -> m_p.isTheSameAs(cat.compose(c_i_and_m_c_i.getKey(), c_i_and_m_c_i.getValue()))));
	}
}
