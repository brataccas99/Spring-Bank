package com.springBank.code.REST.UsersREST;

import com.springBank.code.DTO.RisultatoDTO;
import com.springBank.code.DTO.User.DeleteUserRequestDTO;
import com.springBank.code.DTO.User.InsertUserRequestDTO;
import com.springBank.code.DTO.User.UpdateUserRequestDTO;
import com.springBank.code.Services.UserServices.UserService;
import com.springBank.code.fallBacks.CommonFallbackUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/user")
public class userREST {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFallbackUtil fallbackUtil;

    private static final String CIRCUITBREAKER = "restBreaker";
    private static final String TIMELIMITER = "restTimeLimiter";
    private static final String BULKHEAD = "restBulk";

    private static final Logger logger = LogManager.getLogger(userREST.class.getName());

    // Insert endpoint with CompletableFuture and fallback
    @PostMapping(value = "/insertUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> insertUser(@RequestBody InsertUserRequestDTO userDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                Long result = userService.insertUser(userDTO);
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
            logger.error("Exception occurred in insertUser: " + ex.getMessage());
            return fallbackUtil.insertFallback(ex);
        });
    }

    // Delete endpoint with CompletableFuture and fallback
    @DeleteMapping(value = "/deleteUser")
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> deleteUser(@RequestBody DeleteUserRequestDTO userDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                userService.deleteUser(userDTO);
                if (risultatoDTO.isSuccess()) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setDescrizione("Hai fatto bello");
                } else {
                    risultatoDTO.setDescrizione("Impossibile eliminare l'utenza");
                    risultatoDTO.success(HttpStatus.SC_OK);
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                risultatoDTO.error("Error", HttpStatus.SC_BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Errore: " + e);
                risultatoDTO.error("Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in deleteUser: " + ex.getMessage());
            return fallbackUtil.deleteFallback(ex);
        });
    }

    // Update endpoint with CompletableFuture and fallback
    @PutMapping(value = "/updateUser")
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> updateUser(@RequestBody UpdateUserRequestDTO userDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                Long result = userService.updateUser(userDTO);
                if (!ObjectUtils.isEmpty(result)) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setData(result);
                    risultatoDTO.setDescrizione("Successo");
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Errore: " + e);
                risultatoDTO.error("Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in updateUser: " + ex.getMessage());
            return fallbackUtil.updateFallback(ex);
        });
    }

    // Search endpoint with CompletableFuture and fallback
    @GetMapping(value = "/getUser")
    public CompletableFuture<ResponseEntity<RisultatoDTO<Long>>> getUser(@RequestParam("id utente") Long userDTO) {
        return CompletableFuture.supplyAsync(() -> {
            RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
            try {
                Long result = userService.searchUser(userDTO);
                if (!ObjectUtils.isEmpty(result)) {
                    risultatoDTO.success(HttpStatus.SC_OK);
                    risultatoDTO.setData(result);
                    risultatoDTO.setDescrizione("Successo");
                    return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
                }
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            } catch (Exception e) {
                logger.info("Errore: " + e);
                risultatoDTO.error("Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
            }
        }).exceptionally(ex -> {
            logger.error("Exception occurred in getUser: " + ex.getMessage());
            return fallbackUtil.searchFallback(ex);
        });
    }
}
