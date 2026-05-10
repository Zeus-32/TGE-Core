package eu.zunix.tge_core.datagen.provider;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class TGEOreLangProvider extends LanguageProvider {
    public TGEOreLangProvider(PackOutput output) {
        super(output, TGECore.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("tooltip.tge_core.formula", "\u00A7e%s");

        for (OreMaterial material : OreMaterial.values()) {
            String display = material.displayName();
            add("block.tge_core." + material.oreId(), display + " Ore");
            add("block.tge_core." + material.deepslateOreId(), "Deepslate " + display + " Ore");
        }
    }
}
