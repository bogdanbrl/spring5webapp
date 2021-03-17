package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AddressRepository;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AddressRepository addressRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, AddressRepository addressRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Author rod = new Author("Rod", "Johnson");

        Book ddd = new Book("Domain Driven Design", "123123");
        Book noEJB = new Book("J2EE Development without EJB", "1345356331549");

        Address branCastleAddress = new Address("24 Strada General Traian Moșoiu", "Bran", "Romania", "507025");
        Address addressPetersburg = new Address();
        addressPetersburg.setCity("St Petersburg");
        addressPetersburg.setState("FL");

        Publisher branCastlePublisher = new Publisher("branCastleAddress");
        Publisher publisherSFG = new Publisher();
        publisherSFG.setName("SFG Publishing");

        addressRepository.save(branCastleAddress);
        addressRepository.save(addressPetersburg);
        publisherRepository.save(branCastlePublisher);
        publisherRepository.save(publisherSFG);

        branCastlePublisher.setAddress(branCastleAddress);
        branCastleAddress.setPublisher(branCastlePublisher);
        publisherSFG.setAddress(addressPetersburg);
        addressPetersburg.setPublisher(publisherSFG);

        addressRepository.save(branCastleAddress);
        addressRepository.save(addressPetersburg);
        publisherRepository.save(branCastlePublisher);
        publisherRepository.save(publisherSFG);

        authorRepository.save(eric);
        authorRepository.save(rod);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(branCastlePublisher);
        branCastlePublisher.getBooks().add(ddd);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(branCastlePublisher);
        branCastlePublisher.getBooks().add(noEJB);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        publisherRepository.save(branCastlePublisher);




        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Bran Publisher Number of Books: " + branCastlePublisher.getBooks().size());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of addresses: " + addressRepository.count());



    }
}












