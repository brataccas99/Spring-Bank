package com.springBank.code.REST.UsersREST;

import com.springBank.code.DTO.RisultatoDTO;
import com.springBank.code.DTO.User.DeleteUserRequestDTO;
import com.springBank.code.DTO.User.InsertUserRequestDTO;
import com.springBank.code.DTO.User.UpdateUserRequestDTO;
import com.springBank.code.Services.UserServices.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class userREST {

    @Autowired
    UserService userService;
    private static final Logger logger = LogManager.getLogger(userREST.class.getName());

    @PostMapping(value = "/insertUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RisultatoDTO<Long>> insertUser(@RequestBody InsertUserRequestDTO userDTO){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            Long result=userService.insertUser(userDTO);

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

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<RisultatoDTO<Long>> deleteUser(@RequestBody DeleteUserRequestDTO userDTO){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            userService.deleteUser(userDTO);
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

    }catch (Exception e){
            logger.info("errore"+ e);
            risultatoDTO.error("err" , HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(risultatoDTO);
        }
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity<RisultatoDTO<Long>> updateUser(@RequestBody UpdateUserRequestDTO userDTO){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            Long result=userService.updateUser(userDTO);

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

    @GetMapping(value = "/getUser")
    public ResponseEntity<RisultatoDTO<Long>> getUser(@RequestParam("id utente") Long userDTO){
        RisultatoDTO<Long> risultatoDTO = new RisultatoDTO<>();
        try{
            Long result=userService.searchUser(userDTO);

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
