package com.example.produktapi.repository;

import com.example.produktapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //Används när vi vill enhetstesta vår repository.
class  ProductRepositoryTest  {

    @Autowired
    private  ProductRepository underTest;


    @Test
    void testingRepository(){
        List<Product> products = underTest.findAll();
        assertFalse(products.isEmpty());
    }



    @Test
    void testFindByCategory(){
        // Skapa en Product-objekt
        String category = "jewelery";
        Product product = new Product("title",33.33,category,"expensive", "tom");

        // Lägg till produkten i databasen
        underTest.save(product);

        // skapar en list
        List<Product> result = underTest.findByCategory(category);



        // Jämför kategorin på den hämtade produkten med förväntat värde
        assertEquals("jewelery",result.get(0).getCategory());
    }


    @Test
    void findByTitle() {
        //given
        String title = "dator";
        underTest.save(new Product(title, 250.3, "El", "bra att ha", "ur bild till"));
        //When
        Optional<Product> optionalProduct = underTest.findByTitle(title);

        Assertions.assertAll(
                () -> assertTrue(optionalProduct.isPresent()),
                () -> assertFalse(optionalProduct.isEmpty()),
                () -> assertEquals(title, optionalProduct.map(Product::getTitle).orElse("dator finns inte"))
        );
    }



    @Test
    void whenSearchingForNonExistingTitleEmptyOptional() {
        //given
        String title = "Mens Casual Slim Fit";

        // When letar efter en produkt via title
        Optional<Product> optionalProduct = underTest.findByTitle(title);

        // Then testa för att se om produkten existerar eller inte
        assertFalse(optionalProduct.isEmpty(),"The product doesn´t exist");
        assertTrue(optionalProduct.isPresent(), "The product already exists");


    }



    @Test
    void testFindAllCategories() {
        // Anropa findAllCategories() metoden för att hämta alla kategorier
        List<String> categories = underTest.findAllCategories();

        // Kontrollera att alla kategorier innehåller minst en produkt
        for (String category : categories) {
            List<Product> productsInCategory = underTest.findByCategory(category);
            assertFalse(productsInCategory.isEmpty());
        }

        // Kontrollera att listan inte är tom
        assertFalse(categories.isEmpty());


    }

}



