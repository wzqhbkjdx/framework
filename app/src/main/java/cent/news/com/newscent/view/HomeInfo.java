package cent.news.com.newscent.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class HomeInfo {

    public final int				position;

    public final String	tag;

    public final Class<?>	clss;

    public final Bundle args;

    public Fragment fragment;

    HomeInfo(String _tag, Class<?> _class, Bundle _args, int _position) {
        tag = _tag;
        clss = _class;
        args = _args;
        position = _position;
    }


}
