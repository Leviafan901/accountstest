package by.tut.accounttests.parsers.xml;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.tut.accounttests.domain.UserAccount;

public class SaxHandler extends DefaultHandler {

	private String currentElement;
	private Processor processor = new Processor();

	public List<UserAccount> getPlantList() {
		List<UserAccount> accountList = processor.getAccountList();
		return accountList;
	}

	@Override
	public void startDocument() throws SAXException {
		processor.processStartDocument();
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
			throws SAXException {
		currentElement = qName;
		processor.processStartElement(currentElement, attributes);
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		processor.processEndElement(qName);
		currentElement = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		boolean isNull = currentElement == null;
		if (isNull) {
			return;
		}
		String elementValue = new String(ch, start, length);
		processor.processCharacters(currentElement, elementValue);
	}

	@Override
	public void endDocument() {
		processor.processEndDocument();
	}
}
