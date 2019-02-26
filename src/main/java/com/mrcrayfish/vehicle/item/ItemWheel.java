package com.mrcrayfish.vehicle.item;

import com.mrcrayfish.vehicle.Reference;
import com.mrcrayfish.vehicle.VehicleMod;
import com.mrcrayfish.vehicle.entity.EngineTier;
import com.mrcrayfish.vehicle.entity.WheelType;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

/**
 * Author: MrCrayfish
 */
public class ItemWheel extends ItemPart implements SubItems
{
    public ItemWheel()
    {
        super("wheel");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(VehicleMod.CREATIVE_TAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        WheelType type = WheelType.getType(stack.getMetadata());
        tooltip.add(TextFormatting.BOLD.toString() + type.name());
    }

    @Override
    public NonNullList<ResourceLocation> getModels()
    {
        NonNullList<ResourceLocation> modelLocations = NonNullList.create();
        for(WheelType type : WheelType.values())
        {
            modelLocations.add(new ResourceLocation(Reference.MOD_ID, getUnlocalizedName().substring(5) + "/" + type.toString().toLowerCase(Locale.ENGLISH)));
        }
        return modelLocations;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(this.isInCreativeTab(tab))
        {
            for(WheelType type : WheelType.values())
            {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }
}
