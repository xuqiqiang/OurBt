package com.bjtu.ourbt.ui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.gudy.azureus2.core3.torrent.TOTorrent;
import org.gudy.azureus2.core3.torrent.TOTorrentCreator;
import org.gudy.azureus2.core3.torrent.TOTorrentException;
import org.gudy.azureus2.core3.torrent.TOTorrentFactory;

import com.bjtu.ourbt.ui.listener.SelectionButtonListener;

/**
 * 创建种子对话框
 */
public class CreateTorrentDialog extends Dialog {
    Shell shell;
    protected Object result;
    private Group selectGroup;
    private Combo addFileCombo;
    private Button addFileButton;
    private Button addDirectoryButton;
    private Label skipFileLabel;
    private Text text;
    private Group torrentProGroup;
    private Label trackLabel;
    private Text trackText;
    private Label webSeedLabel;
    private Text webSeedText;
    private Label commentLabel;
    private Text commentText;
    private Label pieceSizeLabel;
    private Combo pieceSizeCombo;
    private Group otherGroup;
    private Button startCheck;
    private Button privateTorrentCheck;
    private Button create;
    private Button cancel;

    public CreateTorrentDialog(Shell shell, int style) {
        super(shell, style);
    }

    public CreateTorrentDialog(Shell shell) {
        this(shell, SWT.NONE);
    }

    /**
     * Open the dialog
     * 
     * @return the result
     */
    public Object open() {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        return result;

    }

    /**
     * Create contents of the dialog
     */
    protected void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(320, 450);
        shell.setLocation(450, 180);
        shell.setText("创建Torrent");
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginWidth = 10;
        rowLayout.marginHeight = 10;
        rowLayout.spacing = 10;
        shell.setLayout(rowLayout);
        createSelectGroup();
        createtorrentProGroup();
        createotherGroup();
        createButton();
    }

    private void createSelectGroup() {
        selectGroup = new Group(shell, SWT.SHADOW_ETCHED_IN);
        RowData selectGroupData = new RowData(280, 95);
        selectGroup.setLayoutData(selectGroupData);
        selectGroup.setText("选择资源");

        addFileCombo = new Combo(selectGroup, SWT.NONE);
        addFileCombo.setBounds(10, 15, 270, 20);

        addFileButton = new Button(selectGroup, SWT.NONE);
        addFileButton.setText("添加文件");
        addFileButton.setBounds(100, 40, 70, 25);
        addFileButton.addListener(SWT.Selection, new SelectionButtonListener(
                shell, addFileCombo));

        addDirectoryButton = new Button(selectGroup, SWT.NONE);
        addDirectoryButton.setText("添加目录");
        addDirectoryButton.setBounds(180, 40, 100, 25);
        addDirectoryButton.addListener(SWT.Selection,
                new SelectionButtonListener(shell, addFileCombo));

        skipFileLabel = new Label(selectGroup, SWT.NONE);
        skipFileLabel.setText("忽略文件");
        skipFileLabel.setBounds(10, 75, 65, 20);

        text = new Text(selectGroup, SWT.BORDER);
        text.setBounds(80, 75, 200, 20);
    }

    private void createtorrentProGroup() {
        torrentProGroup = new Group(shell, SWT.SHADOW_ETCHED_IN);
        RowData torrentProData = new RowData(280, 170);
        torrentProGroup.setLayoutData(torrentProData);
        torrentProGroup.setText("Torrent属性");
        trackLabel = new Label(torrentProGroup, SWT.NONE);
        trackLabel.setText("Tracks:");
        trackLabel.setBounds(10, 15, 50, 20);

        trackText = new Text(torrentProGroup, SWT.BORDER);
        trackText.setBounds(65, 15, 210, 50);

        webSeedLabel = new Label(torrentProGroup, SWT.NONE);
        webSeedLabel.setText("网页种子:");
        webSeedLabel.setBounds(10, 70, 50, 20);
        webSeedText = new Text(torrentProGroup, SWT.BORDER | SWT.LEFT);
        webSeedText.setBounds(65, 70, 210, 50);

        commentLabel = new Label(torrentProGroup, SWT.NONE);
        commentLabel.setText("注释:");
        commentLabel.setBounds(10, 125, 50, 20);
        commentText = new Text(torrentProGroup, SWT.BORDER | SWT.LEFT);
        commentText.setBounds(65, 125, 210, 20);

        pieceSizeLabel = new Label(torrentProGroup, SWT.NONE);
        pieceSizeLabel.setText("块大小:");
        pieceSizeLabel.setBounds(10, 150, 65, 20);
        pieceSizeCombo = new Combo(torrentProGroup, SWT.READ_ONLY);
        pieceSizeCombo = addComboValue(pieceSizeCombo);
        pieceSizeCombo.setBounds(170, 150, 100, 20);
    }

    private void createotherGroup() {
        otherGroup = new Group(shell, SWT.SHADOW_ETCHED_IN);
        RowData otherGroupData = new RowData(280, 30);
        otherGroup.setLayoutData(otherGroupData);
        otherGroup.setText("其它");
        startCheck = new Button(otherGroup, SWT.CHECK);
        startCheck.setText("开始做种");
        startCheck.setBounds(10, 15, 100, 20);

        privateTorrentCheck = new Button(otherGroup, SWT.CHECK);
        privateTorrentCheck.setText("私有种子");
        privateTorrentCheck.setBounds(140, 15, 100, 20);

    }

    private void createButton() {
        Composite composite = new Composite(shell, SWT.NONE);
        RowData buttonData = new RowData(280, 30);
        composite.setLayoutData(buttonData);
        create = new Button(composite, SWT.PUSH);
        create.setText("创建并保存");
        create.setBounds(30, 0, 100, 20);
        create.addSelectionListener(new CreatedListener());

        cancel = new Button(composite, SWT.PUSH);
        cancel.setText("取消");
        cancel.setBounds(150, 0, 80, 20);
        cancel.addListener(SWT.Selection, new SelectionButtonListener(shell));
    }

    private Combo addComboValue(Combo combo) {
        combo.add("auto detect");
        combo.setData("auto detect", 128 * 1024);// 默认的块大小128K
        combo.add("32kb");
        combo.setData("32kb", 32 * 1024);
        combo.add("64kb");
        combo.setData("64kb", 64 * 1024);
        combo.add("128kb");
        combo.setData("128kb", 128 * 1024);
        combo.add("256kb");
        combo.setData("256kb", 256 * 1024);
        combo.add("512kb");
        combo.setData("512kb", 512 * 1024);
        combo.add("1M");
        combo.setData("1M", 1 * 1024 * 1024);
        combo.add("2M");
        combo.setData("2M", 2 * 1024 * 1024);
        combo.add("4M");
        combo.setData("4M", 4 * 1024 * 1024);
        combo.select(0);
        return combo;
    }

    private class CreatedListener implements SelectionListener {

        public void widgetDefaultSelected(SelectionEvent e) {

        }

        public void widgetSelected(SelectionEvent e) {
            TOTorrentCreator c;
            TOTorrent t;
            try {
                FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
                fileDialog.setText("保存种子文件");
                fileDialog
                        .setFilterNames(new String[] { "Torrents(*.torrent)" });
                fileDialog.setFilterExtensions(new String[] { "*.torrent" });
                fileDialog.open();

                // 获取参数生成torrent文件

                c = TOTorrentFactory.createFromFileOrDirWithFixedPieceLength(
                        new File(addFileCombo.getText()),
                        new URL(trackText.getText()), (Integer) pieceSizeCombo
                                .getData(pieceSizeCombo.getText()));
                t = c.create();

                String path = fileDialog.getFilterPath() + "\\"
                        + fileDialog.getFileName();

                // B编码torrent文件
                t.serialiseToBEncodedFile(new File(path));
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (TOTorrentException e1) {
                e1.printStackTrace();
            }

        }

    }

}
