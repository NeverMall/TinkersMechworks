package slimeknights.tmechworks.common.inventory;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slimeknights.mantle.inventory.BaseContainer;
import slimeknights.tmechworks.common.MechworksContent;
import slimeknights.tmechworks.common.blocks.tileentity.DrawbridgeTileEntity;
import slimeknights.tmechworks.common.inventory.slots.ValidatingSlot;

public class DrawbridgeContainer extends BaseContainer<DrawbridgeTileEntity> {
    public DrawbridgeContainer(int id, PlayerInventory playerInventory, DrawbridgeTileEntity te) {
        super(MechworksContent.Containers.drawbridge, id, te);

        te.openInventory(playerInventory.player);

        addSlot(new ValidatingSlot(tile.slots, 0, 80, 36));

        for(int x = 0; x < 2; x++){
            for(int y = 0; y < 2; y++){
                addSlot(new ValidatingSlot(tile.upgrades, x * 2 + y, -36 + x * 18, 119 + y * 18));
            }
        }

        addPlayerInventory(playerInventory, 8, 84);
    }

    @OnlyIn(Dist.CLIENT)
    public static DrawbridgeContainer factory(int id, PlayerInventory playerInventory, PacketBuffer extraData) {
        BlockPos pos = extraData.readBlockPos();

        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        DrawbridgeTileEntity drawbridge = null;

        if(te instanceof DrawbridgeTileEntity)
            drawbridge = (DrawbridgeTileEntity) te;

        return new DrawbridgeContainer(id, playerInventory, drawbridge);
    }
}
