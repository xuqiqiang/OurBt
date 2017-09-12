package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * 节点信息表
 */
public class PeerTable {
    private Composite composite;
    private TableColumn ip;
    private TableColumn client;
    private TableColumn flag;
    private TableColumn rate;
    private TableColumn downSpeed;
    private TableColumn upSpeed;
    private TableColumn request;
    private TableColumn downloaded;
    private TableColumn uploaded;
    private TableColumn speed;

    public PeerTable(Composite composite) {
        this.composite = composite;
    }

    public void open() {
        Table table = new Table(composite, SWT.BORDER);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        ip = new TableColumn(table, SWT.NONE);
        ip.setText("ip");
        ip.setWidth(200);
        client = new TableColumn(table, SWT.NONE);
        client.setText("client");
        client.setWidth(100);
        flag = new TableColumn(table, SWT.NONE);
        flag.setText("flag");
        flag.setWidth(100);
        rate = new TableColumn(table, SWT.NONE);
        rate.setText("rate");
        rate.setWidth(100);
        downSpeed = new TableColumn(table, SWT.NONE);
        downSpeed.setText("downSpeed");
        downSpeed.setWidth(100);
        upSpeed = new TableColumn(table, SWT.NONE);
        upSpeed.setText("upSpeed");
        upSpeed.setWidth(100);

        request = new TableColumn(table, SWT.NONE);
        request.setText("request");
        request.setWidth(100);

        downloaded = new TableColumn(table, SWT.NONE);
        downloaded.setText("downloaded");
        downloaded.setWidth(100);
        uploaded = new TableColumn(table, SWT.NONE);
        uploaded.setText("uploaded");
        uploaded.setWidth(100);
        upSpeed = new TableColumn(table, SWT.NONE);
        upSpeed.setText("upSpeed");
        upSpeed.setWidth(100);
        speed = new TableColumn(table, SWT.NONE);
        speed.setText("speed");
        speed.setWidth(100);

    }

}
