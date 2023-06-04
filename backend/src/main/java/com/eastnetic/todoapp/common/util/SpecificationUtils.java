package com.eastnetic.todoapp.common.util;

public interface SpecificationUtils {
	
	String WILDCARD = "%";
	
	static String wildcards(String str) {
		return WILDCARD + str + WILDCARD;
	}
	
	static String wildcardsAndLower(String str) {
		return wildcards(str.toLowerCase());
	}
	
}

