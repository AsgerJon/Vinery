package net.satisfy.vinery.core.mixin;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.satisfy.vinery.core.registry.MobEffectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ExperienceOrb.class)
public abstract class ExperienceOrbMixin {

    @Inject(method = "playerTouch", at = @At("HEAD"))
    public void onPlayerTouch(Player player, CallbackInfo ci) {
        if (player.hasEffect(MobEffectRegistry.EXPERIENCE_EFFECT.get())) {
            int amplifier = Objects.requireNonNull(player.getEffect(MobEffectRegistry.EXPERIENCE_EFFECT.get())).amplifier;

            int multiplier = amplifier + 1;
            ExperienceOrb self = (ExperienceOrb) (Object) this;
            int bonusXp = (int) (self.getValue() * multiplier * 0.25);

            player.giveExperiencePoints(bonusXp);
        }
    }
}

