package com.coindesk.api.service;

import com.coindesk.api.model.CurrencyUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class CoinDeskServiceImpl implements CoinDeskService {

    @Override
    public Optional<CurrencyUpdate> getCurrencyUpdate() {
        try {
            String url = "https://api.coindesk.com/v1/bpi/currentprice/GBP";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            log.info("resp: {}", exchange.getBody());

            ObjectMapper mapper = new ObjectMapper();
            CurrencyUpdate currencyUpdate = mapper.readValue(exchange.getBody(), CurrencyUpdate.class);
            log.info("currencyUpdate: {}", currencyUpdate);
            return Optional.of(currencyUpdate);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
