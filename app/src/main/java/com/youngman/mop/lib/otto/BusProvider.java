package com.youngman.mop.lib.otto;

import com.squareup.otto.Bus;

/**
 * Created by YoungMan on 2019-07-08.
 */

public final class BusProvider extends Bus {

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
    }
}
