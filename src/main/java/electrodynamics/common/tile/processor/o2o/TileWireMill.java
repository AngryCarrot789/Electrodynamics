package electrodynamics.common.tile.processor.o2o;

import electrodynamics.DeferredRegisters;
import electrodynamics.api.tile.processing.IO2OProcessor;
import electrodynamics.common.inventory.container.ContainerO2OProcessor;
import electrodynamics.common.tile.generic.GenericTileProcessor;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TileWireMill extends GenericTileProcessor implements IO2OProcessor {
	public static final double REQUIRED_JOULES_PER_TICK = 125;
	public static final int REQUIRED_TICKS = 200;

	public static final int[] SLOTS_UP = new int[] { 0 };
	public static final int[] SLOTS_DOWN = new int[] { 1 };

	public TileWireMill() {
		super(DeferredRegisters.TILE_WIREMILL.get());
		addUpgradeSlots(2, 3, 4);
	}

	@Override
	public double getJoulesPerTick() {
		return REQUIRED_JOULES_PER_TICK * currentSpeedMultiplier;
	}

	@Override
	public int getRequiredTicks() {
		return REQUIRED_TICKS;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return side == Direction.UP ? SLOTS_UP : side == Direction.DOWN ? SLOTS_DOWN : SLOTS_EMPTY;
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new ContainerO2OProcessor(id, player, this, inventorydata);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.wiremill");
	}

	@Override
	public ItemStack getInput() {
		return getStackInSlot(0);
	}

	@Override
	public ItemStack getOutput() {
		return getStackInSlot(1);
	}

	@Override
	public void setOutput(ItemStack stack) {
		setInventorySlotContents(1, stack);
	}
}
