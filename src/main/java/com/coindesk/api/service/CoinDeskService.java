package com.coindesk.api.service;

import com.coindesk.api.model.CurrencyUpdate;

import java.util.Optional;

public interface CoinDeskService {

    Optional<CurrencyUpdate> getCurrencyUpdate();
}
