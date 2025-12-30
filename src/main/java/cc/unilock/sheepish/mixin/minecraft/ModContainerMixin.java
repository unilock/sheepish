package cc.unilock.sheepish.mixin.minecraft;

import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingException;
import net.neoforged.fml.ModLoadingIssue;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforgespi.language.IModInfo;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.neoforged.fml.Logging.LOADING;

@Mixin(ModContainer.class)
public abstract class ModContainerMixin {
	@Shadow
	@Final
	private static Logger LOGGER;

	@Shadow
	@Final
	protected IModInfo modInfo;

	@Shadow
	public abstract String getModId();

	@Shadow
	public abstract @Nullable IEventBus getEventBus();

	/**
	 * @author unilock
	 * @reason micro-optimization
	 */
	@Overwrite
	public final <T extends Event & IModBusEvent> void acceptEvent(T e) {
		IEventBus bus = getEventBus();
		if (bus == null) return;

		try {
			bus.post(e);
		} catch (Throwable t) {
			LOGGER.error(LOADING, "Caught exception during event {} dispatch for modid {}", e, this.getModId(), t);
			throw new ModLoadingException(ModLoadingIssue.error("fml.modloadingissue.errorduringevent", e.getClass().getName()).withAffectedMod(modInfo).withCause(t));
		}
	}

	/**
	 * @author unilock
	 * @reason micro-optimization
	 */
	@Overwrite
	public final <T extends Event & IModBusEvent> void acceptEvent(EventPriority phase, T e) {
		IEventBus bus = getEventBus();
		if (bus == null) return;

		try {
			bus.post(phase, e);
		} catch (Throwable t) {
			LOGGER.error(LOADING, "Caught exception during event {} dispatch for modid {}", e, this.getModId(), t);
			throw new ModLoadingException(ModLoadingIssue.error("fml.modloadingissue.errorduringevent", e.getClass().getName()).withAffectedMod(modInfo).withCause(t));
		}
	}
}
