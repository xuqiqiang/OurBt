package com.bjtu.ourbt.ui.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * 选择监听器
 */
public class SelectionButtonListener implements Listener {
    private Shell shell;
    private Combo combo;

    public SelectionButtonListener(Shell shell, Combo combo) {
        this.shell = shell;
        this.combo = combo;
    }

    public SelectionButtonListener(Shell shell) {
        this.shell = shell;
    }

    public void handleEvent(Event event) {

        // 设置对话框的确定按钮监听事件
        if (((Button) event.widget).getText().equals("OK")) {

        } else

        // 设置对话框的关闭按钮事件
        if (((Button) event.widget).getText().equals("Cancel")
                || ((Button) event.widget).getText().equals("取消")) {
            shell.close();
        } else

        // 设置对话框的应用按钮事件
        if (((Button) event.widget).getText().equals("Apply")) {

        } else

        if (((Button) event.widget).getText().equals("添加文件")) {
            addFile();
        } else if (((Button) event.widget).getText().equals("添加目录")) {
            addDirectory();
        }
    }

    /**
     * 增加文件
     */
    private void addFile() {
        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN | SWT.MULTI);
        fileDialog.setText("Select Files");
        fileDialog.setFilterNames(new String[] { "All (*.*)" });
        fileDialog.setFilterExtensions(new String[] { "*.*" });
        fileDialog.open();
        String[] fileNames = fileDialog.getFileNames();
        String names = "";
        for (int i = 0; i < fileNames.length; i++) {
            names = names + fileDialog.getFilterPath() + "\\" + fileNames[i]
                    + "  ";
        }
        combo.setText(names);
    }

    /**
     * 增加目录
     */
    private void addDirectory() {
        DirectoryDialog directoryDialog = new DirectoryDialog(shell, SWT.NONE);
        directoryDialog.setText("Select Directory");
        directoryDialog.setMessage("Select a directory");
        directoryDialog.open();
        combo.setText(directoryDialog.getFilterPath());
    }

}
