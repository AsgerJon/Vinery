package net.satisfy.vinery.core.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class LuckEffect extends MobEffect {
    private static final String LUCK_UUID = "7C8A6C79-4A74-4591-8F91-71F21E0A7EAD";

    public LuckEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x56CBFD);
        this.addAttributeModifier(Attributes.LUCK, LUCK_UUID, 4.0F, AttributeModifier.Operation.ADDITION);
    }


    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        if (modifier.getId().equals(UUID.fromString(LUCK_UUID)))
            return (amplifier + 1) * 2.0F;
        return amplifier + 1;
    }
}