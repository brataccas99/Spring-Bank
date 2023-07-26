package com.springBank.code.REST.UserTransactionREST;

import com.springBank.code.DTO.RisultatoDTO;
import com.springBank.code.DTO.UserTransactions.DeleteUserTransactionRequestDTO;
import com.springBank.code.DTO.UserTransactions.InsertUserTransactionRequestDTO;
import com.springBank.code.Services.UserTransactionServices.UserTransactionService;
import com.springBank.code.fallBacks.CommonFallbackUtil;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping("/api/userTransaction")
public class userTransactionREST {

    @Autowired
    private CommonFallbackUtil fallbackUtil;

    @Autowired
    UserTransactionService userTransactionService;

    private static final String CIRCUITBREAKER = "restBreaker";
    private static final String TIMELIMITER = "restTimeLimiter";
    private static final String BULKHEAD = "restBulk";

    private static final Logger logger = LogManager.getLogger(userTransactionREST.class.getName());

    // Insert endpoint with CompletableFuture and fallback
    @PostMapping(value = "/insertUserTransaction")
    @TimeLimiter(name = TIMELIMITER, fallbackMethod = "fallbackInsertUserTransaction")
    @Bulkhead(name = BULKHEAD, fallbackMethod = "fallbackInsertUserTransaction", type = Bulkhead.Type.THREADPOOL)
    @CircuitBreaker(name = CIRCUITBREAKER, fallbackMethod = "fallbackInsertUserTransaction")
    @CrossOrigin
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> insertUserTransaction(@RequestBody InsertUserTransactionRequestDTO userTransactionDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            logger.info("Received request to insert user transaction: " + userTransactionDTO);

            try {
                Long result = userTransactionService.insertUserTransaction(userTransactionDTO);
                if (result != null && result > 0) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setData(result);
                    risultatoDTO.setDescrizione("User transaction inserted successfully.");
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                } else {
                    risultatoDTO.error("Failed to insert user transaction.", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
                }
            } catch (Exception e) {
                logger.error("Error occurred while inserting user transaction: " + e.getMessage());
                risultatoDTO.error("Error occurred while inserting user transaction.", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in insertUserTransaction: " + ex.getMessage());
            return fallbackUtil.insertFallback(ex);
        });
    }

    // Delete endpoint with CompletableFuture and fallback
    @DeleteMapping(value = "/deleteUserTransaction")
    @TimeLimiter(name = TIMELIMITER, fallbackMethod = "fallbackDeleteUserTransaction")
    @Bulkhead(name = BULKHEAD, fallbackMethod = "fallbackDeleteUserTransaction", type = Bulkhead.Type.THREADPOOL)
    @CircuitBreaker(name = CIRCUITBREAKER, fallbackMethod = "fallbackDeleteUserTransaction")
    @CrossOrigin
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> deleteUserTransaction(@RequestBody DeleteUserTransactionRequestDTO userTransactionDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                userTransactionService.deleteUserTransaction(userTransactionDTO);
                if (risultatoDTO.isSuccess()) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setDescrizione("Success");
                } else {
                    risultatoDTO.setDescrizione("Impossibile eliminare");
                    risultatoDTO.error("Impossibile eliminare",HttpStatus.SC_OK);
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                risultatoDTO.error("Error", HttpStatus.SC_BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Error: " + e);
                risultatoDTO.error("Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in deleteUserTransaction: " + ex.getMessage());
            return fallbackUtil.deleteFallback(ex);
        });
    }

    // Search endpoint with CompletableFuture and fallback
    @GetMapping(value = "/getUserTransaction")
    @TimeLimiter(name = TIMELIMITER, fallbackMethod = "fallbackGetUserTransaction")
    @Bulkhead(name = BULKHEAD, fallbackMethod = "fallbackGetUserTransaction", type =  Bulkhead.Type.THREADPOOL)
    @CircuitBreaker(name = CIRCUITBREAKER, fallbackMethod = "fallbackGetUserTransaction")
    @CrossOrigin
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> getUserTransaction(@RequestParam("id utente") Long userTransactionDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                Long result = userTransactionService.searchUserTransactionById(userTransactionDTO);
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
            logger.error("Exception occurred in getUserTransaction: " + ex.getMessage());
            return fallbackUtil.searchFallback(ex);
        });
    }
}
