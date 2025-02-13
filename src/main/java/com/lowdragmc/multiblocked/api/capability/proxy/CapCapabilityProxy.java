package com.lowdragmc.multiblocked.api.capability.proxy;

import com.lowdragmc.multiblocked.api.capability.MultiblockCapability;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;

import java.util.Objects;

/**
 * The Proxy of a Capability {@link Capability}
 */
public abstract class CapCapabilityProxy<C, K> extends CapabilityProxy<K>{
    public final Capability<? extends C> CAP;

    public CapCapabilityProxy(MultiblockCapability<? super K> capability, BlockEntity tileEntity, Capability<? extends C> cap) {
        super(capability, tileEntity);
        CAP = cap;
    }

    public C getCapability() {
        return super.getCapability(CAP);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CapCapabilityProxy && Objects.equals(getCapability(), ((CapCapabilityProxy<?, ?>) obj).getCapability());
    }
}
