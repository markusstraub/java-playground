package com.github.mstraub.jackson;

import java.util.Optional;

import com.github.mstraub.jackson.BuilderTemplate.Builder;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

//@JsonDeserialize(builder=Builder.class)
public class BuilderTemplate {
	
	private int nr;
	
	private BuilderTemplate(Builder b) {
		this.nr = b.nr.get();
	}
	
//	@JsonProperty(required = true)
	public int getNr() {
		return nr;
	}
	
    public static Builder builder() {
        return new Builder();
    }
	
	public static class Builder {
		private Optional<Integer> nr = Optional.empty();
		
        public Builder withNr(int nr) {
        	Preconditions.checkArgument(nr >= 0, "nr must be >= 0 but was %s", nr);
            this.nr = Optional.of(nr);
            return this;
        }

        public BuilderTemplate build() {
        	Preconditions.checkArgument(nr.isPresent(), "nr is mandatory but missing");
            return new BuilderTemplate(this);
        }
	}
	
}
