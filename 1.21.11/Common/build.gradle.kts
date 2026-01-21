plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-common")
}

dependencies {
    modCompileOnlyApi(libs.puzzleslib.common)
}

multiloader {
    mixins {
        mixin("EnchantmentHelperMixin", "GrindstoneMenu\$SlotMixin", "GrindstoneMenuMixin", "ItemStackMixin")
    }
}
