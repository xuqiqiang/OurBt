package com.bjtu.ourbt.ui.listener;

import java.io.IOException;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.aelitis.azureus.core.AzureusCoreFactory;
import com.bjtu.ourbt.ui.AboutDialog;
import com.bjtu.ourbt.util.UICharacter;

/**
 * �˵����������̳�BaseListener
 */
public class MenuBarListener extends BaseListener {

    public MenuBarListener(Shell shell, String type) {
        super(shell, type);
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        addBaseListener(e);
        addSelfListener(e);
    }

    private void addSelfListener(SelectionEvent e) {

        // �ӷ�Ĭ��·���������
        if (((MenuItem) e.getSource()).getText().equals(
                UICharacter.ADD_TORRENT_NOT_DEFAULT)) {

        }

        // ϵͳ�˳�
        if (((MenuItem) e.getSource()).getText().equals(UICharacter.EXIT)) {
            shell.getDisplay().getSystemTray().getItems()[0].dispose();
            AzureusCoreFactory.getSingleton().stop();
            System.exit(0);
        }

        // �Ƿ���ʾ������
        if (((MenuItem) e.getSource()).getText().equals(
                UICharacter.SHOW_TOOL_BAR)) {
            System.out.println("ok");
        }

        // ��ʾ������Ϣ
        if (((MenuItem) e.getSource()).getText().equals(
                UICharacter.SHOW_DETAIL_INFO)) {
            System.out.println("ok");
        }

        // ��ʾ������
        if (((MenuItem) e.getSource()).getText().equals(
                UICharacter.SHOW_STATUS_BAR)) {
            System.out.println("ok");
        }

        // ��ʾ���������״�ṹ
        if (((MenuItem) e.getSource()).getText().equals(
                UICharacter.SHOW_CATEGORY_LIST)) {
            System.out.println("ok");
        }

        // �����ĵ�
        if (((MenuItem) e.getSource()).getText().equals(UICharacter.HELP)) {
            try {
                Runtime.getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler https://xuqiqiang.github.io/");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        // ����
        if (((MenuItem) e.getSource()).getText().equals(UICharacter.ABOUT)) {
            AboutDialog aboutDialog = new AboutDialog(shell);
            aboutDialog.open();
        }
    }

}
