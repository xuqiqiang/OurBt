package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import com.aelitis.azureus.core.AzureusCoreFactory;
import com.bjtu.ourbt.util.ImageLoader;
import com.bjtu.ourbt.util.NullImageException;

/**
 * 系统托盘类，实现托盘
 */
public class UITray {
    private Shell shell;
    private TrayItem trayItem;
    private boolean isAnotherClick = false;

    public UITray(Shell shell) {
        this.shell = shell;

    }

    public void open() {
        createTray();
    }

    private void createTray() {
        final ToolTip tip = new ToolTip(shell, SWT.BALLOON
                | SWT.ICON_INFORMATION);
        final Tray tray = shell.getDisplay().getSystemTray();
        if (tray != null) {
            trayItem = new TrayItem(tray, SWT.NONE);
            trayItem.setToolTipText(getTrayMessage());
            try {
                trayItem.setImage(new Image(shell.getDisplay(), ImageLoader
                        .addImage("image/ourbt_1.jpg")));
            } catch (NullImageException e1) {
                e1.printStackTrace();
            }
            trayItem.setToolTip(tip);

            final Menu menu = new Menu(shell, SWT.POP_UP);
            final MenuItem showMenu = new MenuItem(menu, SWT.PUSH);
            showMenu.setText("S&how");
            showMenu.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    boolean showFlag = shell.isVisible();
                    shell.setVisible(showFlag);
                    showMenu.setText(showFlag ? "&Show" : "&Hide");
                    tip.setText("QuickBt托盘");
                    tip.setMessage("右键单击");
                    tip.setVisible(true);
                }
            });

            MenuItem exitItem = new MenuItem(menu, SWT.PUSH);
            exitItem.setText("E&xit");
            exitItem.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    AzureusCoreFactory.getSingleton().stop();
                    trayItem.dispose();
                    shell.dispose();
                    System.exit(0);
                }
            });

            trayItem.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event e) {
                    if (!isAnotherClick) {
                        shell.setVisible(true);
                        isAnotherClick = true;
                    } else {
                        shell.setVisible(false);
                        isAnotherClick = false;
                    }
                }
            });

            trayItem.addListener(SWT.MenuDetect, new Listener() {
                public void handleEvent(Event e) {
                    menu.setVisible(true);
                }
            });

            shell.addShellListener(new ShellAdapter() {
                public void shellClosed(ShellEvent e) {
                    e.doit = false;
                    shell.setVisible(false);
                    showMenu.setText("S&how");
                    tip.setText("QuickBt");
                    tip.setMessage(getTrayMessage());
                    tip.setVisible(true);

                }
            });
        }
    }

    /**
     * 
     * @return 返回托盘对象
     */
    public TrayItem getTrayItem() {
        return this.trayItem;
    }

    /**
     * 内部方法 返回托盘显示的信息
     * 
     * @return
     */
    private String getTrayMessage() {
        return "QuickBt1.0.0\n0(0)downing,0(0)seeding\n"
                + "0.0Kb/s down ,0.0Kb/s up";
    }
}
