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
 * ѡ�������
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

        // ���öԻ����ȷ����ť�����¼�
        if (((Button) event.widget).getText().equals("OK")) {

        } else

        // ���öԻ���Ĺرհ�ť�¼�
        if (((Button) event.widget).getText().equals("Cancel")
                || ((Button) event.widget).getText().equals("ȡ��")) {
            shell.close();
        } else

        // ���öԻ����Ӧ�ð�ť�¼�
        if (((Button) event.widget).getText().equals("Apply")) {

        } else

        if (((Button) event.widget).getText().equals("����ļ�")) {
            addFile();
        } else if (((Button) event.widget).getText().equals("���Ŀ¼")) {
            addDirectory();
        }
    }

    /**
     * �����ļ�
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
     * ����Ŀ¼
     */
    private void addDirectory() {
        DirectoryDialog directoryDialog = new DirectoryDialog(shell, SWT.NONE);
        directoryDialog.setText("Select Directory");
        directoryDialog.setMessage("Select a directory");
        directoryDialog.open();
        combo.setText(directoryDialog.getFilterPath());
    }

}
