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
 * ��ʾ����ʱ��ʵʱ��Ϣ��ֻ�н��湦���д�����
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
        generalItem.setText("����");
        generalItem.setToolTipText("��ʾ������Ϣ");
        try {
            generalItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x00.bmp")));
        } catch (NullImageException e6) {
            e6.printStackTrace();
        }

        TabItem trackerItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        trackerItem.setText("�ŷ�");
        trackerItem.setToolTipText("�ŷ�");
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
        peerItem.setText("�û�");
        peerItem.setToolTipText("��ʾ�û���ϸ��Ϣ");
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
        pieceItem.setText("����");
        pieceItem.setToolTipText("��ʾ������Ϣ");
        try {
            pieceItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x03.bmp")));
        } catch (NullImageException e3) {
            e3.printStackTrace();
        }

        TabItem fileItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        fileItem.setText("�ļ�");
        fileItem.setToolTipText("��ʾ�ļ�");
        try {
            fileItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x04.bmp")));
        } catch (NullImageException e2) {
            e2.printStackTrace();
        }

        TabItem speedItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        speedItem.setText("�ٶ�");
        speedItem.setToolTipText("��ʾ�ٶ�");

        try {
            speedItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x05.bmp")));
        } catch (NullImageException e1) {
            e1.printStackTrace();
        }

        TabItem loggerItem = new TabItem(tabFolder, SWT.BORDER | SWT.CLOSE);
        loggerItem.setText("��־");
        loggerItem.setToolTipText("��ʾ��־");
        try {
            loggerItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/tab_00x06.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }

    }
}
