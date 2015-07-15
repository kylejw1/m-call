package com.kylejw.marketcall.server.guests;

import java.util.List;

import com.kylejw.marketcall.shared.model.Guest;

public interface IGuestMiner {
    List<Guest> getGuests();
}
