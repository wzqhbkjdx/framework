package cent.news.com.newscent.view.pulldown;

import android.content.Context;
import android.view.View;

import cent.news.com.newscent.news.NCFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class PullManage {

    /**
     * 初始化
     *
     * @param context
     *            上下文
     * @param hnaFrameLayout
     *            View
     * @param iPull
     *            回调接口
     */
    public void init(Context context, final NCFrameLayout hnaFrameLayout, final IPull iPull) {
        // final StoreHouseHeader header = new StoreHouseHeader(context);
        // header.setPadding(0, (int) SKYAppUtil.convertDpToPixel(5, context),
        // 0, 0);
        // header.setDropHeight((int) SKYAppUtil.convertDpToPixel(50, context));
        // header.initWithString(context.getString(R.string.app_name_e));
        // header.setTextColor(ContextCompat.getColor(context, R.color.font));

        NCDefaultHeader ncDefaultHeader = new NCDefaultHeader(context);
        ncDefaultHeader.setHeaderState(new IHeaderState() {

            @Override public void onStart() {
                hnaFrameLayout.setTouch(true);
            }

            @Override public void onComplete() {
                hnaFrameLayout.setTouch(false);
            }
        });
        hnaFrameLayout.disableWhenHorizontalMove(true);

        hnaFrameLayout.setKeepHeaderWhenRefresh(true);
        hnaFrameLayout.setDurationToCloseHeader(1000);
        hnaFrameLayout.setHeaderView(ncDefaultHeader);
        hnaFrameLayout.setResistance(1.7f);
        hnaFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        hnaFrameLayout.addPtrUIHandler(ncDefaultHeader);

        hnaFrameLayout.setPtrHandler(new NCHandler() {

            @Override public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return NCHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override public void onRefreshBegin(final PtrFrameLayout frame) {
                if (iPull != null) {
                    iPull.onRefresh();
                }
            }
        });
    }

    /**
     * 自动刷新
     *
     * @param hnaFrameLayout
     *            参数
     */
    public void autoRefresh(final NCFrameLayout hnaFrameLayout) {
        if (hnaFrameLayout != null) {
            hnaFrameLayout.postDelayed(new Runnable() {

                @Override public void run() {
                    hnaFrameLayout.autoRefresh(false);
                }
            }, 100);
        }
    }

}
