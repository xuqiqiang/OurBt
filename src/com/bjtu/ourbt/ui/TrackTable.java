package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * tracker信息显示表
 */
public class TrackTable {
    private Composite composite;
    private TableColumn name;
    private TableColumn status;
    private TableColumn update;
    private TableColumn seed;
    private TableColumn peer;
    private TableColumn download;

    public TrackTable(Composite composite) {
        this.composite = composite;
    }

    public void open() {
        Table table = new Table(composite, SWT.BORDER);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        name = new TableColumn(table, SWT.NONE);
        name.setText("name");
        name.setWidth(200);
        status = new TableColumn(table, SWT.NONE);
        status.setText("status");
        status.setWidth(100);
        update = new TableColumn(table, SWT.NONE);
        update.setText("update");
        update.setWidth(100);
        seed = new TableColumn(table, SWT.NONE);
        seed.setText("seed");
        seed.setWidth(100);
        peer = new TableColumn(table, SWT.NONE);
        peer.setText("peer");
        peer.setWidth(100);
        download = new TableColumn(table, SWT.NONE);
        download.setText("download");
        download.setWidth(100);

    }

}
