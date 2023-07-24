package com.springBank.code.REST.CurrencyExchange;

import com.springBank.code.DTO.CurrencyExchange.CurrencyExchangeRequestDTO;
import com.springBank.code.DTO.RisultatoDTO;
import com.springBank.code.Services.CurrencyServices.CurrencyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyExchangeREST {

    @Autowired
    private CurrencyService currencyService;

    private static final Logger logger = LogManager.getLogger(CurrencyExchangeREST.class.getName());

    @PostMapping(value = "/exchange_EUR_USD", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RisultatoDTO<Double>> exchangeCurrency(@RequestBody CurrencyExchangeRequestDTO requestDTO) {
        RisultatoDTO<Double> risultatoDTO = new RisultatoDTO<>();
        try {
            Double exchangedAmount = currencyService.exchangeCurrency(requestDTO.getSourceCurrency(),
                    requestDTO.getTargetCurrency(), requestDTO.getAmount());

            if (!ObjectUtils.isEmpty(exchangedAmount)) {
                risultatoDTO.success(HttpStatus.SC_OK);
                risultatoDTO.setData(exchangedAmount);
                risultatoDTO.setDescrizione("Currency exchanged successfully.");
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            }

            risultatoDTO.error("Failed to exchange currency.", HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        } catch (Exception e) {
            logger.info("Error: " + e);
            risultatoDTO.error("An error occurred while processing the request.", HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        }
    }
}
