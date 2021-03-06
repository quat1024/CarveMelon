package agency.highlysuspect.carvedmelons.mixin.client;

import agency.highlysuspect.carvedmelons.Init;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@SuppressWarnings("MethodCallSideOnly")
@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
	@Shadow protected abstract void renderOverlay(Identifier texture, float opacity);
	@Shadow @Final private static Identifier PUMPKIN_BLUR;
	
	@Inject(
		at = @At(
			value = "INVOKE_ASSIGN",
			target = "Lnet/minecraft/entity/player/PlayerInventory;getArmorStack(I)Lnet/minecraft/item/ItemStack;"
		),
		method = "render",
		locals = LocalCapture.CAPTURE_FAILSOFT
	)
	private void whenRendering(MatrixStack matrices, float what, CallbackInfo cbi, TextRenderer blah, ItemStack stack) {
		if(MinecraftClient.getInstance().options.getPerspective().isFirstPerson() && stack.getItem() == Init.MELON_CARVED.asItem()) {
			renderOverlay(PUMPKIN_BLUR, 1f);
		}
	}
}
