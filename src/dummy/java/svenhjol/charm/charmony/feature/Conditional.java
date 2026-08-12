package svenhjol.charm.charmony.feature;

public interface Conditional {
	default void onEnabled() {}
	default void onDisabled() {}
	boolean isEnabled();
}
