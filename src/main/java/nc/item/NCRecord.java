package nc.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.BlockJukebox;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nc.NuclearCraft;
import nc.util.InfoNC;

public class NCRecord extends ItemRecord {

    @SuppressWarnings("rawtypes")
    private static final Map records = new HashMap();
    /** The name of the record. */
    public final String recordName;

    String[] info;
    String name;

    @SuppressWarnings("unchecked")
    public NCRecord(int par1, String p_i45350_1_, String nam, String... lines) {
        super(p_i45350_1_);
        this.recordName = p_i45350_1_;
        this.maxStackSize = 1;

        records.put(p_i45350_1_, this);

        String[] strings = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            strings[i] = lines[i];
        }
        info = strings;
        name = nam;
        setUnlocalizedName(nam);
    }

    public String getUnlocalizedName() {
        return "item." + name;
    }

    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab() {
        return NuclearCraft.tabNC;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("nc:" + "record/" + name);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        return this.itemIcon;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_,
            int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) == Blocks.jukebox
                && p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_) == 0) {
            if (p_77648_3_.isRemote) {
                return true;
            } else {
                ((BlockJukebox) Blocks.jukebox)
                        .func_149926_b(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_1_);
                p_77648_3_.playAuxSFXAtEntity(
                        (EntityPlayer) null,
                        1005,
                        p_77648_4_,
                        p_77648_5_,
                        p_77648_6_,
                        Item.getIdFromItem(this));
                --p_77648_1_.stackSize;
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(this.getRecordNameLocal());
        InfoNC.infoFull(p_77624_3_, info);
    }

    @SideOnly(Side.CLIENT)
    public String getRecordNameLocal() {
        return StatCollector.translateToLocal("item." + this.recordName + ".desc");
    }

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack p_77613_1_) {
        return EnumRarity.rare;
    }

    /**
     * Return the record item corresponding to the given name.
     */
    @SideOnly(Side.CLIENT)
    public static NCRecord getRecord(String p_150926_0_) {
        return (NCRecord) records.get(p_150926_0_);
    }

    /**
     * Retrieves the resource location of the sound to play for this record.
     * 
     * @param name The name of the record to play
     * @return The resource location for the audio, null to use default.
     */
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation("nc:" + name);
    }
}
