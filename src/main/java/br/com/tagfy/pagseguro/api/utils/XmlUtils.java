package br.com.tagfy.pagseguro.api.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class XmlUtils {

	public static <T> String toXml(T object) throws Exception {
		try {
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			StringWriter xml = new StringWriter();
			marshaller.marshal(object, xml);
			return xml.toString();
		} catch (JAXBException e) {
			throw new Exception(e);
		}
	}

	public static <T> String toXml(T object, String enconding) throws Exception {
		try {
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, enconding);
			StringWriter xml = new StringWriter();
			marshaller.marshal(object, xml);
			return xml.toString();
		} catch (JAXBException e) {
			throw new Exception(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> cls) throws Exception {
		try {
			JAXBContext context = JAXBContext.newInstance(cls);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StreamSource(new StringReader(xml)));
		} catch (JAXBException e) {
			throw new Exception(e);
		}
	}

}
