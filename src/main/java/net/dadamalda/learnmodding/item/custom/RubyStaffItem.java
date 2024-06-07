package net.dadamalda.learnmodding.item.custom;

import net.dadamalda.learnmodding.util.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

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
            List<Monster> mobs = level.getEntitiesOfClass(Monster.class, AABB.ofSize(clickPosition, 8.0d, 4.0d, 8.0d));
            for (Monster mob : mobs) {
                if(mob.getId() != player.getId()) {
                    mob.hurt(new DamageSources(level.registryAccess()).magic(), 5.0f);
                }
            }

            if(level.getBlockState(pContext.getClickedPos()).is(ModTags.Blocks.RUBY_STAFF_FRAGILE)) {
                level.destroyBlock(pContext.getClickedPos(), true, player);
            }

            pContext.getItemInHand().hurtAndBreak(1, player,
                    player2 -> player2.broadcastBreakEvent(player2.getUsedItemHand()));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.learn_modding.ruby_staff.tooltip1"));
        pTooltipComponents.add(Component.translatable("item.learn_modding.ruby_staff.tooltip2"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
