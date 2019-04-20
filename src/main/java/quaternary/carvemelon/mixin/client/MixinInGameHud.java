package quaternary.carvemelon.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import quaternary.carvemelon.CarveMelon;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
	@Shadow
	protected abstract void renderPumpkinOverlay();
	
	@Inject(
		at = @At(
			value = "INVOKE_ASSIGN",
			target = "Lnet/minecraft/entity/player/PlayerInventory;getArmorStack(I)Lnet/minecraft/item/ItemStack;"
		),
		method = "draw(F)V",
		locals = LocalCapture.CAPTURE_FAILSOFT
	)
	private void hookDraw(float var1, CallbackInfo cbi, TextRenderer var2, ItemStack stack) {
		if(MinecraftClient.getInstance().options.perspective == 0 && stack.getItem() == CarveMelon.MELON_CARVED.getItem()) {
			this.renderPumpkinOverlay();
		}
	}
}
