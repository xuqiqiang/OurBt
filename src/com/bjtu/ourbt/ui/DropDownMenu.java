package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.gudy.azureus2.core3.download.DownloadManager;
import org.gudy.azureus2.ui.swt.views.utils.ManagerUtils;

import com.bjtu.ourbt.ui.UITorrentTable.ShowTableItem;

/**
 * ��ѡ��TableItem�������˵�
 */
public class DropDownMenu {
    private Shell shell;
    private Menu menu;
    private MenuItem beginItem;
    private MenuItem beginAllItem;
    private MenuItem stopItem;
    private MenuItem stopAllItem;
    private MenuItem pauseItem;
    private MenuItem resumeItem;
    private MenuItem removeItem;
    private MenuItem removeTorrentItem;
    private MenuItem removeAllItem;
    private Menu removeMenu;
    private Table table;
    private DownloadManager manager;

    public DropDownMenu(Shell shell, Table table) {
        this.shell = shell;
        this.table = table;
        init();
    }

    private void init() {
        menu = new Menu(shell, SWT.POP_UP);

        beginItem = new MenuItem(menu, SWT.PUSH);
        beginItem.setText("��ʼ");
        beginItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                manager = (DownloadManager) table.getSelection()[0]
                        .getData("manager");
                ManagerUtils.start(manager);
            }
        });

        beginAllItem = new MenuItem(menu, SWT.PUSH);
        beginAllItem.setText("��ʼ����");
        beginAllItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                ManagerUtils.asyncStartAll();
            }
        });

        stopItem = new MenuItem(menu, SWT.PUSH);
        stopItem.setText("ֹͣ");
        stopItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                manager = (DownloadManager) table.getSelection()[0]
                        .getData("manager");
                ManagerUtils.stop(manager, null);
            }

        });

        stopAllItem = new MenuItem(menu, SWT.PUSH);
        stopAllItem.setText("ֹͣ����");
        stopAllItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ManagerUtils.asyncStopAll();
            }
        });

        pauseItem = new MenuItem(menu, SWT.PUSH);
        pauseItem.setText("��ͣ");
        pauseItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ManagerUtils.asyncPause();
            }
        });

        resumeItem = new MenuItem(menu, SWT.PUSH);
        resumeItem.setText("�ָ�");
        resumeItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ManagerUtils.asyncResume();
            }
        });

        removeItem = new MenuItem(menu, SWT.CASCADE);
        removeItem.setText("ɾ��");

        removeMenu = new Menu(shell, SWT.DROP_DOWN);

        removeTorrentItem = new MenuItem(removeMenu, SWT.PUSH);
        removeTorrentItem.setText("ɾ������");
        removeTorrentItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                manager = (DownloadManager) table.getSelection()[0]
                        .getData("manager");
                ShowTableItem deletdItem = (ShowTableItem) table.getSelection()[0]
                        .getData("showTableItemObject");
                manager.removeListener(deletdItem);
                manager.removePeerListener(deletdItem);
                manager.removePieceListener(deletdItem);
                table.remove(table.getSelectionIndices());
                ManagerUtils.asyncStopDelete(manager,
                        DownloadManager.STATE_STOPPED, true, false, null);

            }
        });

        removeAllItem = new MenuItem(removeMenu, SWT.PUSH);
        removeAllItem.setText("ɾ�����Ӻ��ļ�");
        removeAllItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                manager = (DownloadManager) table.getSelection()[0]
                        .getData("manager");
                ShowTableItem deletdItem = (ShowTableItem) table.getSelection()[0]
                        .getData("showTableItemObject");
                manager.removeListener(deletdItem);
                manager.removePeerListener(deletdItem);
                manager.removePieceListener(deletdItem);
                table.remove(table.getSelectionIndices());
                ManagerUtils.asyncStopDelete(manager,
                        DownloadManager.STATE_STOPPED, true, true, null);

            }
        });
        removeItem.setMenu(removeMenu);
    }

    public Menu getMenu() {
        return this.menu;
    }

}
