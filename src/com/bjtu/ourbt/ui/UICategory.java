package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.bjtu.ourbt.util.ImageLoader;
import com.bjtu.ourbt.util.NullImageException;

/**
 * Ŀ¼��
 */
public class UICategory {
    private SashForm sashForm;

    public UICategory(SashForm sashForm) {
        this.sashForm = sashForm;

    }

    public void open() {
        createCategory();
    }

    private void createCategory() {
        Tree tree = new Tree(sashForm, SWT.BORDER | SWT.SINGLE);
        tree.setLinesVisible(false);
        TreeItem allItem = new TreeItem(tree, SWT.BORDER);
        allItem.setText("ȫ��");
        try {
            allItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/status_00x00.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }

        TreeItem downloadItem = new TreeItem(tree, SWT.BORDER);
        downloadItem.setText("����");
        try {
            downloadItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/status_00x01.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }

        TreeItem completeItem = new TreeItem(tree, SWT.BORDER);
        completeItem.setText("���");
        try {
            completeItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/status_00x02.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }

        TreeItem activeItem = new TreeItem(tree, SWT.BORDER);
        activeItem.setText("�");
        try {
            activeItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/status_00x03.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }

        TreeItem inActiveItem = new TreeItem(tree, SWT.BORDER);
        inActiveItem.setText("ֹͣ");
        try {
            inActiveItem.setImage(new Image(sashForm.getDisplay(), ImageLoader
                    .addImage("image/status_00x04.bmp")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
    }

}
