package com.springBank.code.REST.UserTransactionREST;

import com.springBank.code.DTO.RisultatoDTO;
import com.springBank.code.DTO.UserTransactions.DeleteUserTransactionRequestDTO;
import com.springBank.code.DTO.UserTransactions.InsertUserTransactionRequestDTO;
import com.springBank.code.Services.UserTransactionServices.UserTransactionService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api/userTransaction")
public class userTransactionREST {

    @Autowired
    UserTransactionService userTransactionService;

    private static final Logger logger = Logger.getLogger(userTransactionREST.class.getName());

    @PostMapping(value = "/insertUserTransaction")
    public ResponseEntity<RisultatoDTO<Long>> insertUserTransaction(@RequestBody InsertUserTransactionRequestDTO userTransactionDTO) {
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try {
            Long result=userTransactionService.insertUserTransaction(userTransactionDTO);
            if(!ObjectUtils.isEmpty(result)) {
                risultatoDTO.success(HttpStatus.SC_OK);
                risultatoDTO.setData(result);
                risultatoDTO.setDescrizione("successo");
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);

            }
            return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
        } catch (Exception e) {
            logger.info("errore" + e);
            risultatoDTO.error("err", 500);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        }
    }

    @DeleteMapping(value = "/deleteUserTransaction")
    public ResponseEntity<RisultatoDTO<Long>> deleteUserTransaction(@RequestBody DeleteUserTransactionRequestDTO userTransactionDTO) {
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try {
            userTransactionService.deleteUserTransaction(userTransactionDTO);
            if (risultatoDTO.isSuccess()) {
                risultatoDTO.success(HttpStatus.SC_OK);
                risultatoDTO.setDescrizione("success");
            } else {
                risultatoDTO.setDescrizione("impossibile eliminare ");
                risultatoDTO.success(HttpStatus.SC_OK);
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            }
            risultatoDTO.error("err", HttpStatus.SC_BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        } catch (Exception e) {
            logger.info("errore" + e);
            risultatoDTO.error("err", HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        }
    }

    @GetMapping(value = "/getUserTransaction")
public ResponseEntity<RisultatoDTO<Long>> getUserTransaction(@RequestParam("id utente") Long userTransactionDTO) {
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try {
            Long result=userTransactionService.searchUserTransactionById(userTransactionDTO);
            if(!ObjectUtils.isEmpty(result)) {
                risultatoDTO.success(HttpStatus.SC_OK);
                risultatoDTO.setData(result);
                risultatoDTO.setDescrizione("successo");
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);

            }
            return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
        }catch(Exception e){
            logger.info("errore"+ e);
            risultatoDTO.error("err" , HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        }
    }

}
