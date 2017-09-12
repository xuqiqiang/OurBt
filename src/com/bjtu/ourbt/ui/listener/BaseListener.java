package com.bjtu.ourbt.ui.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;

import com.bjtu.ourbt.ui.AddTorrentDefaultDialog;
import com.bjtu.ourbt.ui.CreateTorrentDialog;
import com.bjtu.ourbt.ui.PerferenceDialog;
import com.bjtu.ourbt.ui.UIMenu;
import com.bjtu.ourbt.ui.UIToolBar;
import com.bjtu.ourbt.util.UICharacter;

/**
 * 基本监听器，界面的监听都在这实现，是父类，把界面中共用的监听放在这
 */
public class BaseListener implements SelectionListener {
    public Shell shell;
    private String type;
    private String text;

    public BaseListener(Shell shell, String type) {
        this.shell = shell;
        this.type = type;
    }

    public void widgetDefaultSelected(SelectionEvent e) {

    }

    public void widgetSelected(SelectionEvent e) {
        addBaseListener(e);
    }

    protected void addBaseListener(SelectionEvent e) {
        if (type.equals(UIToolBar.LISTENER_OBJECT_NAME)) {
            text = ((ToolItem) e.getSource()).getToolTipText();
        } else if (type.equals(UIMenu.LISTEN_OBJECT_NAME)) {
            text = ((MenuItem) e.getSource()).getText();
        }

        // 增加种子按钮监听
        if (text.equals(UIToolBar.ADD_TORRENT_NAEM)
                || text.equals(UICharacter.ADD_TORRENT_DEFAULT)) {
            AddTorrentDefaultDialog addDefaultDialog = new AddTorrentDefaultDialog(
                    shell);
            addDefaultDialog.open();

            // 从URL创建种子监听
        } else if (text.equals(UIToolBar.ADD_TORRENT_URL_NAEM)
                || text.equals(UICharacter.ADD_TORRENT_URL)) {

            // 创建种子监听
        } else if (text.equals(UIToolBar.CREATE_TORRENT_NAEM)
                || text.equals(UICharacter.CREATE_NEW_TORRENT)) {
            CreateTorrentDialog createTorrentDialog = new CreateTorrentDialog(
                    shell);
            createTorrentDialog.open();
            // 属性按钮监听
        } else if (text.equals(UIToolBar.PERFERENCE_NAEM)
                || text.equals(UICharacter.PERFERENCE)) {
            PerferenceDialog perferenceDialog = new PerferenceDialog(shell);
            perferenceDialog.open();
        }
    }
}
