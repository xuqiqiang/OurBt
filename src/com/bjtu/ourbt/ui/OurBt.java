package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.bjtu.ourbt.util.ImageLoader;
import com.bjtu.ourbt.util.NullImageException;

/**
 * 主类，其它类都是在这创建的
 */
public class OurBt {
    private Shell shell;
    private static UITorrentTable uiTorrentTable;

    public OurBt() {

    }

    public void open() {
        final Display display = Display.getDefault();
        shell = new Shell(display);
        shell.setText("OurBT");
        try {
            shell.setImage(new Image(display, ImageLoader
                    .addImage("image/ourbt_1.jpg")));
        } catch (NullImageException e1) {
            e1.printStackTrace();
        }
        shell.setLayout(new FillLayout());

        // 创建系统托盘
        UITray uiTray = new UITray(shell);
        uiTray.open();

        // 创建菜单目录
        UIMenu uiMenu = new UIMenu(shell);
        uiMenu.open();

        SashForm sashForm = new SashForm(shell, SWT.VERTICAL | SWT.NONE);

        // 创建工具栏
        UIToolBar uiToolBar = new UIToolBar(sashForm);
        uiToolBar.open();

        SashForm childSashForm = new SashForm(sashForm, SWT.VERTICAL);
        SashForm childchildSashForm = new SashForm(childSashForm, SWT.NONE);

        // 创建状态栏
        StatusBar statusBar = new StatusBar(childSashForm);
        statusBar.open();
        sashForm.setWeights(new int[] { 5, 100 });
        sashForm.setLayout(new FillLayout());

        // 创建左侧目录树
        UICategory uiCategory = new UICategory(childchildSashForm);
        uiCategory.open();

        // 创建表格
        uiTorrentTable = new UITorrentTable(childchildSashForm);
        childSashForm.setWeights(new int[] { 110, 4 });
        childSashForm.setLayout(new FillLayout());
        childchildSashForm.setWeights(new int[] { 10, 90 });
        childchildSashForm.setLayout(new FillLayout());

        shell.setMaximized(true);
        shell.open();
        shell.addShellListener(new ShellAdapter() {

            public void shellClosed(ShellEvent e) {
                Shell[] shells = display.getShells();
                for (int i = 0; i < shells.length; i++) {
                    if (shells[i] != shell)
                        shells[i].close();
                }
            }
        });
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    /**
     * @return 返回表格对象
     */
    public static UITorrentTable getUITorrentTable() {
        return uiTorrentTable;
    }

}
