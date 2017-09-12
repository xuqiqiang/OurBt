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
     * ����������
     * 
     * @param childSashForm
     *            ���ڷָ���
     */
    private void createTorrentTable(SashForm childSashForm) {
        torrentTable = new Table(childSashForm, SWT.FULL_SELECTION);
        torrentTable.setLayout(new RowLayout(SWT.VERTICAL));
        torrentTable.setHeaderVisible(true);
        torrentTable.setLinesVisible(true);
        TableColumn nameColumn = new TableColumn(torrentTable, SWT.BORDER);
        nameColumn.setText("�ļ���");
        nameColumn.setWidth(150);

        TableColumn sizeColumn = new TableColumn(torrentTable, SWT.BORDER);
        sizeColumn.setText("��С");
        sizeColumn.setWidth(100);

        TableColumn doneColumn = new TableColumn(torrentTable, SWT.BORDER);
        doneColumn.setText("�����");
        doneColumn.setWidth(100);

        TableColumn downloadedColumn = new TableColumn(torrentTable, SWT.BORDER);
        downloadedColumn.setText("������");
        downloadedColumn.setWidth(100);

        TableColumn statusColumn = new TableColumn(torrentTable, SWT.BORDER);
        statusColumn.setText("״̬");
        statusColumn.setWidth(80);

        TableColumn seedColumn = new TableColumn(torrentTable, SWT.BORDER);
        seedColumn.setText("����");
        seedColumn.setWidth(100);

        TableColumn peerColumn = new TableColumn(torrentTable, SWT.BORDER);
        peerColumn.setText("�û�");
        peerColumn.setWidth(80);

        TableColumn downSpeedColumn = new TableColumn(torrentTable, SWT.BORDER);
        downSpeedColumn.setText("�����ٶ�");
        downSpeedColumn.setWidth(100);

        TableColumn upSpeedColumn = new TableColumn(torrentTable, SWT.BORDER);
        upSpeedColumn.setText("�ϴ��ٶ�");
        upSpeedColumn.setWidth(100);

        TableColumn upLoadColumn = new TableColumn(torrentTable, SWT.BORDER);
        upLoadColumn.setText("���ϴ�");
        upLoadColumn.setWidth(100);

        TableColumn addTimeColumn = new TableColumn(torrentTable, SWT.BORDER);
        addTimeColumn.setText("���ʱ��");
        addTimeColumn.setWidth(100);

    }

    /**
     * ����������ɾ�����������ʱ�������������ʹ����е���Ϣ��ʱ�õ�����
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

        // ��ȡȫ��������

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
     * ���±��ʱ������������������е������У������ϵļ�����
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
     * �ڲ��࣬ʵ�ּ����������ص���Ϣ���ص�������
     */
    class ShowTableItem implements DownloadManagerListener,
            DownloadManagerPeerListener, DownloadManagerPieceListener {
        private TableItem item;
        private DownloadManager downloadManager;

        public ShowTableItem(DownloadManager downloadManager) {
            this.downloadManager = downloadManager;
        }

        /**
         * ����������һ��
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

            // ���ô�С
            item.setText(1, String.valueOf(size < 1024 * 1024 * 1024 ? Math
                    .round((float) size / 1024 / 1024 * 100.0) / 100.0 + "M"
                    : Math.round((float) size / 1024 / 1024 / 1024 * 100.0)
                            / 100.0 + "G"));
            item.setText(9,
                    String.valueOf(new Date(downloadManager.getCreationTime())));

            // ������
            item.setText(6, "0");

            // �û���
            item.setText(5, "0");

            // �����
            item.setText(2, DisplayFormatters
                    .formatPercentFromThousands(downloadManager.getStats()
                            .getCompleted()));

            // ��������
            item.setText(3, DisplayFormatters
                    .formatByteCountToKiBEtc(downloadManager.getStats()
                            .getTotalDataBytesReceived()));
            // �����ٶ�
            item.setText(7, "0 B/s");
            // �ϴ��ٶ�
            item.setText(8, "0 B/s");
            // ���ϴ�
            item.setText(9, DisplayFormatters
                    .formatByteCountToKiBEtc(downloadManager.getStats()
                            .getTotalDataBytesSent()));
        }

        /**
         * �������ӵ�DownloadManager�ļ�������
         */
        public void addListener() {
            downloadManager.addListener(this);
            downloadManager.addPeerListener(this);
            downloadManager.addPieceListener(this);
        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void completionChanged(DownloadManager manager, boolean completed) {

        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void downloadComplete(DownloadManager manager) {

        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void filePriorityChanged(DownloadManager download,
                DiskManagerFileInfo file) {
        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void positionChanged(DownloadManager download, int oldPosition,
                int newPosition) {
        }

        /**
         * DownloadManagerListener��ʵ��,״̬�ı�ļ���
         */
        public void stateChanged(DownloadManager manager, int state) {
            downloadManager = manager;
            Utils.execSWTThread(new AERunnable() {
                public void runSupport() {
                    if (downloadManager.getState() == DownloadManager.STATE_DOWNLOADING) {
                        item.setText(4, "��������");
                    } else if (downloadManager.getState() == DownloadManager.STATE_FINISHING) {
                        item.setText(4, "���");
                    } else if (downloadManager.getState() == DownloadManager.STATE_SEEDING) {
                        item.setText(4, "����");
                    } else if (downloadManager.getState() == DownloadManager.STATE_STOPPED) {
                        item.setText(4, "ֹͣ");
                    } else if (downloadManager.getState() == DownloadManager.STATE_STOPPING) {
                        item.setText(4, "��ͣ");
                    } else if (downloadManager.getState() == DownloadManager.STATE_QUEUED) {
                        item.setText(4, "����");
                    } else if (downloadManager.getState() == DownloadManager.STATE_WAITING) {
                        item.setText(4, "�ȴ�");
                    } else if (downloadManager.getState() == DownloadManager.STATE_READY) {
                        item.setText(4, "׼��");
                    } else if (downloadManager.getState() == DownloadManager.STATE_CHECKING) {
                        item.setText(4, "���");
                    } else {
                        item.setText(4,
                                String.valueOf(downloadManager.getState()));
                    }
                }
            });

        }

        /**
         * DownloadManagerPeerListener������ʵ�֣�peer����ʱ�ļ���
         */
        public void peerAdded(PEPeer peer) {
            // Ҫ��SWT���߳�ͬ��
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

                    // �ڵ���
                    item.setText(6, String.valueOf(lTotalSeeds));

                    // ������
                    item.setText(5, String.valueOf(lConnectedSeeds));

                }
            });
        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void peerManagerAdded(PEPeerManager manager) {

        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void peerManagerRemoved(PEPeerManager manager) {

        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void peerManagerWillBeAdded(PEPeerManager manager) {

        }

        /**
         * DownloadManagerListener��ʵ��
         */
        public void peerRemoved(PEPeer peer) {
            // Ҫ��SWT���߳�ͬ��
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

                    // �ڵ���
                    item.setText(6, String.valueOf(lTotalSeeds));

                    // ������
                    item.setText(5, String.valueOf(lConnectedSeeds));

                }
            });

        }

        /**
         * DownloadManagerPieceListener��ʵ�֣����ӿ�ʱ�ļ�����������ʣ������ٶȣ��ϴ��ٶ� ��ӳ�ڽ�����
         */
        public void pieceAdded(PEPiece piece) {
            Utils.execSWTThread(new AERunnable() {
                public void runSupport() {
                    // �����
                    item.setText(2, DisplayFormatters
                            .formatPercentFromThousands(downloadManager
                                    .getStats().getCompleted()));
                    // ��������
                    item.setText(3, DisplayFormatters
                            .formatByteCountToKiBEtc(downloadManager.getStats()
                                    .getTotalDataBytesReceived()));
                    // �����ٶ�
                    item.setText(7, DisplayFormatters
                            .formatByteCountToKiBEtcPerSec(downloadManager
                                    .getStats().getDataReceiveRate()));
                    // �ϴ��ٶ�
                    item.setText(8, DisplayFormatters
                            .formatByteCountToKiBEtcPerSec(downloadManager
                                    .getStats().getDataSendRate()));
                    // ���ϴ�
                    item.setText(9, DisplayFormatters
                            .formatByteCountToKiBEtc(downloadManager.getStats()
                                    .getTotalDataBytesSent()));
                }
            });

        }

        /**
         * DownloadManagerPieceListener��ʵ��
         */
        public void pieceRemoved(PEPiece piece) {

        }

    }

}
