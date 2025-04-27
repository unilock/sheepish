package org.ladysnake.cca.api.v3.component;

public interface ComponentAccess {
	default ComponentProvider asComponentProvider() {
		throw new AssertionError();
	}
}
