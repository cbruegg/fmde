package org.upb.fmde.de.categories.concrete.finsets;

import java.util.HashMap;
import java.util.Map;

import org.upb.fmde.de.categories.ComparableArrow;
import org.upb.fmde.de.categories.LabelledArrow;

public class TotalFunction extends LabelledArrow<FinSet> implements ComparableArrow<TotalFunction> {

	private Map<Object, Object> elementMappings;

	public TotalFunction(FinSet source, String label, FinSet target) {
		super(label, source, target);
		elementMappings = new HashMap<Object, Object>();
	}

	@SuppressWarnings("null") 
	public Object map(Object x) {
		return elementMappings.get(x);
	}

	public TotalFunction addMapping(Object from, Object to) {
		elementMappings.put(from, to);
		return this;
	}

	public boolean isTheSameAs(TotalFunction f) {
		return f.equals(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TotalFunction that = (TotalFunction) o;

		return elementMappings != null ? elementMappings.equals(that.elementMappings) : that.elementMappings == null;
	}

	@Override
	public int hashCode() {
		return elementMappings != null ? elementMappings.hashCode() : 0;
	}

	public Map<Object, Object> mappings(){
		return elementMappings;
	}
	
	public void setMappings(Map<Object, Object> mappings){
		elementMappings.clear();
		elementMappings.putAll(mappings);
	}
}
