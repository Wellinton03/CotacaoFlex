/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.wellinton.cotacao.user;

/**
 *
 * @author welli
 */
public record RegisterDTO(String username, String password, UserRole role, String email) {

}
