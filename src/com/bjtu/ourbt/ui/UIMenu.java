package com.bjtu.ourbt.ui;

import com.bjtu.ourbt.ui.listener.MenuBarListener;
import com.bjtu.ourbt.util.UICharacter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * 菜单
 */
public class UIMenu {
    private Shell shell;
    private MenuItem fileItem;
    private MenuItem optionItem;
    private MenuItem helpItem;

    public static String LISTEN_OBJECT_NAME = "MenuItem";

    public UIMenu(Shell shell) {
        this.shell = shell;
    }

    public void open() {
        createMenu();
        createDropMenu();

    }

    private void createMenu() {
        Menu mainMenu = new Menu(shell, SWT.BAR);
        shell.setMenuBar(mainMenu);
        fileItem = new MenuItem(mainMenu, SWT.CASCADE);
        fileItem.setText("文件(&F)");
        optionItem = new MenuItem(mainMenu, SWT.CASCADE);
        optionItem.setText("选项(&O)");
        helpItem = new MenuItem(mainMenu, SWT.CASCADE);
        helpItem.setText("帮助(&H)");
    }

    /**
     * 下拉菜单
     */
    private void createDropMenu() {
        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileItem.setMenu(fileMenu);

        MenuItem addTorrentItem = new MenuItem(fileMenu, SWT.PUSH);
        addTorrentItem.setText(UICharacter.ADD_TORRENT_DEFAULT);
        addTorrentItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem addTorrentNotDefaultItem = new MenuItem(fileMenu, SWT.PUSH);
        addTorrentNotDefaultItem.setText(UICharacter.ADD_TORRENT_NOT_DEFAULT);
        addTorrentNotDefaultItem.addSelectionListener(new MenuBarListener(
                shell, LISTEN_OBJECT_NAME));

        MenuItem addTorrentURLItem = new MenuItem(fileMenu, SWT.PUSH);
        addTorrentURLItem.setText(UICharacter.ADD_TORRENT_URL);
        addTorrentURLItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem createNewTorrentItem = new MenuItem(fileMenu, SWT.PUSH);
        createNewTorrentItem.setText(UICharacter.CREATE_NEW_TORRENT);
        createNewTorrentItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
        exitItem.setText(UICharacter.EXIT);
        exitItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        Menu optionMenu = new Menu(shell, SWT.DROP_DOWN);
        optionItem.setMenu(optionMenu);

        MenuItem showToolItem = new MenuItem(optionMenu, SWT.DROP_DOWN);
        showToolItem.setText(UICharacter.SHOW_TOOL_BAR);
        showToolItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem showDetailItem = new MenuItem(optionMenu, SWT.DROP_DOWN);
        showDetailItem.setText(UICharacter.SHOW_DETAIL_INFO);
        showDetailItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem showStatusBarItem = new MenuItem(optionMenu, SWT.DROP_DOWN);
        showStatusBarItem.setText(UICharacter.SHOW_STATUS_BAR);
        showStatusBarItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem showCategoryItem = new MenuItem(optionMenu, SWT.DROP_DOWN);
        showCategoryItem.setText(UICharacter.SHOW_CATEGORY_LIST);
        showCategoryItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem perferenceItem = new MenuItem(optionMenu, SWT.DROP_DOWN);
        perferenceItem.setText(UICharacter.PERFERENCE);
        perferenceItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
        helpItem.setMenu(helpMenu);

        MenuItem helpSonItem = new MenuItem(helpMenu, SWT.DROP_DOWN);
        helpSonItem.setText(UICharacter.HELP);
        helpSonItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));

        MenuItem aboutItem = new MenuItem(helpMenu, SWT.DROP_DOWN);
        aboutItem.setText(UICharacter.ABOUT);
        aboutItem.addSelectionListener(new MenuBarListener(shell,
                LISTEN_OBJECT_NAME));
    }

}
