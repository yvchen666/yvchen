package com.nszx.bbs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nszx.bbs.bean.MyUser;
import com.nszx.bbs.bean.Post;
import com.nszx.bbs.bean.UserBean;
import com.nszx.bbs.ui.GridViewActivity;
import com.nszx.bbs.ui.LoginActivity;
import com.nszx.bbs.ui.PersonalActivity;
import com.nszx.bbs.ui.TopicActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private long first_time;
    private ImageView img_icon;//侧拉菜单栏头像
    private BottomNavigationView bottomNavigationView;
    private String TAG = "Main";
    private ProgressDialog dialog;//加载中对话框
    private ProgressDialog dialog2;
    private FloatingActionButton fab;//发帖按钮
    private AlertDialog dialog1;//对话框
    private EditText title, message;
    private Button qx, tj;
    private TextView ft;


    private TopicActivity topicActivity;
    private GridViewActivity gridViewActivity;
    private PersonalActivity personalActivity;
    private Fragment[] fragments;

    private int lastfragment;//用于记录上个选择的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    , WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "c81784a1d4fa4c25abcf3af17e877cde");//初始化bmob
//透明化设置

        //申请读取手机状态权限
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


//        初始化权限申请
        permission();
//        初始化fragment
        initFragment();
//        初始化控件
        initView();

    }

    private void initFragment() {
        topicActivity = new TopicActivity();
        gridViewActivity = new GridViewActivity();
        personalActivity = new PersonalActivity();
        fragments = new Fragment[]{topicActivity, gridViewActivity, personalActivity};

        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view, topicActivity).show(topicActivity).commit();
        bottomNavigationView = findViewById(R.id.bnv);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }

    private void initView() {
    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;
                    }
                    return true;
                }
                case R.id.navigation_dashboard: {
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;
                    }
                    return true;
                }
                case R.id.navigation_notifications: {
                    if (lastfragment != 2) {
                        switchFragment(lastfragment, 2);
                        lastfragment = 2;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    //切换Fragment
    private void switchFragment(int i, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[i]);//隐藏上个Fragment
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.main_view, fragments[index]);


        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }

    public void permission() {
        AndPermission.with(MainActivity.this)
                .runtime()
                .permission(Permission.READ_PHONE_STATE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                    }
                }).onDenied(new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                Toasty.error(MainActivity.this, "请允许软件运行所需权限").show();
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mFtDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (BmobUser.isLogin()) {
                UserBean user = BmobUser.getCurrentUser(UserBean.class);
                Log.d(TAG, "已登陆");
            } else {
                Toasty.warning(MainActivity.this,"您还未登录，请先登录").show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {
            BmobUser.logOut();
            Toasty.warning(MainActivity.this,"登出").show();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void mFtDialog() {
        LayoutInflater lf = LayoutInflater.from(this);
        View v = lf.inflate(R.layout.newtie, null);
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setView(v);
        dialog1 = ad.show();

        ft = v.findViewById(R.id.ftdialogTextView1);
        title = v.findViewById(R.id.ftdialogEditText1);
        message = v.findViewById(R.id.ftdialogEditText2);
        qx = v.findViewById(R.id.ftdialogButton1);
        tj = v.findViewById(R.id.ftdialogButton2);

        SharedPreferences settings = this.getSharedPreferences("INFO", 0);
        boolean THEME = settings.getBoolean("THEME", true);

        //判断配置文件是否存在
        File f = new File("/data/data/com.duhuang.jsbbs/shared_prefs/INFO.xml");
        if (f.exists()) {
            //存在
        } else {
            //不存在
            settings.edit().putBoolean("THEME", true).commit();
        }

        if (THEME == true) {
            setTheme(R.style.AppTheme);
            ft.setTextColor(Color.BLACK);
        } else {
            setTheme(R.style.NightAppTheme);
        }

        qx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                // TODO: Implement this method
                dialog1.dismiss();
            }
        });

        tj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                // TODO: Implement this method
                String nr = title.getText().toString().trim();
                String nr2 = message.getText().toString().trim();
                if (nr.isEmpty() || nr2.isEmpty()) {
                    Toasty.error(MainActivity.this, "发帖标题或内容不能为空！");
                } else {
                    dialog2 = new ProgressDialog(MainActivity.this);
                    dialog2.setTitle("提示：");
                    dialog2.setCancelable(false);
                    dialog2.setProgressStyle(dialog.STYLE_SPINNER);
                    dialog2.setMessage("发帖中...");
                    dialog2.show();

                    MyUser bu = BmobUser.getCurrentUser(MyUser.class);

                    Post XianLiao = new Post();
                    XianLiao.setTitle(nr);
                    XianLiao.setMessage(nr2);
                    XianLiao.setAuthors(bu);
                    XianLiao.setBk("AIDE");
                    XianLiao.setSh(false);
                    XianLiao.setRenzheng(false);
                    XianLiao.save(new SaveListener<String>() {
                        @Override
                        public void done(String p1, BmobException p2) {
                            if (p2 == null) {
                                Toasty.success(getApplicationContext(), "您刚发的帖子需要审核，审核成功后才会显示！").show();
                                dialog2.dismiss();
                                dialog1.dismiss();
                            } else {
                                Toasty.error(getApplicationContext(), "发帖失败，请重试！").show();
                                dialog2.dismiss();
                                dialog1.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onKeyUp(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            long second_time = System.currentTimeMillis();
            if (second_time - first_time > 2000) {
                Toasty.warning(MainActivity.this, "再按一次退出程序").show();
                first_time = second_time;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyUp(keycode, event);
    }


}
