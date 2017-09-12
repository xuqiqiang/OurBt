package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.bjtu.ourbt.ui.listener.SelectionButtonListener;
import com.bjtu.ourbt.util.ImageLoader;
import com.bjtu.ourbt.util.NullImageException;

/**
 * 关于对话框
 */
public class AboutDialog extends Dialog {
    private Shell shell;

    public AboutDialog(Shell shell) {
        this(shell, SWT.NONE);
    }

    public AboutDialog(Shell shell, int style) {
        super(shell, style);
    }

    public void open() {
        createContent();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    private void createContent() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM);
        shell.setBounds(450, 180, 300, 350);
        shell.setText("About");

        Label image = new Label(shell, SWT.NONE);
        try {
            image.setImage(new Image(shell.getDisplay(), ImageLoader
                    .addImage("image/ourbt_1.jpg")));
        } catch (NullImageException e) {
            e.printStackTrace();
        }
        image.setBounds(0, 0, 300, 200);

        Label label2 = new Label(shell, SWT.NONE);
        label2.setText("* SnailStudio\n\n" + "* 北京交通大学\n\n"
                + "* https://xuqiqiang.github.io/");
        label2.setBounds(10, 220, 220, 100);

        Button cancel = new Button(shell, SWT.NONE);
        cancel.setText("Cancel");
        cancel.setBounds(230, 270, 50, 20);
        cancel.addListener(SWT.Selection, new SelectionButtonListener(shell));
    }

}
