package com.gmf.mc.shop.manager;

import com.gmf.mc.shop.event.MoneyEvent;

public interface MoneyEventListener {
    void listen(MoneyEvent event);
}
