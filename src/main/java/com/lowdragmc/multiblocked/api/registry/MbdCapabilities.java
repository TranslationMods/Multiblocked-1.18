package com.lowdragmc.multiblocked.api.registry;

import com.google.common.collect.Maps;
import com.lowdragmc.multiblocked.Multiblocked;
import com.lowdragmc.multiblocked.api.block.CustomProperties;
import com.lowdragmc.multiblocked.api.capability.MultiblockCapability;
import com.lowdragmc.multiblocked.api.definition.ComponentDefinition;
import com.lowdragmc.multiblocked.api.definition.PartDefinition;
import com.lowdragmc.multiblocked.common.capability.*;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MbdCapabilities {

    public static final Map<String, MultiblockCapability<?>> CAPABILITY_REGISTRY = Maps.newHashMap();
    public static final List<String> CAPABILITY_ORDER = new ArrayList<>();

    public static void registerCapability(MultiblockCapability<?> capability) {
        CAPABILITY_REGISTRY.put(capability.name, capability);
        CAPABILITY_ORDER.add(capability.name);
    }

    public static void registerCapabilities() {
        registerCapability(FEMultiblockCapability.CAP);
        registerCapability(ItemMultiblockCapability.CAP);
        registerCapability(FluidMultiblockCapability.CAP);
        registerCapability(EntityMultiblockCapability.CAP);
        if (Multiblocked.isMekLoaded()) {
            registerCapability(ChemicalMekanismCapability.CAP_GAS);
            registerCapability(ChemicalMekanismCapability.CAP_SLURRY);
            registerCapability(ChemicalMekanismCapability.CAP_INFUSE);
            registerCapability(ChemicalMekanismCapability.CAP_PIGMENT);
            registerCapability(HeatMekanismCapability.CAP);
        }
        if (Multiblocked.isBotLoaded()) {
            registerCapability(ManaBotaniaCapability.CAP);
        }
        if (Multiblocked.isCreateLoaded()) {
            registerCapability(CreateStressCapacityCapability.CAP);
        }
        if (Multiblocked.isProjectELoaded()) {
            registerCapability(EMCProjectECapability.CAP);
        }
        if (Multiblocked.isNaturesAuraLoaded()) {
            registerCapability(AuraMultiblockCapability.CAP);
        }
        if (Multiblocked.isPneumaticLoaded()) {
            registerCapability(PneumaticPressureCapability.CAP);
        }
    }

    public static MultiblockCapability<?> get(String s) {
        return CAPABILITY_REGISTRY.get(s);
    }

    /**
     * Uses VarInt instead of direct strings in Network to reduce payload.
     */
    public static MultiblockCapability<?> getByIndex(int idx) {
        return CAPABILITY_REGISTRY.get(CAPABILITY_ORDER.get(idx));
    }

    public static int indexOf(MultiblockCapability<?> capability) {
        return CAPABILITY_ORDER.indexOf(capability.name);
    }

    public static void registerAnyCapabilityBlocks() {
        for (MultiblockCapability<?> capability : MbdCapabilities.CAPABILITY_REGISTRY.values()) {
            ComponentDefinition definition = new PartDefinition(new ResourceLocation(Multiblocked.MODID, capability.name + ".any"));
            definition.properties.isOpaque = false;
            definition.properties.tabGroup = null;
            definition.properties.rotationState = CustomProperties.RotationState.NONE;
            definition.properties.showInJei = false;
            MbdComponents.registerComponent(definition);
        }
    }
}
