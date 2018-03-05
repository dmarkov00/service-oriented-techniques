
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetBookByName_QNAME = new QName("http://service/", "getBookByName");
    private final static QName _FilterByGenre_QNAME = new QName("http://service/", "filterByGenre");
    private final static QName _GetAllBooks_QNAME = new QName("http://service/", "getAllBooks");
    private final static QName _FilterByGenreResponse_QNAME = new QName("http://service/", "filterByGenreResponse");
    private final static QName _GetMemberResponse_QNAME = new QName("http://service/", "getMemberResponse");
    private final static QName _RegisterMember_QNAME = new QName("http://service/", "registerMember");
    private final static QName _RegisterMemberResponse_QNAME = new QName("http://service/", "registerMemberResponse");
    private final static QName _GetBooksCheaperThanResponse_QNAME = new QName("http://service/", "getBooksCheaperThanResponse");
    private final static QName _GetBookByNameResponse_QNAME = new QName("http://service/", "getBookByNameResponse");
    private final static QName _GetMember_QNAME = new QName("http://service/", "getMember");
    private final static QName _GetAllBooksResponse_QNAME = new QName("http://service/", "getAllBooksResponse");
    private final static QName _GetBooksCheaperThan_QNAME = new QName("http://service/", "getBooksCheaperThan");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FilterByGenreResponse }
     * 
     */
    public FilterByGenreResponse createFilterByGenreResponse() {
        return new FilterByGenreResponse();
    }

    /**
     * Create an instance of {@link GetAllBooks }
     * 
     */
    public GetAllBooks createGetAllBooks() {
        return new GetAllBooks();
    }

    /**
     * Create an instance of {@link FilterByGenre }
     * 
     */
    public FilterByGenre createFilterByGenre() {
        return new FilterByGenre();
    }

    /**
     * Create an instance of {@link GetBookByName }
     * 
     */
    public GetBookByName createGetBookByName() {
        return new GetBookByName();
    }

    /**
     * Create an instance of {@link GetAllBooksResponse }
     * 
     */
    public GetAllBooksResponse createGetAllBooksResponse() {
        return new GetAllBooksResponse();
    }

    /**
     * Create an instance of {@link GetBooksCheaperThan }
     * 
     */
    public GetBooksCheaperThan createGetBooksCheaperThan() {
        return new GetBooksCheaperThan();
    }

    /**
     * Create an instance of {@link GetBookByNameResponse }
     * 
     */
    public GetBookByNameResponse createGetBookByNameResponse() {
        return new GetBookByNameResponse();
    }

    /**
     * Create an instance of {@link GetMember }
     * 
     */
    public GetMember createGetMember() {
        return new GetMember();
    }

    /**
     * Create an instance of {@link GetBooksCheaperThanResponse }
     * 
     */
    public GetBooksCheaperThanResponse createGetBooksCheaperThanResponse() {
        return new GetBooksCheaperThanResponse();
    }

    /**
     * Create an instance of {@link GetMemberResponse }
     * 
     */
    public GetMemberResponse createGetMemberResponse() {
        return new GetMemberResponse();
    }

    /**
     * Create an instance of {@link RegisterMember }
     * 
     */
    public RegisterMember createRegisterMember() {
        return new RegisterMember();
    }

    /**
     * Create an instance of {@link RegisterMemberResponse }
     * 
     */
    public RegisterMemberResponse createRegisterMemberResponse() {
        return new RegisterMemberResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getBookByName")
    public JAXBElement<GetBookByName> createGetBookByName(GetBookByName value) {
        return new JAXBElement<GetBookByName>(_GetBookByName_QNAME, GetBookByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterByGenre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "filterByGenre")
    public JAXBElement<FilterByGenre> createFilterByGenre(FilterByGenre value) {
        return new JAXBElement<FilterByGenre>(_FilterByGenre_QNAME, FilterByGenre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllBooks")
    public JAXBElement<GetAllBooks> createGetAllBooks(GetAllBooks value) {
        return new JAXBElement<GetAllBooks>(_GetAllBooks_QNAME, GetAllBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterByGenreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "filterByGenreResponse")
    public JAXBElement<FilterByGenreResponse> createFilterByGenreResponse(FilterByGenreResponse value) {
        return new JAXBElement<FilterByGenreResponse>(_FilterByGenreResponse_QNAME, FilterByGenreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getMemberResponse")
    public JAXBElement<GetMemberResponse> createGetMemberResponse(GetMemberResponse value) {
        return new JAXBElement<GetMemberResponse>(_GetMemberResponse_QNAME, GetMemberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterMember }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "registerMember")
    public JAXBElement<RegisterMember> createRegisterMember(RegisterMember value) {
        return new JAXBElement<RegisterMember>(_RegisterMember_QNAME, RegisterMember.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterMemberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "registerMemberResponse")
    public JAXBElement<RegisterMemberResponse> createRegisterMemberResponse(RegisterMemberResponse value) {
        return new JAXBElement<RegisterMemberResponse>(_RegisterMemberResponse_QNAME, RegisterMemberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooksCheaperThanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getBooksCheaperThanResponse")
    public JAXBElement<GetBooksCheaperThanResponse> createGetBooksCheaperThanResponse(GetBooksCheaperThanResponse value) {
        return new JAXBElement<GetBooksCheaperThanResponse>(_GetBooksCheaperThanResponse_QNAME, GetBooksCheaperThanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getBookByNameResponse")
    public JAXBElement<GetBookByNameResponse> createGetBookByNameResponse(GetBookByNameResponse value) {
        return new JAXBElement<GetBookByNameResponse>(_GetBookByNameResponse_QNAME, GetBookByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMember }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getMember")
    public JAXBElement<GetMember> createGetMember(GetMember value) {
        return new JAXBElement<GetMember>(_GetMember_QNAME, GetMember.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllBooksResponse")
    public JAXBElement<GetAllBooksResponse> createGetAllBooksResponse(GetAllBooksResponse value) {
        return new JAXBElement<GetAllBooksResponse>(_GetAllBooksResponse_QNAME, GetAllBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooksCheaperThan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getBooksCheaperThan")
    public JAXBElement<GetBooksCheaperThan> createGetBooksCheaperThan(GetBooksCheaperThan value) {
        return new JAXBElement<GetBooksCheaperThan>(_GetBooksCheaperThan_QNAME, GetBooksCheaperThan.class, null, value);
    }

}
