package com.bjtu.ourbt.ui;

import com.aelitis.azureus.core.AzureusCore;
import com.aelitis.azureus.core.AzureusCoreException;
import com.aelitis.azureus.core.impl.AzureusCoreImpl;

/**
 * ���棬�Ӻ�̨��ȡ��������ã��Ǻ�̨�ͽ���Ľӿ�
 */
public class OurBTCore {
    private static AzureusCore core;

    static {
        core = AzureusCoreImpl.create();
        core.start();
    }

    public static AzureusCore getSingleton() {
        if (core == null) {
            throw (new AzureusCoreException("Core Not instantiated"));
        }
        return core;
    }

}
