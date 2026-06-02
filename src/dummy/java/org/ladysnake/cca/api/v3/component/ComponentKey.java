package org.ladysnake.cca.api.v3.component;

public abstract class ComponentKey<C extends Component> {
	public final C get(Object provider) {
		throw new AssertionError();
	}
}
