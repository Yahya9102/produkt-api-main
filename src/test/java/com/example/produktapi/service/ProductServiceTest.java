package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;


import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService underTest;

    @Captor
    ArgumentCaptor<Product> productCaptor;




    @Test
    void testGetAllProducts() {
        //given: ingenting specific behöver anges som jag förstår det..


        //when: metoden getAllProducts anropas
        underTest.getAllProducts();

        //then
        // Verifiera att metoden findAll() i produktRepository anropas exakt 1 gång
        verify(repository,times(1)).findAll();

        // Verifiera att ingen annan metod i produktRepository anropas
        verifyNoMoreInteractions(repository);

    }

    @Test
    void testGetAllCategories() {

        //when
        //kallar på metoden som ska testas

        underTest.getAllCategories();

        //then

        /* verifierar att repositoryns findAllCategories-metod anropas en gång
        och att inga fler interaktioner sker med repositoryn */
        verify(repository,times(1)).findAllCategories();
        verifyNoMoreInteractions(repository);
    }








    @Test
    public void testGetProductsByCategoryReturnsProductInCategory() {
        // Given Skapar en produkt och sätter dess kategori till "hello".
        String category = "hello";
        Product product = new Product("hej", 33.44, category, "mer text", "bild");


        when(repository.findByCategory(category)).thenReturn(List.of(product));


        // When När metoden getProductsByCategory anropas med kategorin "hallp" och produkten skickas tillbaka.
        List<Product> result = underTest.getProductsByCategory(category);


        // Then Kontrollerar att titeln är samma, att det finns en produkt i listan och att beskrivningen är korrekt.
        assertEquals("hej", result.get(0).getTitle()); //Kollar så det är samma title
        assertEquals(1, result.size()); // Förväntar sig en produkt i den kategorin
        assertEquals("mer text", result.get(0).getDescription()); // Hämtar även ut beskrivningen för att dubbelkolla

    }






    @Test
    public void testGetProductByIdThrowsExceptionWhenProductNotFound() {
        /*Given: Sätter upp ett scenario där id inte hittas,
        genom att returnera en tom optional när vi söker efter id:et
         */

        Integer id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        // When: getProductById anropas med det givna id:et i assertThrows,
        // förväntar oss en EntityNotFoundException
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            underTest.getProductById(id);
        });

        // Then:  Kontrollerar att exceptionen innehåller rätt meddelande,
        // att produkten med givet id inte hittades.
        assertEquals("Produkt med id " + id + " hittades inte", exception.getMessage());
    }







    @Test
    void whenAddingAProduct_thenSaveMethodShouldBeCalled() {
        // given
        // Skapar en ny produkt
        Product product = new Product("Dator", 3.4, "hej", "", "");

        // when
        underTest.addProduct(product);

        // then
        // Kollar att repository.save() anropas en gång med rätt produkt
        verify(repository, times(1)).save(productCaptor.capture());
        assertEquals(product, productCaptor.getValue());
    }





    @Test
    public void testAddProductThrowsExceptionWhenProductTitleAlreadyExists() {
        // Given skapar en ny product
        Product product = new Product("Dator",3.4,"hej","","");

        // When en produkt med samma title redan finns i databasen
        when(repository.findByTitle(product.getTitle())).thenReturn(Optional.of(product));

        // Then produkten läggs till, förväntar vi oss ett exception av typen BadRequestException
        assertThrows(BadRequestException.class, () -> underTest.addProduct(product));
    }




    @Test
    void getProductByIdTest(){
        //given
        Product product = new Product("En mobil", 33.33,"elektronik", "mobiltelefon", "tom");
        product.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(product));

        //when
        //anropar getProductById() på productService och matar in ID 1 som parameter.
        Product result = underTest.getProductById(1);

        //then
        // validerar att result är samma som den skapade produkten.
        // Om produkten inte hittas så kommer testet att misslyckas.
        assertEquals(product, result);
    }









    @Test
    public void testGetProductByIdReturnsProduct() {
        // Given skapar en ny product och sätter dess id till 1
        Product product = new Product("En mobil", 33.33,"elektronik", "mobiltelefon", "tom");
        product.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(product));

        // When: getProductById(1) anropas på underTest-objektet för att hämta produkten med ID 1.
        Product result = underTest.getProductById(1);

        // Then: Jämför att den hämtade produkten är samma som den förväntade produkten.
        // Kontrollerar också att ID på den hämtade produkten är 1.
        assertEquals(product, result);
        assertEquals(1, result.getId());

    }





    @Test
    public void testUpdateProductSavesUpdatedProduct() {
        // Given
        // Skapar en ny uppdaterad produkt och tilldelar id 1
        Product updatedProduct = new Product("Dator", 5.6, "hej", "", "");
        Integer id = 1;

        // Sätter upp ett scenario där findById-metoden anropas med id 1 och returnerar en valfri produkt från databasen
        when(repository.findById(id)).thenReturn(Optional.of(new Product()));

        // Sätter upp ett scenario där save-metoden anropas med en valfri produkt och returnerar den uppdaterade produkten.
        when(repository.save(any())).thenReturn(updatedProduct);

        // When: anropar metoden updateProduct på productService med de uppdaterade produkt och id som parameter
        Product result = underTest.updateProduct(updatedProduct, id);

        // Then: Kollar att det uppdaterade resultatet är samma som den nya uppdaterade produkten.
        assertEquals(updatedProduct, result);
    }




    @Test
    public void testUpdateProductDoesNotUpdateIfProductNotFound() {
        // Given
        // Skapar en ny produkt
        Product updatedProduct = new Product("Dator", 5.6, "hej", "", "");
        Integer id = 1;

        // When
        // Sätter upp ett scenario där man försöker hitta id med findById,
        // sedan ska den returnera en ny product
        when(repository.findById(id)).thenReturn(Optional.of(new Product()));
        when(repository.save(any())).thenReturn(null);

        // Then
        // Vi validerar att metoden updateProduct returnerar null när den inte hittar produkten.
        assertNull(underTest.updateProduct(updatedProduct, id));
    }






    @Test
    public void testUpdateProductTitleUpdatesTitle() {
        // Given: skapar en uppdaterad produkt och sätter ett ID
        Product updatedProduct = new Product("Dator", 3.4, "hej", "", "");
        Integer id = 1;


        //when anropar findById() med id och returnerar en optional som innehåller den uppdaterade produkten
        when(repository.findById(id)).thenReturn(Optional.of(updatedProduct));
        //sparar uppdaterad produkt till databasen

        when(repository.save(updatedProduct)).thenReturn(updatedProduct);


        // When
        Product result = underTest.updateProduct(updatedProduct, id);

        // Then:jämför den nya titeln med den förväntade titeln "Dator"
        assertEquals("Dator", result.getTitle());
    }





    @Test
    void testUpdateProductThrowsExceptionWhenProductNotFound() {
        // Given: skapar ett scenario där produkten med angivet id inte finns i databasen

        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // When och then - förväntar oss att en EntityNotFoundException kommer
        // att kastas när vi försöker uppdatera en produkt som inte finns
        assertThrows(EntityNotFoundException.class, () -> {
            underTest.updateProduct(new Product(), id);
        });

    }


    @Test
    void testDeleteProductDeletesProduct() {
        // Given skapar en produkt med id 1
        Integer id = 1;
        Product product = new Product();
        when(repository.findById(id)).thenReturn(Optional.of(product));

        // When anropar vi deleteProduct-metoden.
        underTest.deleteProduct(id);

        // Then verifierar vi att deleteById-metoden anropas på repositoryt med id 1 genom att använda verify()-metoden.
        verify(repository).deleteById(id);
    }




    @Test
    void testDeleteProductThrowsExceptionWhenProductNotFound() {
        // Given sätter upp ett scenario där en produkt med id 1 inte finns i repositoryn.
        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // When anropar deleteProduct-metoden med id 1.
        // förväntar oss att metoden kastar en EntityNotFoundException.
        assertThrows(EntityNotFoundException.class, () -> {
            underTest.deleteProduct(id);
        });
    }




    @Test //test för github actions
    void testingForGitHub(){
        assertEquals(1,1,"fel siffra");
    }

}