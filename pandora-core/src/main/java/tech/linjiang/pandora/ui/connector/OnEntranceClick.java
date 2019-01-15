package tech.linjiang.pandora.ui.connector;

import android.view.View;

import tech.linjiang.pandora.core.R;
import tech.linjiang.pandora.ui.Dispatcher;
import tech.linjiang.pandora.util.Utils;

/**
 * Created by linjiang on 30/05/2018.
 */

public class OnEntranceClick implements View.OnClickListener {
    @Override
    public final void onClick(View v) {
        if (v.getId() == R.id.entrance_network) {
            onClick(Type.NET);
        } else if (v.getId() == R.id.entrance_sandbox) {
            onClick(Type.FILE);
        } else if (v.getId() == R.id.ui_hierarchy) {
            onClick(Type.HIERARCHY);
        } else if (v.getId() == R.id.ui_select) {
            onClick(Type.SELECT);
        } else if (v.getId() == R.id.entrance_config) {
            onClick(Type.CONFIG);
        } else if (v.getId() == R.id.ui_baseline) {
            onClick(Type.BASELINE);
        } else if (v.getId() == R.id.ui_grid) {
            onClick(Type.GRID);
        } else if (v.getId() == R.id.ui_window) {
            onClick(Type.WINDOW);
        } else if (v.getId() == R.id.ui_route) {
            onClick(Type.ROUTE);
        }
    }


    protected void onClick(@Type int type) {
        Dispatcher.start(Utils.getContext(), type);
    }

}
