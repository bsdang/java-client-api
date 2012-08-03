package com.marklogic.client.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.marklogic.client.MarkLogicIOException;

public final class Utilities {
	

	private static DocumentBuilderFactory factory;
	
	private static DocumentBuilderFactory getFactory()
			throws ParserConfigurationException {
		if (factory == null)
			factory = makeDocumentBuilderFactory();
		return factory;
	}

	private static DocumentBuilderFactory makeDocumentBuilderFactory()
			throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(false);
		return factory;
	}
	/**
	 * Construct a dom Element from a string. A utility function for creating
	 * DOM elements when needed for other builder functions.
	 * 
	 * @param xmlString
	 *            XML for an element.
	 * @return w3c.dom.Element representation the provided XML.
	 */
	public static org.w3c.dom.Element domElement(String xmlString) {
		org.w3c.dom.Element element = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(
					xmlString.getBytes());
			element = getFactory().newDocumentBuilder().parse(bais)
					.getDocumentElement();
		} catch (SAXException e) {
			throw new MarkLogicIOException(
					"Could not make Element from xmlString" + xmlString, e);
		} catch (IOException e) {
			throw new MarkLogicIOException(
					"Could not make Element from xmlString" + xmlString, e);
		} catch (ParserConfigurationException e) {
			throw new MarkLogicIOException(
					"Could not make Element from xmlString" + xmlString, e);
		}
		return element;
	}
}