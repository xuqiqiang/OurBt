package com.bjtu.ourbt.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.gudy.azureus2.core3.global.GlobalManager;
import org.gudy.azureus2.core3.util.AERunnable;
import org.gudy.azureus2.core3.util.AEThread2;
import org.gudy.azureus2.core3.util.Debug;
import org.gudy.azureus2.ui.swt.OpenTorrentWindow;
import org.gudy.azureus2.ui.swt.Utils;
import org.gudy.azureus2.ui.swt.mainwindow.TorrentOpener;

import com.aelitis.azureus.core.AzureusCore;
import com.aelitis.azureus.core.AzureusCoreComponent;
import com.aelitis.azureus.core.AzureusCoreException;
import com.aelitis.azureus.core.AzureusCoreFactory;
import com.aelitis.azureus.core.AzureusCoreLifecycleAdapter;
import com.aelitis.azureus.core.vuzefile.VuzeFile;
import com.aelitis.azureus.core.vuzefile.VuzeFileComponent;
import com.aelitis.azureus.core.vuzefile.VuzeFileHandler;
import com.aelitis.azureus.ui.swt.UIFunctionsSWT;

/**
 * 从默认位置添加种子
 */
public class AddTorrentDefaultDialog {
    private Shell shell;

    public AddTorrentDefaultDialog(Shell shell) {
        this.shell = shell;
    }

    public void open() {
        Utils.execSWTThread(new AERunnable() {
            public void runSupport() {
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN
                        | SWT.MULTI);
                fileDialog.setText("打开种子文件");
                fileDialog.setFilterNames(new String[] { "Torrents(*.torrent)",
                        "All(*.*)" });
                fileDialog.setFilterExtensions(new String[] { "*.torrent",
                        "*.*" });
                String pathName = TorrentOpener.setFilterPathTorrent(fileDialog
                        .open());
                if (fileDialog.getFileName() != null
                        && !fileDialog.getFileName().equals("")) {
                    openTorrentWindow(pathName, fileDialog.getFileNames(),
                            false);
                }

            }
        });

    }

    private static void openTorrentWindow(final String path,
            final String[] torrents, final boolean bOverrideStartModeToStopped) {
        new AEThread2("openTorrentWindow", true) {
            public void run() {
                _openTorrentWindow(path, torrents, bOverrideStartModeToStopped);
            }
        }.start();
    }

    private static void _openTorrentWindow(final String path,
            String[] torrents, final boolean bOverrideStartModeToStopped) {
        if (torrents != null && torrents.length > 0) {

            VuzeFileHandler vfh = VuzeFileHandler.getSingleton();

            List<String> non_vuze_files = new ArrayList<String>();
            List<VuzeFile> vuze_files = new ArrayList<VuzeFile>();

            for (int i = 0; i < torrents.length; i++) {

                String torrent = torrents[i];

                try {
                    VuzeFile vf = vfh.loadVuzeFile(torrent);

                    if (vf == null) {

                        non_vuze_files.add(torrent);

                    } else {

                        vuze_files.add(vf);
                    }
                } catch (Throwable e) {

                    Debug.printStackTrace(e);

                    non_vuze_files.add(torrent);
                }
            }

            if (vuze_files.size() > 0) {

                VuzeFile[] vfs = new VuzeFile[vuze_files.size()];

                vuze_files.toArray(vfs);

                vfh.handleFiles(vfs, VuzeFileComponent.COMP_TYPE_NONE);
            }

            if (non_vuze_files.size() == 0 && vuze_files.size() > 0) {

                return;
            }

            String[] t = new String[non_vuze_files.size()];

            non_vuze_files.toArray(t);

            torrents = t;
        }

        final String[] f_torrents = torrents;

        Utils.execSWTThread(new AERunnable() {
            public void runSupport() {
                Shell shell = Utils.findAnyShell();
                AzureusCore core = AzureusCoreFactory.getSingleton();
                GlobalManager gm = null;
                try {
                    gm = core.getGlobalManager();
                } catch (AzureusCoreException e) {
                }

                if (gm == null) {
                    core.addLifecycleListener(new AzureusCoreLifecycleAdapter() {
                        public void componentCreated(AzureusCore core,
                                AzureusCoreComponent component) {
                            if (component instanceof UIFunctionsSWT) {
                                openTorrentWindow(path, f_torrents,
                                        bOverrideStartModeToStopped);
                            }
                        }
                    });
                    return;
                }

                if (shell == null) {
                    Debug.out("openTorrentWindow().. no shell");
                    return;
                }

                OpenTorrentWindow.invoke(shell, gm, path, f_torrents,
                        bOverrideStartModeToStopped, false, false);
            }
        });

    }
}
