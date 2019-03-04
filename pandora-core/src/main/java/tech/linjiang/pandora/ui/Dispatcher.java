package tech.linjiang.pandora.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import tech.linjiang.pandora.core.R;
import tech.linjiang.pandora.inspector.BaseLineView;
import tech.linjiang.pandora.ui.connector.Type;
import tech.linjiang.pandora.ui.connector.UIStateCallback;
import tech.linjiang.pandora.ui.fragment.ConfigFragment;
import tech.linjiang.pandora.ui.fragment.CrashFragment;
import tech.linjiang.pandora.ui.fragment.HierarchyFragment;
import tech.linjiang.pandora.ui.fragment.HistoryFragment;
import tech.linjiang.pandora.ui.fragment.NetFragment;
import tech.linjiang.pandora.ui.fragment.RouteFragment;
import tech.linjiang.pandora.ui.fragment.SandboxFragment;
import tech.linjiang.pandora.ui.fragment.ViewFragment;
import tech.linjiang.pandora.util.ViewKnife;

/**
 * Created by linjiang on 30/05/2018.
 */

public class Dispatcher extends AppCompatActivity implements UIStateCallback {

    public static final String PARAM1 = "param1";

    public static void start(Context context, @Type int type) {
        boolean needTrans = type == Type.BASELINE || type == Type.SELECT;
        Intent intent = new Intent(context, needTrans ? TransActivity.class : Dispatcher.class)
                .putExtra(PARAM1, type);
        // This flag is very ridiculous in different android versions, like a bug
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private @Type
    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra(PARAM1, Type.FILE);
        ViewKnife.setStatusBarColor(getWindow(), Color.TRANSPARENT);
        ViewKnife.transStatusBar(getWindow());
        dispatch(savedInstanceState);
    }

    private void dispatch(Bundle savedInstanceState) {
        View view;
        switch (type) {
            case Type.BASELINE:
                view = new BaseLineView(this);
                setContentView(view);
                break;
            case Type.SELECT:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new ViewFragment())
                            .commit();
                } else {
                    finish();
                }
                break;
            case Type.NET:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new NetFragment())
                            .commit();
                }
                break;
            case Type.FILE:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new SandboxFragment())
                            .commit();
                }
                break;
            case Type.BUG:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new CrashFragment())
                            .commit();
                }
                break;
            case Type.HISTORY:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new HistoryFragment())
                            .commit();
                }
                break;
            case Type.CONFIG:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new ConfigFragment())
                            .commit();
                }
                break;
            case Type.HIERARCHY:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new HierarchyFragment())
                            .commit();
                } else {
                    finish();
                }
                break;
            case Type.ROUTE:
                view = new FrameLayout(this);
                view.setId(R.id.pd_fragment_container_id);
                setContentView(view);
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.pd_fragment_container_id, new RouteFragment())
                            .commit();
                }
                break;
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private View hintView;

    @Override
    public void showHint() {
        if (hintView == null) {
            hintView = new ProgressBar(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            hintView.setLayoutParams(params);
        }
        if (hintView.getParent() == null) {
            if (getWindow() != null) {
                if (getWindow().getDecorView() instanceof ViewGroup) {
                    ((ViewGroup) getWindow().getDecorView()).addView(hintView);
                }
            }
        }
        if (hintView.getVisibility() == View.GONE) {
            hintView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideHint() {
        if (hintView != null) {
            if (hintView.getVisibility() != View.GONE) {
                hintView.setVisibility(View.GONE);
            }
        }
    }
}
