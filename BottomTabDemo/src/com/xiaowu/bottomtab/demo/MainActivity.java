package com.xiaowu.bottomtab.demo;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 主Activity
 * 
 * @author wwj_748
 * 
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

	// 三个tab布局
	private RelativeLayout knowLayout, iWantKnowLayout, meLayout;

	// 底部标签切换的Fragment
	private Fragment knowFragment, iWantKnowFragment, meFragment,
			currentFragment;
	// 底部标签图片
	private ImageView knowImg, iWantKnowImg, meImg;
	// 底部标签的文本
	private TextView knowTv, iWantKnowTv, meTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();
		clickTab(1);
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		knowLayout = (RelativeLayout) findViewById(R.id.rl_know);
		iWantKnowLayout = (RelativeLayout) findViewById(R.id.rl_want_know);
		meLayout = (RelativeLayout) findViewById(R.id.rl_me);
		knowLayout.setOnClickListener(this);
		iWantKnowLayout.setOnClickListener(this);
		meLayout.setOnClickListener(this);

		knowImg = (ImageView) findViewById(R.id.iv_know);
		iWantKnowImg = (ImageView) findViewById(R.id.iv_i_want_know);
		meImg = (ImageView) findViewById(R.id.iv_me);
		knowTv = (TextView) findViewById(R.id.tv_know);
		iWantKnowTv = (TextView) findViewById(R.id.tv_i_want_know);
		meTv = (TextView) findViewById(R.id.tv_me);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.rl_know: // 知道
			clickTab(1);
			break;
		case R.id.rl_want_know: // 我想知道
			clickTab(2);
			break;
		case R.id.rl_me: // 我的
			clickTab(3);
			break;
		default:
			break;
		}
	}
	
	private void clickTab(int chose){
		knowImg.setImageResource(R.drawable.btn_know_nor);
		knowTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
		iWantKnowTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		meImg.setImageResource(R.drawable.btn_my_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		
		switch (chose) {
		case 1:
			if (knowFragment == null) {
				knowFragment = new ZhidaoFragment();
			}
			addOrShowFragment(getSupportFragmentManager().beginTransaction(), knowFragment);
			knowImg.setImageResource(R.drawable.btn_know_pre);
			knowTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
			break;
		case 2:
			if (iWantKnowFragment == null) {
				iWantKnowFragment = new IWantKnowFragment();
			}
			addOrShowFragment(getSupportFragmentManager().beginTransaction(), iWantKnowFragment);
			iWantKnowImg.setImageResource(R.drawable.btn_wantknow_pre);
			iWantKnowTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
			break;
		case 3:
			if (meFragment == null) {
				meFragment = new MeFragment();
			}
			addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);
			meImg.setImageResource(R.drawable.btn_my_pre);
			meTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
			break;

		default:
			break;
		}
	}

	

	/**
	 * 添加或者显示碎片
	 * 
	 * @param transaction
	 * @param fragment
	 */
	private void addOrShowFragment(FragmentTransaction transaction,Fragment fragment) {
		if(currentFragment==null) {
			currentFragment=fragment;// 记录当前Fragment
			getSupportFragmentManager().beginTransaction().add(R.id.content_layout, fragment).commit();
		}
		
		if (currentFragment == fragment)
			return;
		
		if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
			transaction.hide(currentFragment).add(R.id.content_layout, fragment).commit();
		} else {
			transaction.hide(currentFragment).show(fragment).commit();
		}
		currentFragment = fragment;

	}

}
