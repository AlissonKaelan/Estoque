/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Alisson Kaelan
 */
public class Validador extends Cadastro{


    // Método para validar a data de nascimento
    public static boolean validarDataNascimento(String data_nascimento) {
        try {
            LocalDate data = LocalDate.parse(data_nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate hoje = LocalDate.now();
            // Verifica se a data não é futura e se a pessoa é maior de idade
            return !data.isAfter(hoje) && data.isBefore(hoje.minusYears(18));
        } catch (DateTimeParseException e) {
            return false; // Data inválida
        }
    }

    // Método para validar CPF
    public static boolean validarCPF(String cpf) {
        // Remove caracteres de formatação
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o tamanho do CPF é exatamente 11 caracteres
        if (cpf.length() != 11) {
            return false;
        }

        // Elimina CPFs inválidos conhecidos
        if (cpf.matches("^(0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|8{11}|9{11})$")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int add = 0;
        for (int i = 0; i < 9; i++) {
            add += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int rev = 11 - (add % 11);
        if (rev == 10 || rev == 11) {
            rev = 0;
        }

        // Verifica o primeiro dígito verificador
        if (rev != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcula o segundo dígito verificador
        add = 0;
        for (int i = 0; i < 10; i++) {
            add += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        rev = 11 - (add % 11);
        if (rev == 10 || rev == 11) {
            rev = 0;
        }

        // Verifica o segundo dígito verificador
        return rev == Character.getNumericValue(cpf.charAt(10));
    }
    
 }
 
    

