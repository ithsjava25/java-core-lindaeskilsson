package com.example;

import java.time.LocalDate;


 //Interface för objekt som har ett bäst-före-datum.
 //Klassen som implementerar detta måste ange vilket datum som gäller.

public interface Perishable {


     //Returnerar produktens bäst-före-datum.
    LocalDate expirationDate();


     //Kollar om produkten har passerat sitt bäst-före-datum.
     // @return true om bäst-före-datumet är innan dagens datum.

    default boolean isPastExpirationDate() {
        LocalDate today = LocalDate.now(); // dagens datum
        return expirationDate().isBefore(today);
    }
}
