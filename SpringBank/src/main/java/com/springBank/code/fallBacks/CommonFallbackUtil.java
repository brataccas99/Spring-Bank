package com.springBank.code.fallBacks;

import com.springBank.code.DTO.RisultatoDTO;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component
public class CommonFallbackUtil {

    public ResponseEntity<RisultatoDTO<Long>> insertFallback(Throwable throwable) {
        RisultatoDTO<Long> fallbackResponse = new RisultatoDTO<>();
        fallbackResponse.error("Service is unavailable, please try again later.", HttpStatus.SC_SERVICE_UNAVAILABLE);
        return ResponseEntity.status(HttpStatus.SC_SERVICE_UNAVAILABLE).body(fallbackResponse);
    }

    public ResponseEntity<RisultatoDTO<Long>> deleteFallback(Throwable throwable) {
        RisultatoDTO<Long> fallbackResponse = new RisultatoDTO<>();
        fallbackResponse.error("Failed to delete user transaction.", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(fallbackResponse);
    }

    public ResponseEntity<RisultatoDTO<Long>> searchFallback(Throwable throwable) {
        RisultatoDTO<Long> fallbackResponse = new RisultatoDTO<>();
        fallbackResponse.error("Service is unavailable, please try again later.", HttpStatus.SC_SERVICE_UNAVAILABLE);
        return ResponseEntity.status(HttpStatus.SC_SERVICE_UNAVAILABLE).body(fallbackResponse);
    }

    public ResponseEntity<RisultatoDTO<Long>> updateFallback(Throwable throwable) {
        RisultatoDTO<Long> fallbackResponse = new RisultatoDTO<>();
        fallbackResponse.error("Failed to update user transaction.", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(fallbackResponse);
    }
    public ResponseEntity<RisultatoDTO<Double>> exchangeFallback(Throwable throwable) {
        RisultatoDTO<Double> fallbackResponse = new RisultatoDTO<>();
        fallbackResponse.error("Service is unavailable, please try again later.", HttpStatus.SC_SERVICE_UNAVAILABLE);
        return ResponseEntity.status(HttpStatus.SC_SERVICE_UNAVAILABLE).body(fallbackResponse);
    }

}

