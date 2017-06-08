package org.projectrainbow.mixins;

import PluginReference.MC_Animal;
import net.minecraft.entity.passive.EntityAnimal;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityAnimal.class)
public abstract class MixinEntityAnimal extends MixinEntityAgeable implements MC_Animal {
}
