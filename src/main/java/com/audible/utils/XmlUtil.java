package com.audible.utils;

import com.audible.invoker.FilesToDiffInvoker;
import com.audible.model.Table;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {


  public static List<Table> getTables(String datebaseFilename) throws Exception {

    List<Table> tables = new ArrayList<>();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(getResource(datebaseFilename));

    //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
    NodeList item = doc.getElementsByTagName("ITEM");

    for (int temp = 0; temp < item.getLength(); temp++) {
      Node nNode = item.item(temp);
      //System.out.println("\nCurrent Element :" + nNode.getNodeName());
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {

        Element eElement = (Element) nNode;
        Table table = new Table();
        //System.out.println("Table Name : " + eElement.getElementsByTagName("TABLE").item(0).getTextContent());
        table.setName(eElement.getElementsByTagName("TABLE").item(0).getTextContent());

        //System.out.println("Table SQL: " + eElement.getElementsByTagName("SQL").item(0).getTextContent());
        table.setSql(eElement.getElementsByTagName("SQL").item(0).getTextContent());

        tables.add(table);
      }
    }

    return tables;
  }


  public static InputStream getResource(String fileName) throws Exception{
    return FilesToDiffInvoker.class.getClassLoader().getResourceAsStream(fileName);
  }

  public static String getResourceFileAsString(String fileName) throws Exception{
    InputStream is = FilesToDiffInvoker.class.getClassLoader().getResourceAsStream(fileName);
    if (is != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
    return null;
  }


}
