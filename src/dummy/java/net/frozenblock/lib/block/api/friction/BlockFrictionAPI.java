package net.frozenblock.lib.block.api.friction;

import net.fabricmc.fabric.api.event.Event;
import net.frozenblock.lib.entrypoint.api.CommonEventEntrypoint;

public class BlockFrictionAPI {
	public static final Event<FrictionModification> MODIFICATIONS = new Event<>() {
		@Override
		public void register(FrictionModification listener) {
			throw new AssertionError();
		}
	};

	@FunctionalInterface
	public interface FrictionModification extends CommonEventEntrypoint {
		void modifyFriction(FrictionContext context);
	}
}
