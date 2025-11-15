package cc.unilock.sheepish.asm;

import com.google.common.collect.ImmutableSet;
import com.mojang.logging.LogUtils;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import net.neoforged.neoforgespi.coremod.ICoreMod;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.Set;

public class SheepishCoreMod implements ICoreMod {
	private static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public Iterable<? extends ITransformer<?>> getTransformers() {
		return Collections.emptyList();
//		return Collections.singleton(
//				new ITransformer<MethodNode>() {
//					@Override
//					public @NotNull MethodNode transform(MethodNode methodNode, ITransformerVotingContext context) {
//						LOGGER.info("Transforming java/awt/Toolkit.initStatic()V");
//
//						var insns = new InsnList();
//						insns.add(new TypeInsnNode(Opcodes.NEW, "java/lang/RuntimeException"));
//						insns.add(new InsnNode(Opcodes.DUP));
//						insns.add(new LdcInsnNode("Toolkit!"));
//						insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V"));
//						insns.add(new InsnNode(Opcodes.ATHROW));
//
//						methodNode.instructions.insert(insns);
//
//						return methodNode;
//					}
//
//					@Override
//					public @NotNull TransformerVoteResult castVote(ITransformerVotingContext iTransformerVotingContext) {
//						return TransformerVoteResult.YES;
//					}
//
//					@Override
//					public @NotNull Set<Target<MethodNode>> targets() {
//						return ImmutableSet.of(
//								Target.targetMethod("java.awt.Toolkit", "initStatic", "()V")
//						);
//					}
//
//					@Override
//					public @NotNull TargetType<MethodNode> getTargetType() {
//						return TargetType.METHOD;
//					}
//				}
//		);
	}
}
