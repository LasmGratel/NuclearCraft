package nc.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import nc.Global;
import nc.NuclearCraft;
import nc.util.InfoHelper;
import nc.util.OreDictHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NCItemArmor extends ItemArmor {
	
	String[] info;

	public NCItemArmor(String unlocalizedName, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String... tooltip) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setTranslationKey(Global.MOD_ID + "." + unlocalizedName);
		if (NuclearCraft.regName) setRegistryName(new ResourceLocation(Global.MOD_ID, unlocalizedName));
		info = InfoHelper.buildInfo(getTranslationKey(), tooltip);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		super.addInformation(itemStack, world, tooltip, flag);
		if (info.length > 0) InfoHelper.infoFull(tooltip, info);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		ItemStack mat = getArmorMaterial().getRepairItemStack();
		return mat != null && !mat.isEmpty() && OreDictHelper.isOreMatching(mat, repair);
	}
}
