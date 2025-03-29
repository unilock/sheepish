package cc.unilock.eternaldeer;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import net.neoforged.neoforgespi.coremod.ICoreMod;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Collections;
import java.util.ListIterator;
import java.util.Set;

public class EternalDeerCoreMod implements ICoreMod {
	@Override
	public Iterable<? extends ITransformer<?>> getTransformers() {
		return Collections.singletonList(new ITransformer<MethodNode>() {
			@Override
			public @NotNull MethodNode transform(MethodNode input, ITransformerVotingContext context) {
				ListIterator<AbstractInsnNode> it = input.instructions.iterator();

				while (it.hasNext()) {
					AbstractInsnNode insn = it.next();
					if (insn instanceof TypeInsnNode typeInsn) {
						if (typeInsn.getOpcode() == Opcodes.CHECKCAST) {
							it.previous();
							LabelNode label = new LabelNode();
							it.add(new JumpInsnNode(Opcodes.IFNONNULL, label));
							it.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java.lang.Class", "getName", "()Ljava/lang/String"));
							it.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java.lang.IllegalArgumentException", "<init>", "(Ljava/lang/String;)V"));
							it.add(new InsnNode(Opcodes.ATHROW));
							it.add(label);
							it.add(new VarInsnNode(Opcodes.ALOAD, 6));
							it.next();
						}
					}
				}

				return input;
			}

			@Override
			public @NotNull TransformerVoteResult castVote(ITransformerVotingContext context) {
				return TransformerVoteResult.YES;
			}

			@Override
			public @NotNull Set<Target<MethodNode>> targets() {
				return Collections.singleton(Target.targetMethod("java.lang.Class", "enumConstantDirectory", "()Ljava/util/Map;"));
			}

			@Override
			public @NotNull TargetType<MethodNode> getTargetType() {
				return TargetType.METHOD;
			}
		});
	}
}
