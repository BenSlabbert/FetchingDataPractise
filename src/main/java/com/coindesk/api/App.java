package com.coindesk.api;

import com.coindesk.api.model.CurrencyUpdate;
import com.coindesk.api.service.CoinDeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {

    private final CoinDeskService coinDeskService;

    public App(CoinDeskService coinDeskService) {
        this.coinDeskService = coinDeskService;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App run method Started !!");
        Optional<CurrencyUpdate> currencyUpdate = coinDeskService.getCurrencyUpdate();

        if (currencyUpdate.isPresent()) {
            log.info("got currecny update! {}", currencyUpdate);
        } else {
            log.error("Failed to get update!");
        }

        System.exit(0);
    }
}
