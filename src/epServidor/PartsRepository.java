/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epServidor;

import java.util.LinkedList;

public class PartsRepository {
    LinkedList<Part> partes = new LinkedList<>();

    void listar() {
        for (int i = 0; i < partes.size(); i++){
            System.out.println(partes.get(i).nome);
        }
    }
    
}
