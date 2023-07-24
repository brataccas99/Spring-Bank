package com.springBank.code.REST.AccountREST;

import com.springBank.code.DTO.Account.DeleteAccountRequestDTO;
import com.springBank.code.DTO.Account.InsertAccountRequestDTO;
import com.springBank.code.DTO.Account.UpdateAccountRequestDTO;
import com.springBank.code.DTO.RisultatoDTO;
import com.springBank.code.REST.UsersREST.userREST;
import com.springBank.code.Services.AccountService.AccountService;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class accountREST {
    @Autowired
    AccountService accountService;

    private static final Logger logger = LogManager.getLogger(userREST.class.getName());

    @PostMapping(value = "/insertAccount",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RisultatoDTO<Long>> insertAccount(@RequestBody InsertAccountRequestDTO accountDTO){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            Long result=accountService.insertAccount(accountDTO);

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

    @DeleteMapping(value = "/deleteAccount")
    public ResponseEntity<RisultatoDTO<Long>> deleteAccount(@RequestBody DeleteAccountRequestDTO accountDTO){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            accountService.deleteAccount(accountDTO);
            if(risultatoDTO.isSuccess()){
                risultatoDTO.success(HttpStatus.SC_OK);
                risultatoDTO.setDescrizione("hai fatto bello");
        }else{
                risultatoDTO.setDescrizione("impossibile eliminare l'utenza");
                risultatoDTO.success(HttpStatus.SC_OK);
                return ResponseEntity.status(HttpStatus.SC_OK).body(risultatoDTO);
            }
            risultatoDTO.error("err" , HttpStatus.SC_BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        }catch(Exception e){
            logger.info("errore"+ e);
            risultatoDTO.error("err" , HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        }
    }

    @PutMapping(value = "/updateAccount")
    public ResponseEntity<RisultatoDTO<Long>> updateAccount(@RequestBody UpdateAccountRequestDTO accountDTO){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            Long result=accountService.updateAccount(accountDTO);
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

    @GetMapping(value = "/getAccount")
    public ResponseEntity<RisultatoDTO<Long>> getAccount(@RequestParam("id utente") Long id){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            Long result=accountService.searchAccountById(id);
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
