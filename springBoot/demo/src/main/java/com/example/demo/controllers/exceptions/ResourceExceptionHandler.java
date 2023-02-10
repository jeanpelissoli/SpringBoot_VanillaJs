package com.example.demo.controllers.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//classe para fazer o tratamento de erros
//A ideia é ver no console o erro que tá dando
//e baseado nisso fazer o tratamento de exceção adequado


@ControllerAdvice
public class ResourceExceptionHandler {
    
    //tratando erro do metodo put pra objeto nao encontrado
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {

        //.status já vem com as respostas http pra usar
        //poderiamos retornar o que está abaixo mas podemos especificar mais o erro
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("erro");

                                                            //pegando a mensagem do erro
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    //tratando erro pra requisição delete de objeto nao encontrado
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<String> mptyResultDataAccessException(EmptyResultDataAccessException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    //tratando erro pra quando tenta deleta um cliente que tem um atributo da classe endereço integrado
    //primeiro é preciso excluir o endereço
    //na verdade excluindo direto o endereço ele já exclui o endereço la classe cliente tbm
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
