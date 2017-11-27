package test.xc.com.toolbartest;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public abstract class BaseToolbarActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    Toolbar mToolbar;
    TextView mTitleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getContentViewLayoutID() != 0){
            setContentView(getContentViewLayoutID());
            initToolbar();
        }
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mTitleName = findViewById(R.id.toolbar_center_title);
        if (mToolbar != null){
            mToolbar.setTitle("");
            mTitleName.setText(getSubTitle());
            setSupportActionBar(mToolbar);
            if (isShowBack()){
                showBack();
            }
            if(getMenu() != 0) {
                mToolbar.inflateMenu(getMenu());
            }
        }
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack(){
//      setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
//      mToolbar.setNavigationIcon(R.mipmap.icon_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onNavigationBtnClicked();
            }
        });
        mToolbar.setOnMenuItemClickListener(this);
    }

    /**
     * 右边菜单点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(getMenu() != 0) {
            getMenuInflater().inflate(getMenu(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 设置标题文本
     */
    public abstract String getSubTitle();

    /**
     * 右边导航菜单栏
     * @return
     */
    public int getMenu() {
        return 0;
    }

    public void onNavigationBtnClicked() {
        finish();
    }


    /**
     * 是否有返回按钮
     * @return
     */
    protected boolean isShowBack(){
        return true;
    }

}
