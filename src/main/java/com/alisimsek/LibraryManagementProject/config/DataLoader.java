package com.alisimsek.LibraryManagementProject.config;

import com.alisimsek.LibraryManagementProject.entity.*;
import com.alisimsek.LibraryManagementProject.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Configuration
public class DataLoader {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Bean
  @Transactional
  public CommandLineRunner initializeData(
      AuthorRepository authorRepository,
      PublisherRepository publisherRepository,
      CategoryRepository categoryRepository,
      BookRepository bookRepository,
      BookBorrowingRepository bookBorrowingRepository) {
    return args -> {
      // Prevent duplicate data loading
      if (authorRepository.count() > 0) {
        return;
      }

      // ### 1. Initialize Categories ###
      String categoriesJson = """
          {
            "categories": [
              {
                "name": "Fiction",
                "description": "A narrative that is created from the imagination, not based strictly on history or fact."
              },
              {
                "name": "Science Fiction",
                "description": "Fiction dealing with futuristic settings, futuristic science and technology, space travel, time travel, parallel universes, etc."
              },
              {
                "name": "Fantasy",
                "description": "A genre of speculative fiction set in a fictional universe, often inspired by real world myth and folklore."
              },
              {
                "name": "Mystery",
                "description": "Fiction dealing with the solution of a crime or the unraveling of secrets."
              },
              {
                "name": "Historical",
                "description": "Fiction that has historical settings and real historical events or figures."
              },
              {
                "name": "Biography",
                "description": "A detailed description or account of someone's life."
              },
              {
                "name": "Technology",
                "description": "Books on technology and computing."
              },
              {
                "name": "Philosophy",
                "description": "Philosophical literature."
              }
            ]
          }
          """;

      JsonNode categoriesNode = objectMapper.readTree(categoriesJson).get("categories");
      List<Category> categories = new ArrayList<>();
      for (JsonNode node : categoriesNode) {
        Category category = new Category();
        category.setName(node.get("name").asText());
        category.setDescription(node.get("description").asText());
        categories.add(category);
      }
      categoryRepository.saveAll(categories);

      // ### 2. Initialize Publishers ###
      String publishersJson = """
          {
            "publishers": [
              {
                "name": "Harper & Row",
                "establishmentYear": 1962,
                "address": "195 Broadway, New York, NY"
              },
              {
                "name": "Pan Books",
                "establishmentYear": 1944,
                "address": "83-85 Great Sutton Street, London, UK"
              },
              {
                "name": "Yapı Kredi Yayınları",
                "establishmentYear": 1986,
                "address": "Kalaycıoğlu Mahallesi, Ümit Hatun Sokak No:3, İstanbul, Turkey"
              },
              {
                "name": "Alfred A. Knopf",
                "establishmentYear": 1915,
                "address": "20 East 60th Street, New York, NY"
              },
              {
                "name": "William Morrow",
                "establishmentYear": 1926,
                "address": "175 Fifth Avenue, New York, NY"
              },
              {
                "name": "Houghton Mifflin",
                "establishmentYear": 1832,
                "address": "125 High Street, Boston, MA"
              },
              {
                "name": "Emecé Editores",
                "establishmentYear": 1939,
                "address": "Buenos Aires, Argentina"
              },
              {
                "name": "Knopf",
                "establishmentYear": 1915,
                "address": "20 East 60th Street, New York, NY"
              }
            ]
          }
          """;

      JsonNode publishersNode = objectMapper.readTree(publishersJson).get("publishers");
      List<Publisher> publishers = new ArrayList<>();
      for (JsonNode node : publishersNode) {
        Publisher publisher = new Publisher();
        publisher.setName(node.get("name").asText());
        publisher.setEstablishmentYear(node.get("establishmentYear").asInt());
        publisher.setAddress(node.get("address").asText());
        publishers.add(publisher);
      }
      publisherRepository.saveAll(publishers);

      // ### 3. Initialize Authors ###
      String authorsJson = """
          {
            "authors": [
              {
                "name": "Gabriel García Márquez",
                "birthDate": "1927-03-06",
                "country": "Colombia"
              },
              {
                "name": "Douglas Adams",
                "birthDate": "1952-03-11",
                "country": "United Kingdom"
              },
              {
                "name": "Ahmet Hamdi Tanpınar",
                "birthDate": "1901-08-23",
                "country": "Turkey"
              },
              {
                "name": "Haruki Murakami",
                "birthDate": "1949-01-12",
                "country": "Japan"
              },
              {
                "name": "Isaac Asimov",
                "birthDate": "1920-01-02",
                "country": "United States"
              },
              {
                "name": "Orhan Pamuk",
                "birthDate": "1952-06-07",
                "country": "Turkey"
              },
              {
                "name": "Yasunari Kawabata",
                "birthDate": "1899-06-14",
                "country": "Japan"
              },
              {
                "name": "Neil Gaiman",
                "birthDate": "1960-11-10",
                "country": "United Kingdom"
              },
              {
                "name": "Margaret Atwood",
                "birthDate": "1939-11-18",
                "country": "Canada"
              },
              {
                "name": "Jorge Luis Borges",
                "birthDate": "1899-08-24",
                "country": "Argentina"
              }
            ]
          }
          """;

      JsonNode authorsNode = objectMapper.readTree(authorsJson).get("authors");
      List<Author> authors = new ArrayList<>();
      for (JsonNode node : authorsNode) {
        Author author = new Author();
        author.setName(node.get("name").asText());
        author.setBirthDate(LocalDate.parse(node.get("birthDate").asText()));
        author.setCountry(node.get("country").asText());
        authors.add(author);
      }
      authorRepository.saveAll(authors);

      // ### 4. Initialize Books ###
      String booksJson = """
          {
            "books": [
              {
                "name": "One Hundred Years of Solitude",
                "publicationYear": 1967,
                "stock": 15,
                "authorName": "Gabriel García Márquez",
                "publisherName": "Harper & Row",
                "categoryNames": ["Fiction", "Historical"]
              },
              {
                "name": "Love in the Time of Cholera",
                "publicationYear": 1985,
                "stock": 10,
                "authorName": "Gabriel García Márquez",
                "publisherName": "Harper & Row",
                "categoryNames": ["Fiction"]
              },
              {
                "name": "The Hitchhiker's Guide to the Galaxy",
                "publicationYear": 1979,
                "stock": 20,
                "authorName": "Douglas Adams",
                "publisherName": "Pan Books",
                "categoryNames": ["Science Fiction"]
              },
              {
                "name": "The Restaurant at the End of the Universe",
                "publicationYear": 1980,
                "stock": 18,
                "authorName": "Douglas Adams",
                "publisherName": "Pan Books",
                "categoryNames": ["Science Fiction"]
              },
              {
                "name": "Norwegian Wood",
                "publicationYear": 1987,
                "stock": 14,
                "authorName": "Haruki Murakami",
                "publisherName": "Alfred A. Knopf",
                "categoryNames": ["Fiction", "Mystery"]
              },
              {
                "name": "Foundation",
                "publicationYear": 1951,
                "stock": 22,
                "authorName": "Isaac Asimov",
                "publisherName": "William Morrow",
                "categoryNames": ["Science Fiction"]
              },
              {
                "name": "I, Robot",
                "publicationYear": 1950,
                "stock": 25,
                "authorName": "Isaac Asimov",
                "publisherName": "William Morrow",
                "categoryNames": ["Science Fiction", "Philosophy"]
              },
              {
                "name": "American Gods",
                "publicationYear": 2001,
                "stock": 19,
                "authorName": "Neil Gaiman",
                "publisherName": "William Morrow",
                "categoryNames": ["Fantasy", "Fiction"]
              },
              {
                "name": "The Handmaid's Tale",
                "publicationYear": 1985,
                "stock": 17,
                "authorName": "Margaret Atwood",
                "publisherName": "Houghton Mifflin",
                "categoryNames": ["Fiction", "Science Fiction"]
              },
              {
                "name": "Ficciones",
                "publicationYear": 1944,
                "stock": 11,
                "authorName": "Jorge Luis Borges",
                "publisherName": "Emecé Editores",
                "categoryNames": ["Fiction", "Philosophy"]
              },
              {
                "name": "Snow Country",
                "publicationYear": 1947,
                "stock": 9,
                "authorName": "Yasunari Kawabata",
                "publisherName": "Knopf",
                "categoryNames": ["Fiction", "Philosophy"]
              },
              {
                "name": "My Name is Red",
                "publicationYear": 1998,
                "stock": 13,
                "authorName": "Orhan Pamuk",
                "publisherName": "Yapı Kredi Yayınları",
                "categoryNames": ["Fiction", "Historical"]
              }
            ]
          }
          """;

      JsonNode booksNode = objectMapper.readTree(booksJson).get("books");
      List<Book> books = new ArrayList<>();

      for (JsonNode node : booksNode) {
        Book book = new Book();
        book.setName(node.get("name").asText());
        book.setPublicationYear(node.get("publicationYear").asInt());
        book.setStock(node.get("stock").asInt());

        // Find Author by name
        String authorName = node.get("authorName").asText();
        Optional<Author> authorOpt = authorRepository.findByName(authorName);
        if (authorOpt.isPresent()) {
          book.setAuthor(authorOpt.get());
        } else {
          System.err.println("Author not found: " + authorName);
          continue; // Skip this book if author not found
        }

        // Find Publisher by name
        String publisherName = node.get("publisherName").asText();
        Optional<Publisher> publisherOpt = publisherRepository.findByName(publisherName);
        if (publisherOpt.isPresent()) {
          book.setPublisher(publisherOpt.get());
        } else {
          System.err.println("Publisher not found: " + publisherName);
          continue; // Skip this book if publisher not found
        }

        // Find Categories by names
        List<String> categoryNames = new ArrayList<>();
        node.get("categoryNames").forEach(catNode -> categoryNames.add(catNode.asText()));
        List<Category> bookCategories = categoryRepository.findAllByNameIn(categoryNames);
        if (bookCategories.size() != categoryNames.size()) {
          System.err.println("Some categories not found for book: " + book.getName());
          // Optionally handle missing categories
        }
        book.setCategories(bookCategories);

        books.add(book);
      }

      bookRepository.saveAll(books);

      // ### 5. Initialize Book Borrowings ###
      String borrowingsJson = """
          {
            "bookBorrowings": [
              {
                "borrowerName": "John Doe",
                "borrowerMail": "johndoe@example.com",
                "borrowingDate": "2024-11-20",
                "bookName": "One Hundred Years of Solitude"
              },
              {
                "borrowerName": "Jane Smith",
                "borrowerMail": "janesmith@example.com",
                "borrowingDate": "2024-11-23",
                "bookName": "The Hitchhiker's Guide to the Galaxy"
              },
              {
                "borrowerName": "Alice Johnson",
                "borrowerMail": "alicej@example.com",
                "borrowingDate": "2024-11-15",
                "bookName": "Norwegian Wood"
              },
              {
                "borrowerName": "Bob Williams",
                "borrowerMail": "bobw@example.com",
                "borrowingDate": "2024-11-18",
                "bookName": "I, Robot"
              },
              {
                "borrowerName": "Charlie Brown",
                "borrowerMail": "charlieb@example.com",
                "borrowingDate": "2024-11-24",
                "bookName": "The Handmaid's Tale"
              }
            ]
          }
          """;

      JsonNode borrowingsNode = objectMapper.readTree(borrowingsJson).get("bookBorrowings");
      List<BookBorrowing> borrowings = new ArrayList<>();

      for (JsonNode node : borrowingsNode) {
        BookBorrowing borrowing = new BookBorrowing();
        borrowing.setBorrowerName(node.get("borrowerName").asText());
        borrowing.setBorrowerMail(node.get("borrowerMail").asText());
        borrowing.setBorrowingDate(LocalDate.parse(node.get("borrowingDate").asText()));

        // Find Book by name
        String bookName = node.get("bookName").asText();
        Optional<Book> bookOpt = bookRepository.findByName(bookName);
        if (bookOpt.isPresent()) {
          borrowing.setBook(bookOpt.get());
        } else {
          System.err.println("Book not found for borrowing: " + bookName);
          continue; // Skip if book not found
        }

        borrowings.add(borrowing);
      }

      bookBorrowingRepository.saveAll(borrowings);

      // Optional: If you need to update inverse relationships, ensure they are
      // handled.
      // However, since the owning side is set, Hibernate should manage the inverse
      // side.
      // If you face issues with inverse relationships not being updated, consider
      // manually adding the books to authors and publishers.
    };
  }
}
