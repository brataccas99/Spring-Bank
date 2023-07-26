package com.springBank.code.REST.AccountREST;

import com.springBank.code.DTO.Account.DeleteAccountRequestDTO;
import com.springBank.code.DTO.Account.InsertAccountRequestDTO;
import com.springBank.code.DTO.Account.UpdateAccountRequestDTO;
import com.springBank.code.DTO.RisultatoDTO;
import com.springBank.code.Services.AccountService.AccountService;
import com.springBank.code.fallBacks.CommonFallbackUtil;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/account")
public class accountREST {

    @Autowired
    private CommonFallbackUtil fallbackUtil;

    @Autowired
    AccountService accountService;

    private static final String CIRCUITBREAKER = "restBreaker";
    private static final String TIMELIMITER = "restTimeLimiter";
    private static final String BULKHEAD = "restBulk";

    private static final Logger logger = LogManager.getLogger(accountREST.class.getName());

    // Insert endpoint with CompletableFuture and fallback
    @PostMapping(value = "/insertAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> insertAccount(@RequestBody InsertAccountRequestDTO accountDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                Long result = accountService.insertAccount(accountDTO);
                if (!ObjectUtils.isEmpty(result)) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setData(result);
                    risultatoDTO.setDescrizione("Successo");
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Errore: " + e);
                risultatoDTO.error("Errore", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in insertAccount: " + ex.getMessage());
            return fallbackUtil.insertFallback(ex);
        });
    }

    // Delete endpoint with CompletableFuture and fallback
    @DeleteMapping(value = "/deleteAccount")
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> deleteAccount(@RequestBody DeleteAccountRequestDTO accountDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                accountService.deleteAccount(accountDTO);
                if (risultatoDTO.isSuccess()) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setDescrizione("Successo");
                } else {
                    risultatoDTO.setDescrizione("Impossibile eliminare l'utenza");
                    risultatoDTO.success(HttpStatus.SC_OK);
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                risultatoDTO.error("Errore", HttpStatus.SC_BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Errore: " + e);
                risultatoDTO.error("Errore", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in deleteAccount: " + ex.getMessage());
            return fallbackUtil.deleteFallback(ex);
        });
    }

    // Update endpoint with CompletableFuture and fallback
    @PutMapping(value = "/updateAccount")
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> updateAccount(@RequestBody UpdateAccountRequestDTO accountDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                Long result = accountService.updateAccount(accountDTO);
                if (!ObjectUtils.isEmpty(result)) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setData(result);
                    risultatoDTO.setDescrizione("Successo");
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Errore: " + e);
                risultatoDTO.error("Errore", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in updateAccount: " + ex.getMessage());
            return fallbackUtil.updateFallback(ex);
        });
    }

    // Search endpoint with CompletableFuture and fallback
    @GetMapping(value = "/getAccount")
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> getAccount(@RequestParam("id utente") Long id) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                Long result = accountService.searchAccountById(id);
                if (!ObjectUtils.isEmpty(result)) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setData(result);
                    risultatoDTO.setDescrizione("Successo");
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Errore: " + e);
                risultatoDTO.error("Errore", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in getAccount: " + ex.getMessage());
            return fallbackUtil.searchFallback(ex);
        });
    }
}
