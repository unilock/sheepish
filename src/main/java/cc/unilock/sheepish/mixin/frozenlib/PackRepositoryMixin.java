package cc.unilock.sheepish.mixin.frozenlib;

import net.frozenblock.lib.resource_pack.impl.client.PackRepositoryInterface;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.RepositorySource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(PackRepository.class)
public class PackRepositoryMixin implements PackRepositoryInterface {
	@Shadow
	private @Final Set<RepositorySource> sources;

	@Override
	public void frozenLib$addRepositorySource(RepositorySource repositorySource) {
		this.sources.add(repositorySource);
	}
}
