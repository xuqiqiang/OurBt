package com.bjtu.ourbt.ui;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.gudy.azureus2.core3.disk.DiskManagerFileInfo;
import org.gudy.azureus2.core3.download.DownloadManager;
import org.gudy.azureus2.core3.download.DownloadManagerListener;
import org.gudy.azureus2.core3.download.DownloadManagerPeerListener;
import org.gudy.azureus2.core3.download.DownloadManagerPieceListener;
import org.gudy.azureus2.core3.internat.LocaleTorrentUtil;
import org.gudy.azureus2.core3.internat.LocaleUtilDecoder;
import org.gudy.azureus2.core3.peer.PEPeer;
import org.gudy.azureus2.core3.peer.PEPeerManager;
import org.gudy.azureus2.core3.peer.PEPiece;
import org.gudy.azureus2.core3.torrent.TOTorrent;
import org.gudy.azureus2.core3.torrent.TOTorrentException;
import org.gudy.azureus2.core3.tracker.client.TRTrackerScraperResponse;
import org.gudy.azureus2.core3.util.AERunnable;
import org.gudy.azureus2.core3.util.DisplayFormatters;
import org.gudy.azureus2.ui.swt.Utils;

import com.aelitis.azureus.core.AzureusCore;
import com.aelitis.azureus.core.AzureusCoreException;
import com.aelitis.azureus.core.AzureusCoreFactory;

public class UITorrentTable {
    private SashForm sashForm;
    private AzureusCore core;

    private List<DownloadManager> list;
    private Table torrentTable;
    private TOTorrent torrent;

    public UITorrentTable(SashForm sashForm) {
        this.sashForm = sashForm;
        divide();
    }

    private void divide() {
        SashForm childSashForm = new SashForm(sashForm, SWT.VERTICAL);
        createTorrentTable(childSashForm);
        UITorrentInfo uiTorrentInfo = new UITorrentInfo(childSashForm);
        uiTorrentInfo.open();
        childSashForm.setWeights(new int[] { 65, 35 });
        childSashForm.setLayout(new FillLayout());
        refresh();
    }

    /**
     * 创建表格的列
     * 
     * @param childSashForm
     *            窗口分割器
     */
    private void createTorrentTable(SashForm childSashForm) {
        torrentTable = new Table(childSashForm, SWT.FULL_SELECTION);
        torrentTable.setLayout(new RowLayout(SWT.VERTICAL));
        torrentTable.setHeaderVisible(true);
        torrentTable.setLinesVisible(true);
        TableColumn nameColumn = new TableColumn(torrentTable, SWT.BORDER);
        nameColumn.setText("文件名");
        nameColumn.setWidth(150);

        TableColumn sizeColumn = new TableColumn(torrentTable, SWT.BORDER);
        sizeColumn.setText("大小");
        sizeColumn.setWidth(100);

        TableColumn doneColumn = new TableColumn(torrentTable, SWT.BORDER);
        doneColumn.setText("完成率");
        doneColumn.setWidth(100);

        TableColumn downloadedColumn = new TableColumn(torrentTable, SWT.BORDER);
        downloadedColumn.setText("已下载");
        downloadedColumn.setWidth(100);

        TableColumn statusColumn = new TableColumn(torrentTable, SWT.BORDER);
        statusColumn.setText("状态");
        statusColumn.setWidth(80);

        TableColumn seedColumn = new TableColumn(torrentTable, SWT.BORDER);
        seedColumn.setText("种子");
        seedColumn.setWidth(100);

        TableColumn peerColumn = new TableColumn(torrentTable, SWT.BORDER);
        peerColumn.setText("用户");
        peerColumn.setWidth(80);

        TableColumn downSpeedColumn = new TableColumn(torrentTable, SWT.BORDER);
        downSpeedColumn.setText("下载速度");
        downSpeedColumn.setWidth(100);

        TableColumn upSpeedColumn = new TableColumn(torrentTable, SWT.BORDER);
        upSpeedColumn.setText("上传速度");
        upSpeedColumn.setWidth(100);

        TableColumn upLoadColumn = new TableColumn(torrentTable, SWT.BORDER);
        upLoadColumn.setText("已上传");
        upLoadColumn.setWidth(100);

        TableColumn addTimeColumn = new TableColumn(torrentTable, SWT.BORDER);
        addTimeColumn.setText("添加时间");
        addTimeColumn.setWidth(100);

    }

    /**
     * 更新器，当删除或添加种子时调用这个方法，使表格中的信息及时得到更新
     */
    @SuppressWarnings("unchecked")
    public void refresh() {
        clearItems();
        try {
            core = AzureusCoreFactory.getSingleton();
        } catch (AzureusCoreException e) {
            e.printStackTrace();
        }
        if (core == null) {
            AzureusCoreFactory.create();
            core = AzureusCoreFactory.getSingleton();
            core.start();
        }

        // 获取全局下载器

        list = core.getGlobalManager().getDownloadManagers();

        DropDownMenu menu = new DropDownMenu(sashForm.getShell(), torrentTable);

        torrentTable.setMenu(menu.getMenu());

        torrentTable.getDisplay().asyncExec(new AERunnable() {

            @Override
            public void runSupport() {
                for (DownloadManager manager : list) {
                    new ShowTableItem(manager).addTableItem();
                }
            }

        });

        torrentTable.layout();
    }

    /**
     * 更新表格时调用这个方法清楚表格中的所有列，及列上的监听器
     */
    private void clearItems() {
        TableItem items[] = torrentTable.getItems();
        DownloadManager dm;
        ShowTableItem st;
        for (TableItem item : items) {
            dm = (DownloadManager) item.getData("manager");
            st = (ShowTableItem) item.getData("showTableItemObject");
            dm.removeListener(st);
            dm.removePeerListener(st);
            dm.removePieceListener(st);
            item.dispose();
        }
        torrentTable.clearAll();
        torrentTable.removeAll();
    }

    /**
     * 内部类，实现监听，把下载的信息加载到界面上
     */
    class ShowTableItem implements DownloadManagerListener,
            DownloadManagerPeerListener, DownloadManagerPieceListener {
        private TableItem item;
        private DownloadManager downloadManager;

        public ShowTableItem(DownloadManager downloadManager) {
            this.downloadManager = downloadManager;
        }

        /**
         * 向表格中增加一行
         */
        public void addTableItem() {

            torrent = downloadManager.getTorrent();

            item = new TableItem(torrentTable, SWT.BORDER);

            item.setData("manager", downloadManager);
            item.setData("showTableItemObject", this);

            addListener();
            LocaleUtilDecoder decoder;
            try {
                decoder = LocaleTorrentUtil
                        .getTorrentEncodingIfAvailable(torrent);
                if (decoder != null)
                    item.setText(0, decoder.decodeString(torrent.getName()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (TOTorrentException e) {
                e.printStackTrace();
            }

            long size = torrent.getSize();

            // 设置大小
            item.setText(1, String.valueOf(size < 1024 * 1024 * 1024 ? Math
                    .round((float) size / 1024 / 1024 * 100.0) / 100.0 + "M"
                    : Math.round((float) size / 1024 / 1024 / 1024 * 100.0)
                            / 100.0 + "G"));
            item.setText(9,
                    String.valueOf(new Date(downloadManager.getCreationTime())));

            // 种子数
            item.setText(6, "0");

            // 用户数
            item.setText(5, "0");

            // 完成率
            item.setText(2, DisplayFormatters
                    .formatPercentFromThousands(downloadManager.getStats()
                            .getCompleted()));

            // 已下载量
            item.setText(3, DisplayFormatters
                    .formatByteCountToKiBEtc(downloadManager.getStats()
                            .getTotalDataBytesReceived()));
            // 下载速度
            item.setText(7, "0 B/s");
            // 上传速度
            item.setText(8, "0 B/s");
            // 已上传
            item.setText(9, DisplayFormatters
                    .formatByteCountToKiBEtc(downloadManager.getStats()
                            .getTotalDataBytesSent()));
        }

        /**
         * 把这个类加到DownloadManager的监听器中
         */
        public void addListener() {
            downloadManager.addListener(this);
            downloadManager.addPeerListener(this);
            downloadManager.addPieceListener(this);
        }

        /**
         * DownloadManagerListener的实现
         */
        public void completionChanged(DownloadManager manager, boolean completed) {

        }

        /**
         * DownloadManagerListener的实现
         */
        public void downloadComplete(DownloadManager manager) {

        }

        /**
         * DownloadManagerListener的实现
         */
        public void filePriorityChanged(DownloadManager download,
                DiskManagerFileInfo file) {
        }

        /**
         * DownloadManagerListener的实现
         */
        public void positionChanged(DownloadManager download, int oldPosition,
                int newPosition) {
        }

        /**
         * DownloadManagerListener的实现,状态改变的监听
         */
        public void stateChanged(DownloadManager manager, int state) {
            downloadManager = manager;
            Utils.execSWTThread(new AERunnable() {
                public void runSupport() {
                    if (downloadManager.getState() == DownloadManager.STATE_DOWNLOADING) {
                        item.setText(4, "正在下载");
                    } else if (downloadManager.getState() == DownloadManager.STATE_FINISHING) {
                        item.setText(4, "完成");
                    } else if (downloadManager.getState() == DownloadManager.STATE_SEEDING) {
                        item.setText(4, "种子");
                    } else if (downloadManager.getState() == DownloadManager.STATE_STOPPED) {
                        item.setText(4, "停止");
                    } else if (downloadManager.getState() == DownloadManager.STATE_STOPPING) {
                        item.setText(4, "暂停");
                    } else if (downloadManager.getState() == DownloadManager.STATE_QUEUED) {
                        item.setText(4, "队列");
                    } else if (downloadManager.getState() == DownloadManager.STATE_WAITING) {
                        item.setText(4, "等待");
                    } else if (downloadManager.getState() == DownloadManager.STATE_READY) {
                        item.setText(4, "准备");
                    } else if (downloadManager.getState() == DownloadManager.STATE_CHECKING) {
                        item.setText(4, "检查");
                    } else {
                        item.setText(4,
                                String.valueOf(downloadManager.getState()));
                    }
                }
            });

        }

        /**
         * DownloadManagerPeerListener监听的实现，peer增加时的监听
         */
        public void peerAdded(PEPeer peer) {
            // 要和SWT的线程同步
            Utils.execSWTThread(new AERunnable() {
                public void runSupport() {
                    long lTotalPeers = 0;
                    long lTotalSeeds = -1;
                    long lConnectedSeeds = 0;
                    if (downloadManager != null) {
                        lConnectedSeeds = downloadManager.getNbSeeds();

                        if (lTotalSeeds == -1) {
                            TRTrackerScraperResponse response = downloadManager
                                    .getTrackerScrapeResponse();
                            if (response != null && response.isValid()) {
                                lTotalSeeds = response.getSeeds();
                                lTotalPeers = response.getPeers();
                            }
                        }
                    }

                    long value = (lConnectedSeeds << 42);
                    if (lTotalSeeds > 0)
                        value += (lTotalSeeds << 21);
                    if (lTotalPeers > 0)
                        value += lTotalPeers;

                    String tmp = String.valueOf(lConnectedSeeds);
                    if (lTotalSeeds != -1) {
                        tmp += " (" + lTotalSeeds;

                        long lSeedsToAdd = lTotalPeers;
                        if (lSeedsToAdd > 0)
                            tmp += "+" + lSeedsToAdd;
                        tmp += ")";
                    }

                    // 节点数
                    item.setText(6, String.valueOf(lTotalSeeds));

                    // 种子数
                    item.setText(5, String.valueOf(lConnectedSeeds));

                }
            });
        }

        /**
         * DownloadManagerListener的实现
         */
        public void peerManagerAdded(PEPeerManager manager) {

        }

        /**
         * DownloadManagerListener的实现
         */
        public void peerManagerRemoved(PEPeerManager manager) {

        }

        /**
         * DownloadManagerListener的实现
         */
        public void peerManagerWillBeAdded(PEPeerManager manager) {

        }

        /**
         * DownloadManagerListener的实现
         */
        public void peerRemoved(PEPeer peer) {
            // 要和SWT的线程同步
            Utils.execSWTThread(new AERunnable() {
                public void runSupport() {
                    long lTotalPeers = 0;
                    long lTotalSeeds = -1;
                    long lConnectedSeeds = 0;
                    if (downloadManager != null) {
                        lConnectedSeeds = downloadManager.getNbSeeds();

                        if (lTotalSeeds == -1) {
                            TRTrackerScraperResponse response = downloadManager
                                    .getTrackerScrapeResponse();
                            if (response != null && response.isValid()) {
                                lTotalSeeds = response.getSeeds();
                                lTotalPeers = response.getPeers();
                            }
                        }
                    }

                    long value = (lConnectedSeeds << 42);
                    if (lTotalSeeds > 0)
                        value += (lTotalSeeds << 21);
                    if (lTotalPeers > 0)
                        value += lTotalPeers;

                    String tmp = String.valueOf(lConnectedSeeds);
                    if (lTotalSeeds != -1) {
                        tmp += " (" + lTotalSeeds;

                        long lSeedsToAdd = lTotalPeers;
                        if (lSeedsToAdd > 0)
                            tmp += "+" + lSeedsToAdd;
                        tmp += ")";
                    }

                    // 节点数
                    item.setText(6, String.valueOf(lTotalSeeds));

                    // 种子数
                    item.setText(5, String.valueOf(lConnectedSeeds));

                }
            });

        }

        /**
         * DownloadManagerPieceListener的实现，增加块时的监听，把完成率，下载速度，上传速度 反映在界面上
         */
        public void pieceAdded(PEPiece piece) {
            Utils.execSWTThread(new AERunnable() {
                public void runSupport() {
                    // 完成率
                    item.setText(2, DisplayFormatters
                            .formatPercentFromThousands(downloadManager
                                    .getStats().getCompleted()));
                    // 已下载量
                    item.setText(3, DisplayFormatters
                            .formatByteCountToKiBEtc(downloadManager.getStats()
                                    .getTotalDataBytesReceived()));
                    // 下载速度
                    item.setText(7, DisplayFormatters
                            .formatByteCountToKiBEtcPerSec(downloadManager
                                    .getStats().getDataReceiveRate()));
                    // 上传速度
                    item.setText(8, DisplayFormatters
                            .formatByteCountToKiBEtcPerSec(downloadManager
                                    .getStats().getDataSendRate()));
                    // 已上传
                    item.setText(9, DisplayFormatters
                            .formatByteCountToKiBEtc(downloadManager.getStats()
                                    .getTotalDataBytesSent()));
                }
            });

        }

        /**
         * DownloadManagerPieceListener的实现
         */
        public void pieceRemoved(PEPiece piece) {

        }

    }

}
