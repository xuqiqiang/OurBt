package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.bjtu.ourbt.util.UICharacter;

/**
 * 设置属性中的选项类表
 */
public class OptionTree extends Dialog {
    private SashForm sashForm;
    private Tree tree;
    private TreeItem generalItem;

    private TreeItem uiSetItem;

    private TreeItem directionItem;

    private TreeItem connectItem;

    private TreeItem bandWidthItem;

    private TreeItem bitTorrentItem;

    private TreeItem queueItem;

    private TreeItem scheduleItem;

    private TreeItem webUIItem;

    private TreeItem advancedItem;

    private TreeItem uiExtraItem;

    private TreeItem diskCacheItem;

    private String selectItemName;
    private Composite composite;

    public OptionTree(SashForm sashForm) {
        super(sashForm.getShell(), SWT.NONE);
        this.sashForm = sashForm;
    }

    public void open() {
        createContent();
        composite = new Composite(sashForm, SWT.NONE);
        tree.addListener(SWT.Selection, new SelectionListener());
        sashForm.setWeights(new int[] { 20, 75 });
        sashForm.setLayout(new FillLayout());
    }

    private void createContent() {
        tree = new Tree(sashForm, SWT.BORDER);
        generalItem = new TreeItem(tree, SWT.NONE);
        generalItem.setText(UICharacter.GENERAL);

        uiSetItem = new TreeItem(tree, SWT.NONE);
        uiSetItem.setText(UICharacter.UISETTING);

        directionItem = new TreeItem(tree, SWT.NONE);
        directionItem.setText(UICharacter.DIRECTORIES);

        connectItem = new TreeItem(tree, SWT.NONE);
        connectItem.setText(UICharacter.CONNECTIONS);

        bandWidthItem = new TreeItem(tree, SWT.None);
        bandWidthItem.setText(UICharacter.BANDWIDTH);

        bitTorrentItem = new TreeItem(tree, SWT.NONE);
        bitTorrentItem.setText(UICharacter.BITTORRENT);

        queueItem = new TreeItem(tree, SWT.NONE);
        queueItem.setText(UICharacter.QUEUEING);

        scheduleItem = new TreeItem(tree, SWT.NONE);
        scheduleItem.setText(UICharacter.SCHEDULE);

        webUIItem = new TreeItem(tree, SWT.NONE);
        webUIItem.setText(UICharacter.WEB_UI);

        advancedItem = new TreeItem(tree, SWT.NONE);
        advancedItem.setText(UICharacter.ADVANCED);
        advancedItem.setExpanded(true);

        uiExtraItem = new TreeItem(advancedItem, SWT.NONE);
        uiExtraItem.setText(UICharacter.UIEXTRA);

        diskCacheItem = new TreeItem(advancedItem, SWT.NONE);
        diskCacheItem.setText(UICharacter.DISKCACHE);

    }

    /**
     * 内部监听器，根据选择的选项显示出选项的信息
     */
    private final class SelectionListener implements Listener {

        public void handleEvent(Event event) {

            TreeItem treeItem[] = tree.getSelection();
            selectItemName = treeItem[0].getText();

            // 遍历枚举类，找到匹配的项
            for (PerferenceOptionEnum option : PerferenceOptionEnum.values()) {
                if (option.getOptionName().equals(selectItemName)) {
                    if (!option.isExist()) {
                        option.createOptionComposite(selectItemName, composite);
                    }
                } else {
                    if (option.isExist()) {
                        option.dispose();
                    }
                }
            }
            composite.layout(); // 整了半天才想到是这条语句，少了这句，不能显示变化后的面板
        }
    }

}
