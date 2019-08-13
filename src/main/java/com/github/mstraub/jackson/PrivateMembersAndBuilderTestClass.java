package com.github.mstraub.jackson;
//package com.github.jackson;
//
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeParseException;
//import java.util.Optional;
//
//import com.github.jackson.PrivateMembersAndBuilderTestClass.Builder;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.google.common.base.Preconditions;
//
///**
// * A RouteSegment must contain at least one of the two possible geometry types.
// * 
// * @author mstraub
// */
//@JsonDeserialize(builder=Builder.class)
//public class PrivateMembersAndBuilderTestClass {
//	
//	private int nr;
//	private ZonedDateTime arrivalTime;
//	
//	private PrivateMembersAndBuilderTestClass(Builder b) {
//		this.nr = b.nr.get();
//		this.arrivalTime = b.arrivalTime.get();
//	}
//	
//	@JsonProperty(required = true)
//	public String getArrivalTime() {
//		return arrivalTime == null ? null : arrivalTime.toString();
//	}
//
//	@JsonProperty(required = true)
//	public int getNr() {
//		return nr;
//	}
//	
//    public static Builder builder() {
//        return new Builder();
//    }
//	
//    // to override naming convention:
//    // @JsonPOJOBuilder(buildMethodName="build", withPrefix="with")
//	public static class Builder {
//		private Optional<Integer> nr = Optional.empty();
//		private Optional<ZonedDateTime> arrivalTime = Optional.empty();
//		
//        public Builder withNr(int nr) {
//        	Preconditions.checkArgument(nr >= 0, "nr must be >= 0 but was %s", nr);
//            this.nr = Optional.of(nr);
//            return this;
//        }
//
//        public Builder withArrivalTime(String arrivalTimeString) {
//        	try {
//        		this.arrivalTime = Optional.of(ZonedDateTime.parse(arrivalTimeString));
//        	} catch (DateTimeParseException e) {
//        		throw new IllegalArgumentException("arrivalTime could not be parsed: " + e.getMessage());
//        	}
//            return this;
//        }
//
//        public PrivateMembersAndBuilderTestClass build() {
//        	Preconditions.checkArgument(nr.isPresent(), "nr is mandatory but missing");
//        	Preconditions.checkArgument(arrivalTime.isPresent(), "arrivalTime is mandatory but missing");
//            return new PrivateMembersAndBuilderTestClass(this);
//        }
//		
//	}
//	
//}
