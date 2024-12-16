/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.raven.model;

import com.raven.connection.DatabaseOperation;

/**
 *
 * @author MSI
 */
public class SistemaCine {

    static DatabaseOperation db = new DatabaseOperation();
    static Ticket t = new Ticket();
    static Funcion f = new Funcion();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        tri2(10);
        printUpperTriangle(10);
        //db.showAllTicketsByUser2(1);
        //f.showFuncionDetailsOG(1);
        System.out.println("----------------------------------------------- <  > -----------------------------------------------");
        //t.showAllTicketsByUserOG(1);
        //db.showFuncionDetails2(1);
    }

    static void tri(int num) {
        for (int a = 0; a < num; a++) {

            for (int i = a; i > 0; i--) {
                System.out.print(" *");
            }
            System.out.println("");
        }
    }

    static void tri2(int num) {
        for (int a = 0; a < num; a++) {
            // Print leading spaces to center the triangle
            for (int i = 0; i < num - a - 1; i++) {
                System.out.print(" ");
            }

            // Print stars in the current row
            for (int i = 0; i <= a; i++) {
                System.out.print("*");
            }

            System.out.println();  // Move to the next line after each row
        }
    }

    // Function to print the upper triangle pattern
    static void printUpperTriangle(int num) {
        for (int a = 1; a <= num; a++) {
            // Print leading spaces to center the triangle
            for (int i = 0; i < num - a; i++) {
                System.out.print(" ");
            }

            // Print stars in the current row
            for (int i = 0; i < a; i++) {
                System.out.print("* ");
            }

            System.out.println();  // Move to the next line after each row
        }
    }

    // Function to print the lower triangle pattern
    static void printLowerTriangle(int num) {
        for (int a = num; a >= 1; a--) {
            // Print leading spaces to center the triangle
            for (int i = 0; i < num - a; i++) {
                System.out.print(" ");
            }

            // Print stars in the current row
            for (int i = 0; i < a; i++) {
                System.out.print("* ");
            }

            System.out.println();  // Move to the next line after each row
        }
    }

}
