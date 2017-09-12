package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.bjtu.ourbt.util.ImageLoader;
import com.bjtu.ourbt.util.NullImageException;

/**
 * 显示下载时的实时信息，只有界面功能有待完善
 */
public class UITorrentInfo {
    private SashForm sashForm;

    public UITorrentInfo(SashForm sashForm) {
        this.sashForm = sashForm;
    }

    public void open() {
        create();
    }

    private void create() {
        TabFolder tabFolder = new TabFolder(sashForm, SWT.NONE);

        TabItem generalItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        generalItem.setText("常规");
        generalItem.setToolTipText("显示常规信息");
        try {
            generalItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x00.bmp")));
        } catch (NullImageException e6) {
            e6.printStackTrace();
        }

        TabItem trackerItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        trackerItem.setText("伺服");
        trackerItem.setToolTipText("伺服");
        try {
            trackerItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x01.bmp")));
        } catch (NullImageException e5) {
            e5.printStackTrace();
        }
        Composite trackComposite = new Composite(tabFolder, SWT.NONE);
        trackComposite.setLayout(new FillLayout());
        TrackTable trackTable = new TrackTable(trackComposite);
        trackTable.open();
        trackerItem.setControl(trackComposite);

        TabItem peerItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        peerItem.setText("用户");
        peerItem.setToolTipText("显示用户详细信息");
        try {
            peerItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x02.bmp")));
        } catch (NullImageException e4) {
            // TODO Auto-generated catch block
            e4.printStackTrace();
        }
        Composite peerComposite = new Composite(tabFolder, SWT.NONE);
        peerComposite.setLayout(new FillLayout());
        PeerTable peerTable = new PeerTable(peerComposite);
        peerTable.open();
        peerItem.setControl(peerComposite);

        TabItem pieceItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        pieceItem.setText("区块");
        pieceItem.setToolTipText("显示区块信息");
        try {
            pieceItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x03.bmp")));
        } catch (NullImageException e3) {
            e3.printStackTrace();
        }

        TabItem fileItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        fileItem.setText("文件");
        fileItem.setToolTipText("显示文件");
        try {
            fileItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x04.bmp")));
        } catch (NullImageException e2) {
            e2.printStackTrace();
        }

        TabItem speedItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        speedItem.setText("速度");
        speedItem.setToolTipText("显示速度");

        try {
            speedItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x05.bmp")));
        } catch (NullImageException e1) {
            e1.printStackTrace();
        }

        TabItem loggerItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        loggerItem.setText("日志");
        loggerItem.setToolTipText("显示日志");
        try {
            loggerItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x06.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }

    }
}
