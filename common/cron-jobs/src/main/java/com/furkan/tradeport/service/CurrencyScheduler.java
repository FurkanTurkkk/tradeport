package com.furkan.tradeport.service;

import com.furkan.tradeport.dto.CurrencyResponse;
import com.furkan.tradeport.model.Currency;
import com.furkan.tradeport.repository.CurrencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class CurrencyScheduler {

    private final CurrencyRepository repository;

    public CurrencyScheduler(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 8 * * ?") // Saat 08.00
    @Transactional
    public void fetchAndSaveCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());

        CurrencyResponse response = restTemplate.getForObject("https://www.tcmb.gov.tr/kurlar/today.xml", CurrencyResponse.class);

        response.getCurrencies().forEach(c -> {
            Currency currency = repository.findByCode(c.getCode())
                            .orElse(new Currency());
            currency.setCode(c.getCode());
            currency.setBuying(c.getBuying());
            currency.setSelling(c.getSelling());
            currency.setFetchedAt(LocalDateTime.now());
            repository.save(currency);
        });
    }
}
