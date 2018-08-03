package cent.news.com.newscent;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 为了防止Glide报错
 * java.lang.AbstractMethodError: abstract method "void com.bumptech.glide.module.RegistersComponents.registerComponents(android.content.Context, com.bumptech.glide.Glide, com.bumptech.glide.Registry)"
 */

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}
