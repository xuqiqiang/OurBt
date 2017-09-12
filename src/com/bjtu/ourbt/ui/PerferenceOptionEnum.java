package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public enum PerferenceOptionEnum {
    /**
	 * 
	 */
    GENERAL("常规") {
        private Composite composite;
        private Group languageGroup;
        private Group integration;
        private Group privacy;
        private Group download;
        private Label label;

        @Override
        public void createOptionComposite(String optionName,
                Composite parentComposite) {
            composite = parentComposite;
            RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
            rowLayout.spacing = 5;
            rowLayout.marginHeight = 8;
            rowLayout.marginWidth = 8;
            parentComposite.setLayout(rowLayout);
            label = createOptionNameLabel(optionName, composite);
            createLanguage();
            createIntegration();
            createPrivacy();
            createDownload();
        }

        private void createLanguage() {
            languageGroup = new Group(composite, SWT.NONE);
            languageGroup.setText("Language");
            RowData rowData = new RowData(440, 30);
            languageGroup.setLayoutData(rowData);
            Label label = new Label(languageGroup, SWT.NONE);
            label.setText("Language:");
            label.setBounds(5, 15, 80, 20);
            Combo combo = new Combo(languageGroup, SWT.NONE);
            combo.add("English");
            combo.add("简体中文");
            combo.setBounds(150, 15, 250, 20);
        }

        private void createIntegration() {
            integration = new Group(composite, SWT.NONE);
            integration.setText("Windows Integration");
            RowData rowData = new RowData(440, 80);
            integration.setLayoutData(rowData);
            Button associate1 = new Button(integration, SWT.NONE);
            associate1.setText("Associate with .torrent files");
            associate1.setBounds(10, 15, 200, 20);

            Button check1 = new Button(integration, SWT.CHECK);
            check1.setText("Check associate on startup");
            check1.setBounds(230, 15, 200, 20);
            check1.setSelection(true);

            Button associate2 = new Button(integration, SWT.NONE);
            associate2.setText("Associate with .btsearch files");
            associate2.setBounds(10, 40, 200, 20);

            Button check2 = new Button(integration, SWT.CHECK);
            check2.setText("Start QuickBT on system startup");
            check2.setBounds(230, 40, 205, 20);

            Button associate3 = new Button(integration, SWT.NONE);
            associate3.setText("Associate wiht .magnet UIRs");
            associate3.setBounds(10, 65, 200, 20);

            Button install = new Button(integration, SWT.NONE);
            install.setText("Install IPV6/Teredo");
            install.setBounds(230, 65, 200, 20);
        }

        private void createPrivacy() {
            privacy = new Group(composite, SWT.NONE);
            privacy.setText("Privacy");
            RowData rowData = new RowData(440, 80);
            privacy.setLayoutData(rowData);

            Button check1 = new Button(privacy, SWT.CHECK);
            check1.setSelection(true);
            check1.setText("Check for updates automatically");
            check1.setBounds(10, 15, 200, 20);

            Button check2 = new Button(privacy, SWT.CHECK);
            check2.setText("Update to beta versions");
            check2.setBounds(230, 15, 150, 20);

            Button check3 = new Button(privacy, SWT.CHECK);
            check3.setSelection(true);
            check3.setText("Send anonymous information when checking for updates");
            check3.setBounds(10, 40, 350, 20);

            Label label = new Label(privacy, SWT.CHECK);
            label.setText("Boss-key");
            label.setBounds(10, 65, 50, 20);

            Text text = new Text(privacy, SWT.BORDER);
            text.setBounds(65, 65, 145, 20);

            Button button = new Button(privacy, SWT.NONE);
            button.setText("Clear Privacy Data");
            button.setBounds(230, 65, 200, 20);

        }

        private void createDownload() {
            download = new Group(composite, SWT.NONE);
            download.setText("When Downloading");
            RowData rowData = new RowData(440, 50);
            download.setLayoutData(rowData);

            Button check1 = new Button(download, SWT.CHECK);
            check1.setText("Append .!bt to incomplete files");
            check1.setBounds(10, 15, 230, 20);

            Button check2 = new Button(download, SWT.CHECK);
            check2.setText("Pre-allocate all files");
            check2.setBounds(250, 15, 190, 20);

            Button check3 = new Button(download, SWT.CHECK);
            check3.setSelection(true);
            check3.setText("Prevent standby if there are active torrents ");
            check3.setBounds(10, 40, 300, 20);

        }

        @Override
        public void dispose() {
            if (isExist()) {
                label.dispose();
                label.dispose();
                languageGroup.dispose();
                integration.dispose();
                privacy.dispose();
                download.dispose();
            }
        }

        @Override
        public boolean isExist() {
            if (label == null && languageGroup == null && integration == null
                    && privacy == null && download == null) {
                return false;
            } else if (label.isDisposed()) {
                return false;
            } else {
                return true;
            }
        }

    },
    /**
	 * 
	 */
    UISETTING("界面") {
        private Composite composite;
        private Label label;
        private Group displayGroup;
        private Group systemTrayGroup;
        private Group addTorrentGroup;
        private Group actionGroup;
        private Button check1;
        private Button check2;
        private Button check3;
        private Button check4;
        private Button check5;
        private Button check6;
        private Button check7;
        private Button check8;
        private Button check9;
        private Button check10;
        private Button check11;
        private Button check12;
        private Button check13;
        private Button check14;
        private Button check15;
        private Label label1;
        private Label label2;
        private Combo combo1;
        private Combo combo2;

        @Override
        public void createOptionComposite(String optionName,
                Composite parentComposite) {
            composite = parentComposite;
            RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
            rowLayout.marginWidth = 8;
            rowLayout.marginHeight = 8;
            rowLayout.spacing = 5;
            parentComposite.setLayout(rowLayout);
            label = createOptionNameLabel(optionName, composite);
            createDisplayGroup();
            createSystemTrayGroup();
            createAddTorrentGroup();
            createActionGroup();
        }

        private void createDisplayGroup() {
            displayGroup = new Group(composite, SWT.NONE);
            displayGroup.setText("Display Options");
            RowData rowData = new RowData(440, 80);
            displayGroup.setLayoutData(rowData);

            check1 = new Button(displayGroup, SWT.CHECK);
            check1.setText("Confirm when deleting torrents");
            check1.setSelection(true);
            check1.setBounds(10, 15, 210, 20);

            check2 = new Button(displayGroup, SWT.CHECK);
            check2.setText("Confirm when deleting tracks");
            check2.setSelection(true);
            check2.setBounds(230, 15, 210, 20);

            check3 = new Button(displayGroup, SWT.CHECK);
            check3.setText("Show confirm dialog when exit");
            check3.setSelection(true);
            check3.setBounds(10, 40, 210, 20);

            check4 = new Button(displayGroup, SWT.CHECK);
            check4.setText("Alternate list background color");
            check4.setBounds(230, 40, 210, 20);

            check5 = new Button(displayGroup, SWT.CHECK);
            check5.setText("Show current speed in title bar");
            check5.setBounds(10, 65, 210, 20);

            check6 = new Button(displayGroup, SWT.CHECK);
            check6.setText("Show speed limits in status bar ");
            check6.setBounds(230, 65, 210, 20);

        }

        private void createSystemTrayGroup() {
            systemTrayGroup = new Group(composite, SWT.NONE);
            systemTrayGroup.setText("System Tray");
            RowData rowData = new RowData(440, 80);
            systemTrayGroup.setLayoutData(rowData);

            check7 = new Button(systemTrayGroup, SWT.CHECK);
            check7.setText("Close to tray");
            check7.setSelection(true);
            check7.setBounds(10, 15, 210, 20);

            check8 = new Button(systemTrayGroup, SWT.CHECK);
            check8.setText("Minimize to tray");
            check8.setSelection(true);
            check8.setBounds(230, 15, 210, 20);

            check9 = new Button(systemTrayGroup, SWT.CHECK);
            check9.setText("Always show tray icon");
            check9.setSelection(true);
            check9.setBounds(10, 40, 210, 20);

            check10 = new Button(systemTrayGroup, SWT.CHECK);
            check10.setText("Singal click to tray icon to open");
            check10.setBounds(230, 40, 215, 20);

            check11 = new Button(systemTrayGroup, SWT.CHECK);
            check11.setText("Show ballon notifications in tray");
            check11.setBounds(10, 65, 215, 20);
            check11.setSelection(true);

            check12 = new Button(systemTrayGroup, SWT.CHECK);
            check12.setText("Always active when clicked");
            check12.setBounds(230, 65, 210, 20);
            check12.setSelection(true);
        }

        private void createAddTorrentGroup() {
            addTorrentGroup = new Group(composite, SWT.NONE);
            addTorrentGroup.setText("When Adding Torrent");
            RowData rowData = new RowData(440, 50);
            addTorrentGroup.setLayoutData(rowData);

            check13 = new Button(addTorrentGroup, SWT.CHECK);
            check13.setText("Don't start the download automatically");
            check13.setBounds(10, 15, 250, 20);

            check14 = new Button(addTorrentGroup, SWT.CHECK);
            check14.setText("Activate the program window");
            check14.setSelection(true);
            check14.setBounds(265, 15, 175, 20);

            check15 = new Button(addTorrentGroup, SWT.CHECK);
            check15.setText("Show a window that displays the files inside the torrent");
            check15.setBounds(10, 40, 350, 20);
        }

        private void createActionGroup() {
            actionGroup = new Group(composite, SWT.NONE);
            actionGroup.setText("Action for double click");
            RowData rowData = new RowData(440, 50);
            actionGroup.setLayoutData(rowData);

            label1 = new Label(actionGroup, SWT.NONE);
            label1.setText("For seeding torrents:");
            label1.setBounds(10, 15, 200, 15);

            combo1 = new Combo(actionGroup, SWT.NONE);
            addComboValue(combo1);
            combo1.setBounds(230, 15, 200, 15);

            label2 = new Label(actionGroup, SWT.NONE);
            label2.setText("For downloading torrents:");
            label2.setBounds(10, 40, 200, 15);

            combo2 = new Combo(actionGroup, SWT.NONE);
            addComboValue(combo2);
            combo2.setBounds(230, 40, 200, 15);
        }

        private void addComboValue(Combo combo) {
            combo.add("Show Properties");
            combo.add("Start/Stop");
            combo.add("Open Folder");
            combo.add("Show Download Bar");
            combo.setSelection(new Point(230, 15));
        }

        @Override
        public void dispose() {
            if (isExist()) {
                label.dispose();
                displayGroup.dispose();
                systemTrayGroup.dispose();
                addTorrentGroup.dispose();
                actionGroup.dispose();
            }
        }

        @Override
        public boolean isExist() {
            if (displayGroup == null && actionGroup == null && label == null
                    && systemTrayGroup == null && addTorrentGroup == null) {
                return false;
            } else if (label.isDisposed()) {
                return false;
            } else {
                return true;
            }

        }

    },
    /**
	 * 
	 */
    DIRECTORIES("目录") {
        private Composite composite;
        private Label label;
        private Group locationFileGroup;
        private Group locationTorrentGroup;
        private Button check1;
        private Button check2;
        private Button check3;
        private Button check4;
        private Button check5;
        private Button check6;
        private Button check7;
        private Button check8;
        private Button button1;
        private Button button2;
        private Button button3;
        private Button button4;
        private Button button5;
        private Text text1;
        private Text text2;
        private Text text3;
        private Text text4;
        private Text text5;

        @Override
        public void createOptionComposite(String optionName,
                Composite parentComposite) {
            composite = parentComposite;
            label = createOptionNameLabel(optionName, composite);
            RowLayout rowLayout = new RowLayout();
            rowLayout.marginHeight = 8;
            rowLayout.marginWidth = 8;
            rowLayout.spacing = 5;
            composite.setLayout(rowLayout);
            createLocationFile();
            createLocationTorrent();

        }

        private void createLocationFile() {
            locationFileGroup = new Group(composite, SWT.NONE);
            locationFileGroup.setText("Location of Downloaded File");
            RowData rowData = new RowData(440, 120);
            locationFileGroup.setLayoutData(rowData);

            check1 = new Button(locationFileGroup, SWT.CHECK);
            check1.setText("Put new downloads in:");
            check1.setBounds(10, 10, 200, 20);
            check1.addSelectionListener(new SelectionListener() {

                public void widgetDefaultSelected(SelectionEvent e) {
                    check2.setEnabled(false);
                    text1.setEnabled(false);
                    button1.setEnabled(false);

                }

                public void widgetSelected(SelectionEvent e) {
                    check2.setEnabled(check1.getSelection());
                    text1.setEnabled(check1.getSelection());
                    button1.setEnabled(check1.getSelection());
                }

            });

            check2 = new Button(locationFileGroup, SWT.CHECK);
            check2.setText("Always show dialog on manual add");
            check2.setBounds(220, 10, 210, 20);
            check2.setSelection(true);
            check2.setEnabled(false);

            text1 = new Text(locationFileGroup, SWT.BORDER);
            text1.setBounds(10, 35, 380, 20);
            text1.setEnabled(false);

            button1 = new Button(locationFileGroup, SWT.NONE);
            button1.setText("...");
            button1.setBounds(400, 35, 30, 20);
            button1.setEnabled(false);

            check3 = new Button(locationFileGroup, SWT.CHECK);
            check3.setText("Move Completed downloads to:");
            check3.setBounds(10, 60, 200, 20);
            check3.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {
                    text2.setEnabled(check3.getSelection());
                    button2.setEnabled(check3.getSelection());
                    check4.setEnabled(check3.getSelection());
                    check5.setEnabled(check3.getSelection());
                }

            });

            check4 = new Button(locationFileGroup, SWT.CHECK);
            check4.setText("Append the torrent's label");
            check4.setBounds(220, 60, 200, 20);
            check4.setEnabled(false);

            text2 = new Text(locationFileGroup, SWT.BORDER);
            text2.setBounds(10, 85, 380, 20);
            text2.setEnabled(false);

            button2 = new Button(locationFileGroup, SWT.NONE);
            button2.setText("...");
            button2.setBounds(400, 85, 30, 20);
            button2.setEnabled(false);

            check5 = new Button(locationFileGroup, SWT.CHECK);
            check5.setText("Only move from the default download directory");
            check5.setBounds(10, 110, 300, 20);
            check5.setSelection(true);
            check5.setEnabled(false);
        }

        private void createLocationTorrent() {
            locationTorrentGroup = new Group(composite, SWT.NONE);
            locationTorrentGroup.setText("location of .torrents");
            RowData rowData = new RowData(440, 155);
            locationTorrentGroup.setLayoutData(rowData);

            // ////////////////////////////////////
            check6 = new Button(locationTorrentGroup, SWT.CHECK);
            check6.setText("Store .torrents in:");
            check6.setBounds(10, 15, 200, 20);
            check6.addListener(SWT.Selection, new Listener() {

                public void handleEvent(Event event) {
                    text3.setEnabled(check6.getSelection());
                    button3.setEnabled(check6.getSelection());
                }

            });

            text3 = new Text(locationTorrentGroup, SWT.BORDER);
            text3.setBounds(10, 40, 380, 20);
            text3.setEnabled(false);

            button3 = new Button(locationTorrentGroup, SWT.NONE);
            button3.setText("...");
            button3.setBounds(400, 40, 30, 20);
            button3.setEnabled(false);

            // ////////////////////////////////
            check7 = new Button(locationTorrentGroup, SWT.CHECK);
            check7.setText("Move .torrent from finished jobs to:");
            check7.setBounds(10, 65, 300, 20);
            check7.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {
                    text4.setEnabled(check7.getSelection());
                    button4.setEnabled(check7.getSelection());
                }
            });

            text4 = new Text(locationTorrentGroup, SWT.BORDER);
            text4.setBounds(10, 90, 380, 20);
            text4.setEnabled(false);

            button4 = new Button(locationTorrentGroup, SWT.NONE);
            button4.setBounds(400, 90, 30, 20);
            button4.setText("...");

            // /////////////////////////////////////////////
            check8 = new Button(locationTorrentGroup, SWT.CHECK);
            check8.setText("Move .torrent from finished jobs to:");
            check8.setBounds(10, 115, 300, 20);
            check8.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {
                    text5.setEnabled(check8.getSelection());
                    button5.setEnabled(check8.getSelection());
                }
            });

            text5 = new Text(locationTorrentGroup, SWT.BORDER);
            text5.setBounds(10, 140, 380, 20);
            text5.setEnabled(false);

            button5 = new Button(locationTorrentGroup, SWT.NONE);
            button5.setBounds(400, 140, 30, 20);
            button5.setText("...");

        }

        @Override
        public void dispose() {
            if (isExist()) {
                locationFileGroup.dispose();
                locationTorrentGroup.dispose();
                label.dispose();
            }
        }

        @Override
        public boolean isExist() {
            if (locationFileGroup == null && locationTorrentGroup == null
                    && label == null) {
                return false;
            } else if (label.isDisposed()) {
                return false;
            } else {
                return true;
            }

        }

    },
    /**
	 * 
	 */
    CONNECTIONS("连接") {
        private Composite composite;
        private Label label;
        private Group portGroup;
        private Group proxyGroup;
        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private Label label5;
        private Label label6;
        private Text text1;
        private Text text2;
        private Text text4;
        private Text text5;
        private Text text6;
        private Button button1;
        private Button check1;
        private Button check2;
        private Button check3;
        private Button check4;
        private Button check5;
        private Button check6;
        private Button check7;
        private Combo combo;

        @Override
        public void createOptionComposite(String optionName,
                Composite parentComposite) {
            composite = parentComposite;
            RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
            rowLayout.marginWidth = 8;
            rowLayout.marginHeight = 8;
            rowLayout.spacing = 5;
            composite.setLayout(rowLayout);
            label = createOptionNameLabel(optionName, composite);
            createPortGroup();
            createProxyGroup();

        }

        private void createPortGroup() {
            portGroup = new Group(composite, SWT.NONE);
            portGroup.setText("Listening port");
            RowData rowData = new RowData(440, 80);
            portGroup.setLayoutData(rowData);

            label1 = new Label(portGroup, SWT.NONE);
            label1.setText("Port used for incoming connections:");
            label1.setBounds(10, 15, 250, 20);

            text1 = new Text(portGroup, SWT.BORDER | SWT.RIGHT);
            text1.setBounds(270, 15, 80, 20);

            button1 = new Button(portGroup, SWT.PUSH);
            button1.setText("Random port");
            button1.setBounds(360, 15, 80, 20);

            check1 = new Button(portGroup, SWT.CHECK);
            check1.setText("Enable Upnp port mapping");
            check1.setBounds(10, 40, 200, 20);
            check1.setSelection(true);

            check2 = new Button(portGroup, SWT.CHECK);
            check2.setText("Randomize port each start");
            check2.setBounds(230, 40, 200, 20);

            check3 = new Button(portGroup, SWT.CHECK);
            check3.setText("Enable NAT-PMP port mapping");
            check3.setBounds(10, 65, 200, 20);
            check3.setSelection(true);

            check4 = new Button(portGroup, SWT.CHECK);
            check4.setText("Add Windows Firewall exception");
            check4.setBounds(230, 65, 200, 20);
            check4.setSelection(true);

        }

        private void createProxyGroup() {
            proxyGroup = new Group(composite, SWT.NONE);
            proxyGroup.setText("Proxy Server");
            RowData rowData = new RowData(440, 130);
            proxyGroup.setLayoutData(rowData);

            label2 = new Label(proxyGroup, SWT.NONE);
            label2.setText("Type:");
            label2.setBounds(10, 20, 50, 20);

            combo = new Combo(proxyGroup, SWT.READ_ONLY);
            combo.setBounds(70, 15, 70, 20);
            combo.add("none");
            combo.add("socks4");
            combo.add("socks5");
            combo.add("HTTPS");
            combo.add("HTTP");
            combo.select(0);
            // combo.setSelection(new Point(0,4));
            combo.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    if (!combo.getText().equals("none")) {
                        label3.setEnabled(true);
                        text2.setEnabled(true);
                        label4.setEnabled(true);
                        text4.setEnabled(true);
                        check5.setEnabled(true);
                        check6.setEnabled(true);
                        check7.setEnabled(true);
                    } else {
                        label3.setEnabled(false);
                        text2.setEnabled(false);
                        label4.setEnabled(false);
                        text4.setEnabled(false);
                        check5.setEnabled(false);
                        check6.setEnabled(false);
                        check7.setEnabled(false);
                    }
                }

            });

            label3 = new Label(proxyGroup, SWT.NONE);
            label3.setText("Proxy:");
            label3.setBounds(150, 20, 40, 20);
            label3.setEnabled(false);

            text2 = new Text(proxyGroup, SWT.BORDER);
            text2.setBounds(190, 15, 110, 20);
            text2.setEnabled(false);

            label4 = new Label(proxyGroup, SWT.NONE);
            label4.setText("Port:");
            label4.setBounds(310, 20, 40, 20);
            label4.setEnabled(false);

            text4 = new Text(proxyGroup, SWT.BORDER);
            text4.setBounds(350, 15, 80, 20);
            text4.setEnabled(false);

            check5 = new Button(proxyGroup, SWT.CHECK);
            check5.setText("Authentication");
            check5.setBounds(10, 40, 80, 20);
            check5.setEnabled(false);
            check5.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    label5.setEnabled(check5.getSelection());
                    text5.setEnabled(check5.getSelection());
                    label6.setEnabled(check5.getSelection());
                    text6.setEnabled(check5.getSelection());
                }

            });

            label5 = new Label(proxyGroup, SWT.NONE);
            label5.setText("Usename");
            label5.setBounds(100, 45, 50, 20);
            label5.setEnabled(false);

            text5 = new Text(proxyGroup, SWT.BORDER);
            text5.setBounds(160, 40, 80, 20);
            text5.setEnabled(false);

            label6 = new Label(proxyGroup, SWT.NONE);
            label6.setText("Password");
            label6.setBounds(250, 45, 80, 20);
            label6.setEnabled(false);

            text6 = new Text(proxyGroup, SWT.BORDER | SWT.PASSWORD);
            text6.setBounds(340, 40, 90, 20);
            text6.setEnabled(false);

            check6 = new Button(proxyGroup, SWT.CHECK);
            check6.setText("Resolve hostnames through proxy");
            check6.setBounds(10, 65, 300, 20);
            check6.setEnabled(false);

            check7 = new Button(proxyGroup, SWT.CHECK);
            check7.setText("Use proxy server for peer-to-peer connection");
            check7.setBounds(10, 90, 350, 20);
            check7.setEnabled(false);

        }

        @Override
        public void dispose() {
            if (isExist()) {
                label.dispose();
                portGroup.dispose();
                proxyGroup.dispose();
            }

        }

        @Override
        public boolean isExist() {
            if (label == null && portGroup == null && proxyGroup == null) {
                return false;
            } else if (label.isDisposed()) {
                return false;
            } else {
                return true;
            }
        }

    },
    /**
	 * 
	 */
    BANDWIDTH("带宽") {
        private Composite composite;
        private Label label;
        private Group updateLimitGroup;
        private Group downloadLimitGroup;
        private Group numberOfConnectionGroup;
        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private Label label5;
        private Button check1;
        private Button check2;
        private Text text1;
        private Text text2;
        private Text text3;
        private Text text4;
        private Text text5;
        private Text text6;

        @Override
        public void createOptionComposite(String optionName,
                Composite parentComposite) {
            composite = parentComposite;
            RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
            rowLayout.marginWidth = 8;
            rowLayout.marginHeight = 8;
            rowLayout.spacing = 5;
            composite.setLayout(rowLayout);
            label = createOptionNameLabel(optionName, composite);
            createUpdateLimitGroup();
            createDownloadLimitGroup();
            createNumberOfConnetionGroup();
        }

        private void createUpdateLimitGroup() {
            updateLimitGroup = new Group(composite, SWT.NONE);
            updateLimitGroup.setText("Global Upload Rate Limiting ");
            RowData rowData = new RowData(440, 60);
            updateLimitGroup.setLayoutData(rowData);

            label1 = new Label(updateLimitGroup, SWT.NONE);
            label1.setText("Max upload rate(Kb/s):[0:unlimited]");
            label1.setBounds(10, 20, 230, 20);

            check1 = new Button(updateLimitGroup, SWT.CHECK);
            check1.setText("Automatic");
            check1.setBounds(250, 20, 100, 20);
            check1.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    text1.setEnabled(check1.getSelection());
                    check2.setEnabled(check1.getSelection());
                    // 完善监听，没达到预期效果
                }

            });

            text1 = new Text(updateLimitGroup, SWT.BORDER);
            text1.setBounds(380, 15, 60, 20);
            text1.setEnabled(false);

            check2 = new Button(updateLimitGroup, SWT.CHECK);
            check2.setText("Alternate update rate when not downloading(Kb/s):");
            check2.setBounds(20, 40, 350, 20);
            check2.setEnabled(false);
            check2.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    text2.setEnabled(check2.getSelection());
                }

            });

            text2 = new Text(updateLimitGroup, SWT.BORDER);
            text2.setBounds(380, 40, 60, 20);
            text2.setEnabled(false);
        }

        private void createDownloadLimitGroup() {
            downloadLimitGroup = new Group(composite, SWT.NONE);
            downloadLimitGroup.setText("Global Download Rate Limited");
            RowData rowData = new RowData(440, 30);
            downloadLimitGroup.setLayoutData(rowData);

            label2 = new Label(downloadLimitGroup, SWT.NONE);
            label2.setText("Maximum download rate limited(Kb/s):[0:unlimited]");
            label2.setBounds(10, 20, 300, 20);

            text3 = new Text(downloadLimitGroup, SWT.BORDER);
            text3.setBounds(380, 15, 60, 20);

        }

        private void createNumberOfConnetionGroup() {
            numberOfConnectionGroup = new Group(composite, SWT.NONE);
            numberOfConnectionGroup.setText("Number of Connetions");
            RowData rowData = new RowData(440, 80);
            numberOfConnectionGroup.setLayoutData(rowData);

            label3 = new Label(numberOfConnectionGroup, SWT.NONE);
            label3.setText("Global maximum number of connecions");
            label3.setBounds(10, 15, 300, 20);

            text4 = new Text(numberOfConnectionGroup, SWT.BORDER);
            text4.setBounds(380, 15, 60, 20);
            text4.setText("200");

            label4 = new Label(numberOfConnectionGroup, SWT.NONE);
            label4.setText("Maximum number of connected peers per torrent:");
            label4.setBounds(10, 40, 300, 20);

            text5 = new Text(numberOfConnectionGroup, SWT.BORDER);
            text5.setBounds(380, 40, 60, 20);
            text5.setText("50");

            label5 = new Label(numberOfConnectionGroup, SWT.NONE);
            label5.setBounds(10, 65, 300, 20);
            label5.setText("Number of upload slot per torrent:");

            text6 = new Text(numberOfConnectionGroup, SWT.BORDER);
            text6.setBounds(380, 65, 60, 20);
            text6.setText("4");

        }

        @Override
        public void dispose() {
            if (isExist()) {
                updateLimitGroup.dispose();
                downloadLimitGroup.dispose();
                numberOfConnectionGroup.dispose();
                label.dispose();
            }
        }

        @Override
        public boolean isExist() {
            if (updateLimitGroup == null && downloadLimitGroup == null
                    && numberOfConnectionGroup == null && label == null) {
                return false;
            } else if (label.isDisposed()) {
                return false;
            } else {
                return true;
            }

        }

    },
    /**
	 * 
	 */
    BITTORRENT("BitTorrent") {
        Composite composite;
        Label label;
        private Group featherGroup;
        private Group encrytionGroup;
        private Button check1;
        private Button check2;
        private Button check3;
        private Button check4;
        private Button check5;
        private Button check6;
        private Button check7;
        private Label label1;
        private Label label2;
        private Combo combo;
        private Text text;

        @Override
        public void createOptionComposite(String optionName,
                Composite parentComposite) {
            composite = parentComposite;
            RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
            rowLayout.marginWidth = 8;
            rowLayout.marginHeight = 8;
            rowLayout.spacing = 5;
            composite.setLayout(rowLayout);
            label = createOptionNameLabel(optionName, composite);
            createFeatureGroup();
            createEncrytionGroup();
        }

        private void createFeatureGroup() {
            featherGroup = new Group(composite, SWT.NONE);
            featherGroup.setText("Basic BitTorrent Feather");
            RowData rowData = new RowData(440, 105);
            featherGroup.setLayoutData(rowData);

            check1 = new Button(featherGroup, SWT.CHECK);
            check1.setText("Enable DHT Network");
            check1.setBounds(10, 15, 200, 20);
            check1.setSelection(true);

            check2 = new Button(featherGroup, SWT.CHECK);
            check2.setText("Ask tracker for scrape information");
            check2.setBounds(220, 15, 230, 20);
            check2.setSelection(true);

            check3 = new Button(featherGroup, SWT.CHECK);
            check3.setText("Enable DHT for new torrents");
            check3.setBounds(10, 40, 200, 20);
            check3.setSelection(true);

            check4 = new Button(featherGroup, SWT.CHECK);
            check4.setText("Enable Peer Exchange");
            check4.setBounds(220, 40, 200, 20);
            check4.setSelection(true);

            check5 = new Button(featherGroup, SWT.CHECK);
            check5.setText("Enable local Peer discovery");
            check5.setBounds(10, 65, 200, 20);
            check5.setSelection(true);

            check6 = new Button(featherGroup, SWT.CHECK);
            check6.setText("Limit local peer bandwidth");
            check6.setBounds(220, 65, 200, 20);

            label1 = new Label(featherGroup, SWT.NONE);
            label1.setText("IP/Hostname to report to tracker:");
            label1.setBounds(10, 90, 200, 20);

            text = new Text(featherGroup, SWT.BORDER);
            text.setBounds(220, 90, 200, 20);
        }

        private void createEncrytionGroup() {
            encrytionGroup = new Group(composite, SWT.NONE);
            encrytionGroup.setText("Protocal Encrytion");
            RowData rowData = new RowData(440, 30);
            encrytionGroup.setLayoutData(rowData);

            label2 = new Label(encrytionGroup, SWT.NONE);
            label2.setText("Outgoing:");
            label2.setBounds(10, 20, 60, 20);

            combo = new Combo(encrytionGroup, SWT.READ_ONLY);
            combo.add("Disable");
            combo.add("Enabled");
            combo.add("Forced");
            combo.setSelection(new Point(0, 7));
            combo.setBounds(70, 15, 120, 20);
            combo.select(0);

            check7 = new Button(encrytionGroup, SWT.CHECK);
            check7.setText("Allow incoming legacy connections");
            check7.setBounds(200, 15, 220, 20);

        }

        @Override
        public void dispose() {
            if (isExist()) {
                label.dispose();
                featherGroup.dispose();
                encrytionGroup.dispose();

            }
        }

        @Override
        public boolean isExist() {
            if (label == null) {
                return false;
            } else if (label.isDisposed()) {
                return false;
            } else {
                return true;
            }
        }

    };
    private String optionName;

    private PerferenceOptionEnum(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }

    private static Label createOptionNameLabel(String optionName,
            Composite composite) {
        Label label = new Label(composite, SWT.BORDER);
        label.setText(optionName);
        label.setFont(new Font(composite.getDisplay(), "font", SWT.NORMAL, 20));
        label.setBackground(new Color(composite.getDisplay(), 164, 246, 180));
        RowData rowData = new RowData(445, 25);
        label.setLayoutData(rowData);
        return label;
    }

    public abstract void createOptionComposite(String optionName,
            Composite parentComposite);

    public abstract void dispose();

    public abstract boolean isExist();

}
