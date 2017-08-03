package com.huishengyuan.storemanage.Tab;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.huishengyuan.storemanage.FootList.FootListFragment;
import com.huishengyuan.storemanage.My.MyFragment;
import com.huishengyuan.storemanage.Order.OrderFragment;
import com.huishengyuan.storemanage.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class TabActivity extends SupportActivity {

    @BindView(R.id.contentView)
    FrameLayout mContentView;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private long m_exitTime = 1;
    private OrderFragment mOrderFragment;
    private FootListFragment mFootListFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        injectPages();
        initBottomBar();
    }

    private void injectPages(){
        mOrderFragment = findFragment(OrderFragment.class);
        if(mOrderFragment==null){
            mOrderFragment = OrderFragment.newInstance();
        }
        mFootListFragment = findFragment(FootListFragment.class);
        if(mFootListFragment==null){
            mFootListFragment = FootListFragment.newInstance();
        }
        mMyFragment = findFragment(MyFragment.class);
        if(mMyFragment==null){
            mMyFragment = MyFragment.newInstance();
        }

        loadMultipleRootFragment(R.id.contentView,0,mOrderFragment,mFootListFragment,mMyFragment);
    }

    public BottomBar getBottomBar() {
        return mBottomBar;
    }

    private void initBottomBar(){
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.Order:
                        mOrderFragment = findFragment(OrderFragment.class);
                        if(mOrderFragment!=null){
                            showHideFragment(mOrderFragment);
                        }
                        break;
                    case R.id.FootList:
                        mFootListFragment = findFragment(FootListFragment.class);
                        if(mFootListFragment!=null){
                            showHideFragment(mFootListFragment);
                        }
                        break;
                    case R.id.My:
                        mMyFragment = findFragment(MyFragment.class);
                        if(mMyFragment!=null){
                            showHideFragment(mMyFragment);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 再按一次退出程序
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - m_exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                m_exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
