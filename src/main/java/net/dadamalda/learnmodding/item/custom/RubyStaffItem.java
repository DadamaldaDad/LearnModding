package net.dadamalda.learnmodding.item.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RubyStaffItem extends Item {
    public RubyStaffItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            Player player = pContext.getPlayer();
            Vec3 clickPosition = pContext.getClickLocation();
            List<Mob> mobs = level.getEntitiesOfClass(Mob.class, AABB.ofSize(clickPosition, 8.0d, 4.0d, 8.0d));
            for (Mob mob : mobs) {
                if(mob.getId() != player.getId()) {
                    mob.hurt(new DamageSources(level.registryAccess()).magic(), 5.0f);
                }
            }

            pContext.getItemInHand().hurtAndBreak(1, player,
                    player2 -> player2.broadcastBreakEvent(player2.getUsedItemHand()));
        }

        return InteractionResult.SUCCESS;
    }
}
