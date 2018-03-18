//package library.serialization;
//
//import com.thoughtworks.xstream.XStream;
//import library.models.Book;
//import org.glassfish.jersey.message.internal.AbstractMessageReaderWriterProvider;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.Produces;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.ext.Provider;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Type;
//
//import com.google.gson.Gson;
//
//@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//@Provider
//public class MySerializationProvider extends AbstractMessageReaderWriterProvider<Object> {
//
//
//    @Override
//    public Object readFrom(Class<Object> aClass, Type genericType,
//                           Annotation[] annotations, MediaType mediaType,
//                           MultivaluedMap<String, String> map, InputStream stream)
//            throws IOException, WebApplicationException {
//
//        if (mediaType.isCompatible(MediaType.valueOf(MediaType.APPLICATION_XML))
//                || mediaType.isCompatible(MediaType.valueOf(MediaType.TEXT_XML))) {
//            // de‐serialize the object from XML and return the object
//            // read serialization from parameter stream, create an object and return it
//        }
//        if (mediaType.isCompatible(MediaType.valueOf(MediaType.APPLICATION_JSON))) {// de‐serialize the object from JSON and return the object
//            //read serialization from parameter stream, create an object and return it
//        }
//        return null;
//    }
//
//    @Override
//    public void writeTo(Object o, Class<?> aClass, Type type,
//                        Annotation[] annotations, MediaType mediaType,
//                        MultivaluedMap<String, Object> map, OutputStream stream)
//            throws IOException, WebApplicationException {
//
//        XStream xstream = new XStream();
//
//        if (mediaType.isCompatible(MediaType.valueOf(MediaType.APPLICATION_XML))
//                || mediaType.isCompatible(MediaType.valueOf(MediaType.TEXT_XML))) {
//            // serialize the object to XML and return the object
//            // something like this:
////            Book newBook = (Book) xstream.fromXML(xml);
//
//        }
////        if (mediaType.isCompatible(MediaType.valueOf(MediaType.APPLICATION_JSON))) {
////            // serialize the object to JSON and return the object:
////            ObjectOutputStream out = yourJSONLibrary.createObjectOutputStream(stream);
////            out.writeObject(o);
////            out.close();
//
//        }
//    }
//
//    @Override
//    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
//                              MediaType arg3) {
//        return true;// de‐serialize all classes to XML and JSON
//    }
//
//    @Override
//    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
//                               MediaType arg3) {
//        return true;
//        // serialize all classes to XML and JSON
//    }
//}
