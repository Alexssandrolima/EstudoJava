/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomesobrenome;

/**
 *
 * @author alexssandrolima
 */
public class Pessoa {
    private String nome;
    private String sobrenome;
    private int idade;
    
    public void setNome(String t){
        nome = t;
    }
    public void setSobrenome(String t){
        sobrenome = t;    
    }
    public void setIdade(int i) {
        idade = i;        
    }
    public String getNome(){
        return nome;
    }
    public String getSobreNome(){
        return sobrenome;
    }
    public Integer getIdade(){
        return idade;
    }

    public String toString(){
        String msg = nome + " " + sobrenome + " " + idade;
        return msg;
    }
  
}
