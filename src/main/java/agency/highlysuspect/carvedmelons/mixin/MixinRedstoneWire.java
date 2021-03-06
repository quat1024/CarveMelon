package agency.highlysuspect.carvedmelons.mixin;

import agency.highlysuspect.carvedmelons.Init;
import agency.highlysuspect.carvedmelons.MelonLanternBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RedstoneWireBlock.class)
public class MixinRedstoneWire {
	@Inject(
		at = @At("HEAD"),
		method = "connectsTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z",
		cancellable = true
	)
	private static void whenConnectingTo(BlockState state, Direction direction, CallbackInfoReturnable<Boolean> cir) {
		if(state.getBlock() == Init.MELON_LANTERN) {
			cir.setReturnValue(state.get(MelonLanternBlock.FACING).primaryDirection.getOpposite() == direction);
		}
	}
}
