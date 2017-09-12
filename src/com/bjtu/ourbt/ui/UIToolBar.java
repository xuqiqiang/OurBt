package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.bjtu.ourbt.ui.listener.ToolBarListener;
import com.bjtu.ourbt.util.ImageLoader;
import com.bjtu.ourbt.util.NullImageException;

/**
 * 实现工具栏
 */
public class UIToolBar {
    ToolBar toolBar;
    SashForm sashForm;
    public static String ADD_TORRENT_NAEM = "添加 Torrent";
    public static String ADD_TORRENT_URL_NAEM = "从 URL添加种子";
    public static String CREATE_TORRENT_NAEM = "创建新的Torrent";
    public static String REMOVE_NAEM = "移除";
    public static String START_NAEM = "开始";
    public static String PAUSE_NAEM = "暂停";
    public static String STOP_NAEM = "停止";
    public static String MOVE_UP__NAEM = "向上移动";
    public static String MOVE_DOWN__NAEM = "向下移动";
    public static String PERFERENCE_NAEM = "属性";

    public static String LISTENER_OBJECT_NAME = "ToolItem";

    public UIToolBar(SashForm sashForm) {
        this.sashForm = sashForm;

    }

    public void open() {
        createToolBar();
    }

    private void createToolBar() {
        toolBar = new ToolBar(sashForm, SWT.NONE);

        ToolItem addTorrentItem = new ToolItem(toolBar, SWT.PUSH);
        addTorrentItem.setToolTipText(ADD_TORRENT_NAEM);
        try {
            addTorrentItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x00.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        addTorrentItem.addSelectionListener(new ToolBarListener(sashForm
                .getShell(), LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem addTorrentURLItem = new ToolItem(toolBar, SWT.PUSH);
        addTorrentURLItem.setToolTipText(ADD_TORRENT_URL_NAEM);
        try {
            addTorrentURLItem.setImage(new Image(sashForm.getShell()
                    .getDisplay(), ImageLoader.addImage("image/1_00x01.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        addTorrentURLItem.addSelectionListener(new ToolBarListener(sashForm
                .getShell(), LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem createTorrentItem = new ToolItem(toolBar, SWT.PUSH);
        createTorrentItem.setToolTipText(CREATE_TORRENT_NAEM);
        try {
            createTorrentItem.setImage(new Image(sashForm.getShell()
                    .getDisplay(), ImageLoader.addImage("image/1_00x02.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        createTorrentItem.addSelectionListener(new ToolBarListener(sashForm
                .getShell(), LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem removeItem = new ToolItem(toolBar, SWT.PUSH);
        removeItem.setToolTipText(REMOVE_NAEM);
        try {
            removeItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x03.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        removeItem.addSelectionListener(new ToolBarListener(
                sashForm.getShell(), LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem startItem = new ToolItem(toolBar, SWT.PUSH);
        startItem.setToolTipText(START_NAEM);
        try {
            startItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x04.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        startItem.addSelectionListener(new ToolBarListener(sashForm.getShell(),
                LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem pauseItem = new ToolItem(toolBar, SWT.PUSH);
        pauseItem.setToolTipText(PAUSE_NAEM);
        try {
            pauseItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x05.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        pauseItem.addSelectionListener(new ToolBarListener(sashForm.getShell(),
                LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem stopItem = new ToolItem(toolBar, SWT.PUSH);
        stopItem.setToolTipText(STOP_NAEM);
        try {
            stopItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x06.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        stopItem.addSelectionListener(new ToolBarListener(sashForm.getShell(),
                LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem moveUpItem = new ToolItem(toolBar, SWT.PUSH);
        moveUpItem.setToolTipText(MOVE_UP__NAEM);
        try {
            moveUpItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x07.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        moveUpItem.addSelectionListener(new ToolBarListener(
                sashForm.getShell(), LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem moveDownItem = new ToolItem(toolBar, SWT.PUSH);
        moveDownItem.setToolTipText(MOVE_DOWN__NAEM);
        try {
            moveDownItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x08.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        moveDownItem.addSelectionListener(new ToolBarListener(sashForm
                .getShell(), LISTENER_OBJECT_NAME));
        createSeparator();

        ToolItem perferenceItem = new ToolItem(toolBar, SWT.PUSH);
        perferenceItem.setToolTipText(PERFERENCE_NAEM);
        try {
            perferenceItem.setImage(new Image(sashForm.getShell().getDisplay(),
                    ImageLoader.addImage("image/1_00x11.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        perferenceItem.addSelectionListener(new ToolBarListener(sashForm
                .getShell(), LISTENER_OBJECT_NAME));
    }

    /**
     * 内部方法，产生分割
     */
    private void createSeparator() {
        ToolItem separator = new ToolItem(toolBar, SWT.SEPARATOR);
        separator.setWidth(5);
    }

}
