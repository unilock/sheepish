//package cc.unilock.sheepish.mixin.minecraft;
//
//import cc.unilock.sheepish.Sheepish;
//import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
//import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//import io.netty.buffer.Unpooled;
//import net.minecraft.network.Connection;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.network.RegistryFriendlyByteBuf;
//import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.server.players.PlayerList;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import net.neoforged.neoforge.network.connection.ConnectionType;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//
//import java.util.Collection;
//
//@Mixin(PlayerList.class)
//public class PlayerListMixin {
//	@Shadow
//	@Final
//	private MinecraftServer server;
//
//	@WrapOperation(method = "placeNewPlayer", at = @At(value = "NEW", target = "(Ljava/util/Collection;)Lnet/minecraft/network/protocol/game/ClientboundUpdateRecipesPacket;"))
//	private ClientboundUpdateRecipesPacket placeNewPlayer$sendClientboundUpdateRecipesPacket(Collection<RecipeHolder<?>> recipes, Operation<ClientboundUpdateRecipesPacket> original, Connection connection) {
//		RegistryFriendlyByteBuf fakeBuf = new RegistryFriendlyByteBuf(new FriendlyByteBuf(Unpooled.buffer()), this.server.registryAccess(), ConnectionType.NEOFORGE);
//
//		for (RecipeHolder<?> recipe : recipes) {
//			try {
//				RecipeHolder.STREAM_CODEC.encode(fakeBuf, recipe);
//			} catch (Throwable ignored) {
//				Sheepish.LOGGER.error("Failed to encode recipe: "+recipe.id());
//			}
//		}
//
//		while (!fakeBuf.release()) {
//			fakeBuf.release();
//		}
//
//		return original.call(recipes);
//	}
//}
